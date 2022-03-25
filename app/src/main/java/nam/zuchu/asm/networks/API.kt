package nam.zuchu.asm.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    companion object{
        private val baseURL = "https://asm.congtydacap.club/"

        fun getAPI():Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}