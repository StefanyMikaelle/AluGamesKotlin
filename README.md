# AluGames

É uma plataforma de alugueis de jogos imagens abaixo de referência para o MVP no backend desenvolvido em Kotlin.


[![Alu-Games-1.png](https://i.postimg.cc/pdr2xqFP/Alu-Games-1.png)](https://postimg.cc/DWVVPQ6N)

[![Alu-Games-2.png](https://i.postimg.cc/tRWxntrX/Alu-Games-2.png)](https://postimg.cc/3WrRPp56)

[![Alu-Games-3.png](https://i.postimg.cc/NFdY2zSJ/Alu-Games-3.png)](https://postimg.cc/QKKvR0X1)    

[![planos.png](https://i.postimg.cc/xdpPGQ2y/planos.png)](https://postimg.cc/5Hvv1Zj6)

[![planos-com-assinatura.png](https://i.postimg.cc/fRw76yWf/planos-com-assinatura.png)](https://postimg.cc/N5zrXgHK)


## Arquitetura

https://whimsical.com/alugames-kotlin-diagrama-de-arquitetura-QFvCmBmgF1ZK9yfF8S2Ly2

## Diagrama de Processo

https://whimsical.com/alugames-kotlin-diagrama-de-processo-PGyzDxD7H88UEJVgVXRrjL

## Telas

https://whimsical.com/alugames-kotlin-telas-7KMoeaHwuxTiuwvKmgYkBN

## Plano de Estudos

[![kotlin.png](https://i.postimg.cc/8k0BxZ8t/kotlin.png)](https://postimg.cc/YjQWWfCW)


ChatGPT

Faça o teste unitário em Junit em Kotlin desse código fun generatePdfArray(ddc: DDCPdfResponse): ByteArray {
        val ddcCpfDocument = ddc.cpfDocument
        val passwordPdf = generatePassword(ddcCpfDocument)
        val rows = arrayListOf(ddc)
        val jsonResource = gson.toJson(rows)
        return handlePrint(template, jsonResource, passwordPdf)
    }
ChatGPT
Para criar um teste unitário em JUnit para a função generatePdfArray em Kotlin, você precisa verificar várias coisas, como o comportamento esperado da função e os diferentes componentes que ela usa. Aqui está um exemplo de como você pode configurar esse teste:

Dependências: Primeiro, certifique-se de que você tem as dependências necessárias em seu arquivo build.gradle.kts para JUnit e Mockito (ou outra biblioteca de mock de sua escolha).
kotlin
Copy code
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.mockito:mockito-core:3.9.0")
    testImplementation("org.mockito:mockito-kotlin:3.2.0")
    testImplementation("com.google.code.gson:gson:2.8.6")
}
Classe de teste: Crie uma classe de teste e configure o ambiente para testar a função generatePdfArray.
kotlin
Copy code
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class YourServiceTest {

    private val gson: Gson = Gson()
    private val template: String = "template"

    // Função mockada para gerar senha
    private fun generatePassword(cpf: String): String {
        return "mockedPassword"
    }

    // Função mockada para lidar com a impressão
    private fun handlePrint(template: String, jsonResource: String, passwordPdf: String): ByteArray {
        return "mockedPdf".toByteArray()
    }

    @Test
    fun `test generatePdfArray`() {
        // Mock do DDCPdfResponse
        val ddc = mock<DDCPdfResponse> {
            on { cpfDocument } doReturn "12345678900"
        }

        // Função que estamos testando
        fun generatePdfArray(ddc: DDCPdfResponse): ByteArray {
            val ddcCpfDocument = ddc.cpfDocument
            val passwordPdf = generatePassword(ddcCpfDocument)
            val rows = arrayListOf(ddc)
            val jsonResource = gson.toJson(rows)
            return handlePrint(template, jsonResource, passwordPdf)
        }

        // Chamada da função a ser testada
        val result = generatePdfArray(ddc)

        // Assertivas para verificar se o resultado não é nulo
        assertNotNull(result)
    }



