package com.davalIndustries.daval.moneytoday.SimuladorInver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SimuladorInverViewModelFactory (private val inversionReq : Double, private val interesInverReq : Double, private val plazoInverReq : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimuladorInverViewModel::class.java)) {
            return SimuladorInverViewModel(inversionReq, interesInverReq, plazoInverReq) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}