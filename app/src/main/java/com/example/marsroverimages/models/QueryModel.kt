package com.example.marsroverimages.models

import java.io.Serializable

class QueryModel() : Serializable {
    var name: String = ""
    var camera: String? = null
    var earthDate: String? = null
}