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
            valorCompra = valorCompra,
            cupom = "")

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
                valorCompra = 20.0,
                cupom = ""
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
                valorCompra = 20.0,
                cupom = ""
            )
        }
    }

    @Test
    fun `compra de 350 deve possuir frete gratis no Tipo de Frete Economico`() {

        val resultado = calculadoraFrete.calcular(
            tipo = TipoFrete.ECONOMICO,
            pesoKg = 2.0,
            distanciaKm = 100.0,
            valorCompra = 350.0,
            cupom = ""
        )

        assertEquals(
            0.0,
            resultado.valor,
            0.01
        )
    }

    @Test
    fun `compra exatamente de 300 deve possuir frete gratis`() {

        val resultado = calculadoraFrete.calcular(
            tipo = TipoFrete.ECONOMICO,
            pesoKg = 2.0,
            distanciaKm = 100.0,
            valorCompra = 300.0,
            cupom = ""
        )

        assertEquals(
            0.0,
            resultado.valor,
            0.01
        )
    }

    @Test
    fun `compra abaixo de 300 deve pagar frete`() {

        val resultado = calculadoraFrete.calcular(
            tipo = TipoFrete.ECONOMICO,
            pesoKg = 2.0,
            distanciaKm = 100.0,
            valorCompra = 299.99,
            cupom = ""
        )

        assertEquals(
            24.0,
            resultado.valor,
            0.01
        )
    }


    @Test
    fun `cupom FRETEGRATIS deve zerar o frete`() {

        val resultado = calculadoraFrete.calcular(
            tipo = TipoFrete.ECONOMICO,
            pesoKg = 2.0,
            distanciaKm = 100.0,
            valorCompra = 100.0,
            cupom = "FRETEGRATIS"
        )

        assertEquals(
            0.0,
            resultado.valor,
            0.01
        )
    }


    @Test
    fun `cupom FRETE50 deve aplicar cinquenta porcento de desconto`() {

        val resultado = calculadoraFrete.calcular(
            tipo = TipoFrete.ECONOMICO,
            pesoKg = 2.0,
            distanciaKm = 100.0,
            valorCompra = 100.0,
            cupom = "FRETE50"
        )

        assertEquals(
            12.0,
            resultado.valor,
            0.01
        )
    }

}