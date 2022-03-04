package com.example.daval.moneytoday.Simulador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
            Log.e("navigation", Strcredito)
            if (Strcredito.isNotEmpty()  || Strinteres.isNotEmpty() || Strplazo.isNotEmpty()) {
                credito = Strcredito.toDouble()
                interes = Strinteres.toDouble()
                plazo = Strplazo.toInt()
                Log.e("navigation", (credito/credito).toString())

            }
            val mCredito = simuladorCredito (credito,interes, plazo)
            adapter = context?.let { CustomAdapter(it, mCredito) }!!
            binding.recyclerView.adapter = adapter
        }
        return binding.root



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun simuladorCredito(credito: Double, interes: Double, plazo: Int): ArrayList<objSimuladorCredito> {
        val objCuotaNumero = objSimuladorCredito(0,0.0,0.0,credito,0.0)
        matrizCredito.add(0,objCuotaNumero)
        for (i in 1 .. plazo) {
            coutaNumero++
            cuota = ((credito*(interes*Math.pow((1.0+interes),plazo.toDouble())))/(Math.pow((1.0+interes),plazo.toDouble())-1.0))
            intereses  = matrizCredito.get(i-1).saldo
            abonoCapital = cuota-interes
            saldo = matrizCredito.get(i-1).saldo - abonoCapital
            matrizCredito.add(i,objSimuladorCredito(coutaNumero, abonoCapital, intereses, saldo, cuota))
        }
        return matrizCredito
    }


}