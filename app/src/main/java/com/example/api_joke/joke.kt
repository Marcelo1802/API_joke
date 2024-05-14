// Define o pacote onde essa classe e interfaces estão localizadas
package com.example.api_joke.retrofit

// Importa as classes necessárias do Retrofit
import retrofit2.Call
import retrofit2.http.GET

// Interface que define os endpoints da API Chuck Norris
interface ChuckNorrisApiService {
    // Anotação que indica que essa função faz uma requisição GET para o endpoint "jokes/random"
    @GET("jokes/random")
    // Função que retorna um objeto Call que representa a chamada para obter uma piada aleatória
    fun getRandomJoke(): Call<ChuckNorrisJoke>
}

// Data class que representa uma piada de Chuck Norris
data class ChuckNorrisJoke(
    val icon_url: String, // URL do ícone associado à piada
    val id: String, // ID único da piada
    val url: String, // URL para acessar a piada na web
    val value: String // A própria piada
)
