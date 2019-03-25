package cz.uhk.umte.services

import android.app.IntentService
import android.content.Intent
import cz.uhk.umte.ws.StagService
import cz.uhk.umte.ws.stagService
import java.text.SimpleDateFormat
import java.util.*

class RoomService : IntentService {

    constructor() : super("RoomService")
    constructor(name: String?) : super(name)

    override fun onHandleIntent(intent: Intent?) {

        val rooms = mutableListOf<String>("J8", "J9", "J10", "J23")
        val building = "J"

        val date = SimpleDateFormat("d.M.yyyy").format(Date())

        for(room in rooms){
            val response = stagService.getTimetable(
                building,
                room,
                date,
                date,
                StagService.JSON
            ).execute()//zastaví vlákno a čeká na odpověď

            // rozšíření foreach eliminuje null části
            response.body()?.items?.forEach {
                println(it.toString())
            }
        }

        // TODO pro seznam učeben stáhnout rozvrhy
    }
}