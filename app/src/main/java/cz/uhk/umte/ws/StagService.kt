package cz.uhk.umte.ws

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StagService {

    // nahrazuje statickou třídu
    companion object {
        const val JSON = "JSON"
        const val BASE_URL = "https://stagws.uhk.cz/ws/services/rest2/"
        fun create() : StagService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StagService::class.java)
    }

    @GET("kalendar/getHarmonogramRoku")
    fun getHarmonogram(@Query("outputFormat") format: String): Call<ScheduleDTO>

    @GET("rozvrhy/getRozvrhByMistnost")
    fun getTimetable(
        @Query("budova") building: String,
        @Query("mistnost") room: String,
        @Query("datumOd") dateFrom: String,
        @Query("datumDo") dateTo: String,
        @Query("outputFormat") format: String
    ) : Call<TimetableDTO>
}