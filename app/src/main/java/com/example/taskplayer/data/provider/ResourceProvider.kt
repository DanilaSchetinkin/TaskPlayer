package com.example.taskplayer.data.provider

interface ResourceProvider {
    fun getString(resId: Int): String
}