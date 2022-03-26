package com.example.daval.moneytoday.SimuladorCredito

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SimuladorViewModelFactory (private val creditoReq : Double, private val interesReq : Double, private val plazoReq : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimuladorViewModel::class.java)){
            return SimuladorViewModel (creditoReq, interesReq, plazoReq) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel class")
    }

}