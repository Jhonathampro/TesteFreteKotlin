package br.com.jhonathampro.simuladordefrete.frete

import br.com.jhonathampro.simuladordefrete.model.ResultadoFrete
import br.com.jhonathampro.simuladordefrete.model.TipoFrete

class CalculadoraFrete {

    fun calcular(
        tipo: TipoFrete,
        pesoKg: Double,
        distanciaKm: Double,
        valorCompra: Double

        // A função calcular precisa retornar um objeto do tipo ResultadoFrete
    ) : ResultadoFrete {

        require(pesoKg > 0) {
            "O peso deve ser maior que zero"
        }

        require( distanciaKm >= 0) {
            "O peso deve ser maior que zero"
        }
        val resultado = when(tipo){

            TipoFrete.ECONOMICO -> ResultadoFrete(
                valor = 8 + pesoKg * 3  + distanciaKm * 0.10,
                prazoDias = 5
            )

            TipoFrete.EXPRESSO -> ResultadoFrete(
                valor = 20 + pesoKg * 5  + distanciaKm * 0.25,
                prazoDias = 2
            )

            TipoFrete.RETIRADO -> ResultadoFrete(
                valor = 0.0,
                prazoDias = 1
            )
        }
        if(valorCompra >= 300){
           return resultado.copy(valor = 0.0)
        }
        return resultado

    }

}