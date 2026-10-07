package br.com.jhonathampro.simuladordefrete.frete

import br.com.jhonathampro.simuladordefrete.model.TipoFrete
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
        val valorCompra = 30.0

        //ACT
        val resultado = calculadoraFrete.calcular(TipoFrete.EXPRESSO,
            pesoKg = peso,
            distanciaKm = distanciaKm,
            valorCompra = valorCompra)

        //ASSERT - Verificar
        assertEquals(
            55.0,
            resultado.valor,
            0.01
        )
    }

    @Test
    fun `peso zero deve gerar erro `(){
        assertThrows(IllegalArgumentException::class.java){
            calculadoraFrete.calcular(
                TipoFrete.ECONOMICO,
                pesoKg = 0.0,
                distanciaKm = 100.0,
                valorCompra = 20.0
            )
        }
    }

    @Test
    fun `distancia negativa deve gerar erro`() {

        assertThrows(IllegalArgumentException::class.java) {
            calculadoraFrete.calcular(
                tipo = TipoFrete.ECONOMICO,
                pesoKg = 2.0,
                distanciaKm = -10.0,
                valorCompra = 20.0
            )
        }
    }

    @Test
    fun `compra de 350 deve possuir frete gratis no Tipo de Frete Economico`() {

        val resultado = calculadoraFrete.calcular(
            tipo = TipoFrete.ECONOMICO,
            pesoKg = 2.0,
            distanciaKm = 100.0,
            valorCompra = 350.0
        )

        assertEquals(
            0.0,
            resultado.valor,
            0.01
        )
    }

}