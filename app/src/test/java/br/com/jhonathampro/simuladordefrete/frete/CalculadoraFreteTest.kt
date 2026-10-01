package br.com.jhonathampro.simuladordefrete.frete

import br.com.jhonathampro.simuladordefrete.model.TipoFrente
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


class CalculadoraFreteTest {

    private val calculadoraFrete = CalculadoraFrete()
    @Test
    fun `Frete economico deve calcular valor pelo peso e a distancia `(){
        //ARRANGE - Preperar
        val peso = 2.0
        val distanciaKm = 100.0

        //ACT
        val resultado = calculadoraFrete.calcular(TipoFrente.EXPRESSO,
            pesoKg = peso,
            distanciaKm = distanciaKm)

        //ASSERT - Verificar
        assertEquals(
            22.0,
            resultado.valor,
            0.01
        )
    }

    @Test
    fun `peso zero deve gerar erro `(){
        assertThrows(IllegalArgumentException::class.java){
            calculadoraFrete.calcular(
                TipoFrente.ECONOMICO,
                pesoKg = 0.0,
                distanciaKm = 100.0
            )
        }
    }

}