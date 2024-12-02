package com.example.project1.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.data.model.LoginRequest
import com.example.project1.data.model.User
import com.example.project1.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {


    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    // Método para iniciar sesión
    fun login(username: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                // Llamada a la API
                val response = RetrofitClient.api.login(LoginRequest(username, password))

                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null && body.login == "success" && body.user.isNotEmpty()) {
                        _loginState.value = LoginState.Success(body.user[0])
                    } else {
                        _loginState.value = LoginState.Error("Credenciales incorrectas")
                    }
                } else {
                    _loginState.value = LoginState.Error("Error en la solicitud: ${response.code()}")
                }
            } catch (e: HttpException) {
                _loginState.value = LoginState.Error("Error HTTP: ${e.message}")
            } catch (e: IOException) {
                _loginState.value = LoginState.Error("Error de conexión: ${e.message}")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error desconocido: ${e.message}")
            }
        }
    }
}

// Estados posibles del proceso de login
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}
