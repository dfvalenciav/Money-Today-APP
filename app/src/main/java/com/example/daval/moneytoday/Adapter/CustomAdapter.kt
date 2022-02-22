package com.example.daval.moneytoday.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daval.moneytoday.R
import com.example.daval.moneytoday.dataMoneyToday.objSimuladorCredito

class CustomAdapter (private val context: Context, private val arraySimCredito: ArrayList<objSimuladorCredito>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val mInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.simcredito_list_item, parent, false )
            return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cuota: TextView
        val abonoCapital: TextView
        val intereses: TextView
        val saldo: TextView
        val VDeLaCuota: TextView

        init {
            cuota = view.findViewById(R.id.cuota)
            abonoCapital = view.findViewById(R.id.abonoCapital)
            intereses = view.findViewById(R.id.intereses)
            saldo = view.findViewById(R.id.saldo)
            VDeLaCuota = view.findViewById(R.id.vCuota)
        }
    }

    override fun getItemCount() =  arraySimCredito.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val simCuota = arraySimCredito.get(position).couta.toString()
        val simAbono = arraySimCredito.get(position).abono.toString()
        val simIntereses = arraySimCredito.get(position).instereses.toString()
        val simSaldo = arraySimCredito.get(position).saldo.toString()
        val simValCuota = arraySimCredito.get(position).valCouta.toString()

        holder.cuota.setText(simCuota)
        holder.abonoCapital.setText(simAbono)
        holder.intereses.setText(simIntereses)
        holder.saldo.setText(simSaldo)
        holder.VDeLaCuota.setText(simValCuota)

    }
}