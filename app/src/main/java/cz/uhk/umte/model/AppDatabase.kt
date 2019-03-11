package cz.uhk.umte.model

import com.raizlabs.android.dbflow.annotation.Database

@Database(version = AppDatabase.VERSION, name = AppDatabase.NAME)
object AppDatabase {

    const val VERSION = 1
    const val NAME = "UmteAppDB"
}