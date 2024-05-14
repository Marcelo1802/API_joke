package com.example.api_joke

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.api_joke.retrofit.ChuckNorrisApiService
import com.example.api_joke.retrofit.ChuckNorrisJoke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var textViewPiadas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textViewPiadas = findViewById(R.id.textViewPiadas)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { loadChuckNorrisJoke() } // Define o listener do botão para carregar a piada do Chuck Norris
    }

    private fun loadChuckNorrisJoke() {
        // Configura o Retrofit para fazer chamadas para a API do Chuck Norris
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Cria uma instância do serviço ChuckNorrisApiService
        val service = retrofit.create(ChuckNorrisApiService::class.java)

        // Faz a chamada para obter uma piada aleatória do Chuck Norris
        val call = service.getRandomJoke()
        call.enqueue(object : Callback<ChuckNorrisJoke> {
            override fun onResponse(call: Call<ChuckNorrisJoke>, response: Response<ChuckNorrisJoke>) {
                if (response.isSuccessful) {
                    // Se a resposta for bem-sucedida, atualiza o texto na textView com a piada
                    val joke = response.body()
                    textViewPiadas.text = joke?.value
                }
            }

            override fun onFailure(call: Call<ChuckNorrisJoke>, t: Throwable) {
                // Trate os erros de chamada aqui
            }
        })
    }
}