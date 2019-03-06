package cz.uhk.umte.model

import java.io.Serializable
data class User(val name: String,val lastName: String, val age: Int, val weight: Float) : Serializable {

}