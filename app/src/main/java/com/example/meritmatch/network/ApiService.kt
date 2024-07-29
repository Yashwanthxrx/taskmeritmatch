import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Header

data class SignUpRequest(val username: String, val password: String)
data class LoginRequest(val username: String, val password: String)
data class Task(val id: Int, val description: String)
data class AuthResponse(val access_token: String)

interface ApiService {
    @POST("/signup")
    fun signUp(@Body request: SignUpRequest): AuthResponse

    @POST("/login")
    fun login(@Body request: LoginRequest): AuthResponse

    @GET("/tasks")
    fun getTasks(@Header("Authorization") token: String): List<Task>

    @POST("/tasks/reserve/{taskId}")
    fun reserveTask(@Path("taskId") taskId: Int, @Header("Authorization") token: String)

    @POST("/tasks/complete/{taskId}")
    fun completeTask(@Path("taskId") taskId: Int, @Header("Authorization") token: String)
}

