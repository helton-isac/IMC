package com.hitg.imc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hitg.imc.watchers.DecimalTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //region Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etPeso.addTextChangedListener(DecimalTextWatcher(etPeso, 1))
        etAltura.addTextChangedListener(DecimalTextWatcher(etAltura))
        btCalcular.setOnClickListener {
            calcular()
        }
    }
    //endregion

    //region Calculation
    private fun calcular() {
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()
        val imc = peso / (altura * altura)
        when (imc) {
            in 0.0..18.5 ->
                configuraIMC(imc, R.drawable.masc_abaixo, R.string.magreza)
            in 18.6..24.9 ->
                configuraIMC(imc, R.drawable.masc_ideal, R.string.peso_normal)
            in 25.0..29.9 ->
                configuraIMC(imc, R.drawable.masc_sobre, R.string.sobre_peso)
            in 30.0..34.9 ->
                configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_i)
            in 35.0..39.9 ->
                configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_ii)
            else ->
                configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_iii)
        }
    }

    private fun configuraIMC(imc: Double, drawableId: Int, stringId: Int) {
        tvIMC.text = getString(R.string.resultado_imc, imc)
        ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this, drawableId)
        )
        tvIMCStatus.text = getString(stringId)
    }
    //endregion

}