package com.example.myfirstapp.network

interface Callback<T>{
    fun onSuccess(result: T?)
    fun onFailed(result: T?)
}