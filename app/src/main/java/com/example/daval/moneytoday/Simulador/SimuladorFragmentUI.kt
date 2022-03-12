package com.example.daval.moneytoday.Simulador

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.daval.moneytoday.Adapter.CustomAdapter
import com.example.daval.moneytoday.R
import com.example.daval.moneytoday.dataMoneyToday.objSimuladorCredito
import com.example.daval.moneytoday.databinding.FragmentSimuladorUIBinding


class SimuladorFragmentUI : Fragment() {
    private lateinit var binding: FragmentSimuladorUIBinding
    private lateinit var adapter: CustomAdapter
    private var credito:Double = 0.0
    private var interes:Double = 0.0
    private var plazo:Int = 0
    private var cuota:Double = 0.0
    private var coutaNumero: Int = 0
    private var abonoCapital: Double = 0.0
    private var intereses: Double = 0.0
    private var saldo:Double = 0.0
    private var matrizCredito: ArrayList<objSimuladorCredito> = ArrayList()


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simulador_u_i, null, false)


        binding.btnSimulador.setOnClickListener {
            var Strcredito = binding.simCredito.text.toString()
            var Strinteres = binding.simInteres.text.toString()
            var Strplazo = binding.simPlazo.text.toString()
            if (Strcredito.isNotEmpty() || Strinteres.isNotEmpty() || Strplazo.isNotEmpty()) {
                credito = Strcredito.toDouble()
                interes = Strinteres.toDouble()
                plazo = Strplazo.toInt()

            }
            simuladorCredito(credito, interes, plazo)
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().applicationWindowToken, 0)
        }
        return binding.root



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }




    private fun simuladorCredito(credito: Double, interes: Double, plazo: Int): ArrayList<objSimuladorCredito> {
        val interesConversion = interes/100.00
        val objCuotaNumero = objSimuladorCredito(0,0.0,0.0,credito,0.0)
        matrizCredito.add(0,objCuotaNumero)
        cuota = Math.round(credito*(((interesConversion*(Math.pow((1.0+interesConversion),plazo.toDouble()))))/
                (Math.pow((1.0+interesConversion),plazo.toDouble())-1.0))*100.0).toDouble()/100.0
        for (i in 1 .. plazo+1) {
            coutaNumero++
            intereses  = Math.round(((matrizCredito.get(i-1).saldo)*interesConversion)*100.0).toDouble()/100.0
            abonoCapital = Math.round((cuota-intereses)*100.0).toDouble()/100.0
            saldo = matrizCredito.get(i-1).saldo - abonoCapital
            matrizCredito.add(i,objSimuladorCredito(coutaNumero, abonoCapital, intereses, saldo, cuota))
            Log.e("credito",matrizCredito[i].toString())
        }

        return matrizCredito
    }


}