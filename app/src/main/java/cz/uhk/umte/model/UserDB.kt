package cz.uhk.umte.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.io.Serializable

@Table(database = AppDatabase::class)
class UserDB : BaseModel(), Serializable{

    @PrimaryKey(autoincrement = true)
    var id: Int? = 0

    @Column
    var name: String? = null

    @Column
    var lastName: String? = null

    @Column
    var age: Int? = null

    @Column
    var weight: Float? = null
}