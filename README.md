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


import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ReportServiceTest {

    @Mock
    private lateinit var pdfComponent: PdfComponent

    @Mock
    private lateinit var util: GenerateOficialData

    @InjectMocks
    private lateinit var reportService: ReportService

    @Test
    fun `generate should return ByteArray from PdfComponent`() {
        val mockDdCPdfResponse = DDCPdfResponse("12345678900")
        val mockPdfByteArray = "pdf content".toByteArray()

        whenever(util.generateDataPdf()).thenReturn(mockDdCPdfResponse)
        whenever(pdfComponent.generatePdfArray(mockDdCPdfResponse)).thenReturn(mockPdfByteArray)

        val result = reportService.generate()

        assertEquals(mockPdfByteArray.toList(), result.toList())
    }
}




