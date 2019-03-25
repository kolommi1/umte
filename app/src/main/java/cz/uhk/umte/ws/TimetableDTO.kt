package cz.uhk.umte.ws

import com.google.gson.annotations.SerializedName

class TimetableDTO {
    @SerializedName("rozvrhovaAkce")
    var items: List<ItemDTO>?= null
}

class ItemDTO {
    @SerializedName("nazev")
    var name: String? = null

    @SerializedName("typAkce")
    var type: String? = null

    @SerializedName("vsichniUciteleJmenaTituly")
    var teacher: String? = null

    override fun toString(): String {
        return "$type $name $teacher"
    }
}