package br.com.jhonathampro.simuladordefrete.frete

import br.com.jhonathampro.simuladordefrete.model.ResultadoFrete
import br.com.jhonathampro.simuladordefrete.model.TipoFrente

class CalculadoraFrete {

    fun calcular(
        tipo: TipoFrente,
        pesoKg: Double,
        distanciaKm: Double
    ) : ResultadoFrete {

        val resultado = when(tipo){

            TipoFrente.ECONOMICO -> ResultadoFrete(
                valor = 8 * 3 * pesoKg * 0.10 * distanciaKm,
                prazoDias = 5
            )

            TipoFrente.EXPRESSO -> ResultadoFrete(
                valor = 20 * 5 * pesoKg + 0.25 * distanciaKm,
                prazoDias = 2
            )

            TipoFrente.RETIRADO -> ResultadoFrete(
                valor = 0.0,
                prazoDias = 1
            )
        }
        return resultado

    }

}