from flask import Flask, jsonify, request, Blueprint
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flask_jwt_extended import JWTManager, create_access_token, jwt_required, get_jwt_identity
import os

# Configuration
class Config:
    SQLALCHEMY_DATABASE_URI = os.getenv('DATABASE_URL', 'sqlite:///merit_match.db')
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    JWT_SECRET_KEY = os.getenv('JWT_SECRET_KEY', 'your_jwt_secret_key')

# Initialize app and extensions
app = Flask(__name__)
app.config.from_object(Config)
db = SQLAlchemy(app)
migrate = Migrate(app, db)
jwt = JWTManager(app)

# Models
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True, nullable=False)
    password = db.Column(db.String(120), nullable=False)
    karma_points = db.Column(db.Integer, default=0)
    tasks = db.relationship('Task', backref='user', lazy=True)

class Task(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    description = db.Column(db.String(200), nullable=False)
    reserved_by = db.Column(db.Integer, db.ForeignKey('user.id'))
    is_completed = db.Column(db.Boolean, default=False)

# Routes
auth_routes = Blueprint('auth', __name__)
task_routes = Blueprint('tasks', __name__)

@auth_routes.route('/signup', methods=['POST'])
def signup():
    data = request.json
    username = data['username']
    password = data['password']
    user = User(username=username, password=password, karma_points=50)  # Initial karma points
    db.session.add(user)
    db.session.commit()
    access_token = create_access_token(identity={'id': user.id, 'username': user.username})
    return jsonify(access_token=access_token), 201

@auth_routes.route('/login', methods=['POST'])
def login():
    data = request.json
    username = data['username']
    password = data['password']
    user = User.query.filter_by(username=username, password=password).first()
    if user:
        access_token = create_access_token(identity={'id': user.id, 'username': user.username})
        return jsonify(access_token=access_token), 200
    return jsonify({"msg": "Invalid credentials"}), 401

@task_routes.route('/tasks', methods=['GET'])
@jwt_required()
def get_tasks():
    current_user = get_jwt_identity()
    tasks = Task.query.filter_by(reserved_by=None).all()
    tasks_list = [{'id': task.id, 'description': task.description} for task in tasks]
    return jsonify(tasks_list)

@task_routes.route('/tasks/reserve/<int:task_id>', methods=['POST'])
@jwt_required()
def reserve_task(task_id):
    current_user = get_jwt_identity()
    user = User.query.get_or_404(current_user['id'])
    task = Task.query.get_or_404(task_id)
    
    if task.reserved_by is None:
        task.reserved_by = user.id
        user.karma_points -= 10  # Deduct karma points for reserving a task
        db.session.commit()
        return jsonify({"msg": "Task reserved"}), 200
    
    return jsonify({"msg": "Task already reserved"}), 400

@task_routes.route('/tasks/complete/<int:task_id>', methods=['POST'])
@jwt_required()
def complete_task(task_id):
    current_user = get_jwt_identity()
    user = User.query.get_or_404(current_user['id'])
    task = Task.query.get_or_404(task_id)
    
    if task.reserved_by == user.id:
        task.is_completed = True
        user.karma_points += 20  # Add karma points for completing a task
        db.session.commit()
        return jsonify({"msg": "Task marked as completed"}), 200

    return jsonify({"msg": "You do not have permission to complete this task"}), 403

# Register blueprints
app.register_blueprint(auth_routes)
app.register_blueprint(task_routes)

if __name__ == "__main__":
    app.run(debug=True)