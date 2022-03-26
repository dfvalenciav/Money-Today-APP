package com.example.daval.moneytoday.SimuladorCredito

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daval.moneytoday.dataMoneyToday.objSimuladorCredito

class SimuladorViewModel (creditoReq : Double, interesReq: Double, _plazoReq:Int ) : ViewModel() {
    private var _matrizCredito: ArrayList<objSimuladorCredito> = ArrayList()
    private var resSimulacion : ArrayList<objSimuladorCredito> = ArrayList()

    private val _credito = MutableLiveData<Double>()
    val credito : LiveData<Double>
    get() = _credito

    private val _interes = MutableLiveData<Double>()
    val interes : LiveData<Double>
        get() = _interes

    private val _plazo = MutableLiveData<Int>()
    val plazo : LiveData<Int>
        get() = _plazo

    private val _cuota = MutableLiveData<Double>()
    val cuota : LiveData<Double>
    get() =  _cuota

    private var _cuotaNumero = MutableLiveData<Int>()
    val cuotaNumero : LiveData<Int>
    get() = _cuotaNumero

    private var _intereses1 = MutableLiveData<Double>()
    val intereses : LiveData<Double>
    get() = _intereses1
    private var _intereses: Double = 0.0

    private var _abonoCapital = MutableLiveData<Double>()
    val abonoCapital : LiveData<Double>
        get() = _abonoCapital

    private var _saldo = MutableLiveData<Double>()
    val saldo : LiveData<Double>
        get() = _saldo

   init {
        _credito.value = creditoReq
        _interes.value = interesReq
        _plazo.value = _plazoReq
        _cuotaNumero.value = 0
        _abonoCapital.value = 0.0
        _saldo.value = 0.0
        resSimulacion =  simuladorCredito(_credito.value!!, _interes.value!!, _plazo.value!!)
        _intereses = sumaIntereses(resSimulacion)
       _intereses1.value = _intereses
    }



    private fun simuladorCredito(_credito: Double, _interes: Double, _plazo: Int): ArrayList<objSimuladorCredito> {
        val interesConversion = _interes/100.00
        val objCuotaNumero = objSimuladorCredito(0,0.0,0.0,_credito,0.0)
        _matrizCredito.add(0,objCuotaNumero)
        _cuota.value = Math.round(_credito*(((interesConversion*(Math.pow((1.0+interesConversion),_plazo.toDouble()))))/
                (Math.pow((1.0+interesConversion),_plazo.toDouble())-1.0))*100.0).toDouble()/100.0
        for (i in 1 .._plazo+1) {
            _cuotaNumero.value = _cuotaNumero.value?.plus(1)
            _intereses =  Math.round(((_matrizCredito.get(i-1).saldo)*(interesConversion))*(100.0)).toDouble()/100.0
            _abonoCapital.value = Math.round((_cuota.value!! - _intereses)*100.0).toDouble()/100.0
            _saldo.value = _matrizCredito.get(i-1).saldo.minus(_abonoCapital.value!!)
            _matrizCredito.add(i,
                objSimuladorCredito(_cuotaNumero.value!!, _abonoCapital.value!!,
                    _intereses, _saldo.value!!, _cuota.value!!)
            )
            Log.e("hello", _matrizCredito[i].toString())
        }
        return this._matrizCredito
    }

    private fun sumaIntereses(resSimulacion: ArrayList<objSimuladorCredito>): Double {
        var interesesSuma = 0.0
        for (i in 1 .. resSimulacion.size -1) {
            interesesSuma += (resSimulacion[i].instereses)*100.0/100.0
        }
        Log.e("hello", interesesSuma.toString())
        return interesesSuma
    }
}