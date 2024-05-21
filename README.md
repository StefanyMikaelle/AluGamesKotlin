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




import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfStamper
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream

fun main() {
    try {
        // Caminho do arquivo .jasper
        val jasperReportPath = "caminho/para/seu/relatorio.jasper"
        // Caminho do PDF gerado
        val generatedPdfPath = "caminho/para/seu/relatorio.pdf"
        // Caminho do PDF protegido
        val protectedPdfPath = "caminho/para/seu/relatorio_protegido.pdf"
        
        // Parâmetros do relatório
        val parameters = mapOf<String, Any>()
        
        // Fonte de dados do relatório (exemplo com lista vazia)
        val dataSource = JRBeanCollectionDataSource(emptyList<Any>())
        
        // Preenchimento do relatório
        val jasperPrint: JasperPrint = JasperFillManager.fillReport(jasperReportPath, parameters, dataSource)
        
        // Exporta para PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, generatedPdfPath)
        
        // Senha de usuário
        val userPassword = "senhaUsuario"
        // Senha de proprietário (pode ser usada para alterar permissões)
        val ownerPassword = "senhaProprietario"
        
        // Protege o PDF com senha usando iText
        val reader = PdfReader(generatedPdfPath)
        val stamper = PdfStamper(reader, FileOutputStream(protectedPdfPath))
        stamper.setEncryption(userPassword.toByteArray(), ownerPassword.toByteArray(),
                PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128)
        stamper.close()
        reader.close()
        
        println("PDF protegido com sucesso.")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
