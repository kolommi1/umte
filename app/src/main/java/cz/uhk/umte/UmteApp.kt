package cz.uhk.umte

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import cz.uhk.umte.prefs.Prefs

class UmteApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Prefs.init(applicationContext)

        FlowManager.init(this)
    }
}