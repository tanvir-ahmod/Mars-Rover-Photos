package com.example.marsroverimages.models

import java.io.Serializable

class QueryModel() : Serializable {
    var name: String = ""
    var roverId: Int? = null
    var sol: String? = null
    var camera: String? = null
    var earthDate: String? = null
}