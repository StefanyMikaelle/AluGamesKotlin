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



import com.google.gson.Gson
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.fill.JsonQLQueryExecuterFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class PdfComponentTest {

    @Mock
    private lateinit var jasperFillManager: JasperFillManager

    @Mock
    private lateinit var jasperExportManager: JasperExportManager

    @Mock
    private lateinit var gson: Gson

    @InjectMocks
    private lateinit var pdfComponent: PdfComponent

    @Test
    fun `generatePdfArray should return a ByteArray with the generated PDF`() {
        val ddc = DDCPdfResponse("12345678900")
        val template = "DDC_Documento_Descritivo_de_Credito_Final_com_Senha.jasper"
        val passwordPdf = "00987654321" // Reverse of "12345678900"
        val rows = arrayListOf(ddc)
        val jsonResource = """[{"cpfDocument":"12345678900"}]"""
        val mockJasperPrint = Mockito.mock(JasperPrint::class.java)

        whenever(gson.toJson(rows)).thenReturn(jsonResource)
        whenever(jasperFillManager.fillReport(
            Mockito.eq(template),
            Mockito.anyMap(),
            Mockito.any()
        )).thenReturn(mockJasperPrint)

        val byteArrayOutputStream = ByteArrayOutputStream()
        whenever(jasperExportManager.exportReportToPdfStream(
            Mockito.any(JasperPrint::class.java),
            Mockito.any(ByteArrayOutputStream::class.java)
        )).thenAnswer {
            val outputStream = it.getArgument<ByteArrayOutputStream>(1)
            outputStream.write("pdf content".toByteArray())
        }

        val result = pdfComponent.generatePdfArray(ddc)

        assertEquals("pdf content".toByteArray().toList(), result.toList())
    }
}



