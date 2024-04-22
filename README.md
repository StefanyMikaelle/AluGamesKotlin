# AluGames

É uma plataforma de alugueis de jogos imagens abaixo de referência para o MVP no backend desenvolvido em Kotlin.


[![Alu-Games-1.png](https://i.postimg.cc/pdr2xqFP/Alu-Games-1.png)](https://postimg.cc/DWVVPQ6N)

[![Alu-Games-2.png](https://i.postimg.cc/tRWxntrX/Alu-Games-2.png)](https://postimg.cc/3WrRPp56)

[![Alu-Games-3.png](https://i.postimg.cc/NFdY2zSJ/Alu-Games-3.png)](https://postimg.cc/QKKvR0X1)    

## Arquitetura

https://whimsical.com/alugames-kotlin-diagrama-de-arquitetura-QFvCmBmgF1ZK9yfF8S2Ly2

## Diagrama de Processo

https://whimsical.com/alugames-kotlin-diagrama-de-processo-PGyzDxD7H88UEJVgVXRrjL

## Telas

https://whimsical.com/alugames-kotlin-telas-7KMoeaHwuxTiuwvKmgYkBN


data class Person(val name: String, val age: Int)


package com.example.controller

import net.sf.jasperreports.engine.JREmptyDataSource
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.io.File
import java.io.FileOutputStream

@Controller
class ReportController {

    @GetMapping("/generate-report")
    fun generateReport(): String {
        // Caminho para o arquivo .jrxml
        val jrxmlFile = File("path/to/your/report/funcionarios.jrxml")

        // Compilar o arquivo .jrxml para .jasper
        val jasperFile = JasperCompileManager.compileReportToFile(jrxmlFile.absolutePath)

        // Criar alguns dados mockados
        val data = mapOf(
            "person1" to Person("Alice", 30),
            "person2" to Person("Bob", 25),
            "person3" to Person("Charlie", 35)
        )

        // Preencher o relatório com dados mockados
        val jasperPrint = JasperFillManager.fillReport(jasperFile, null, JREmptyDataSource())

        // Exportar o relatório para PDF
        val outputFile = File("path/to/your/report/funcionarios.pdf")
        JasperExportManager.exportReportToPdfStream(jasperPrint, FileOutputStream(outputFile))

        return "reportGenerated"
    }
}


# Caminho para o arquivo .jrxml
report.jrxml.path=path/to/your/report/funcionarios.jrxml
# Caminho para o arquivo .pdf
report.pdf.path=path/to/your/report/funcionarios.pdf


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Report Generated</title>
</head>
<body>
    <h1>Report Generated Successfully!</h1>
</body>
</html>





