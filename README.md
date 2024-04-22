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




plugins {
	id 'java'
	id 'org.springframework.boot' version '3.2.5'
	id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.stefany'
version = '0.0.1-SNAPSHOT'

java {
	sourceCompatibility = '17'
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
	useJUnitPlatform()
}


import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperReport
import java.io.InputStream

class JasperService {

    private val params: MutableMap<String, Any> = LinkedHashMap()

    fun addParams(key: String, value: Any) {
        params[key] = value
    }

    private fun compilarJrxml(arquivo: String): JasperReport? {
        try {
            val isStream: InputStream? = javaClass.classLoader.getResourceAsStream(arquivo)
            return JasperCompileManager.compileReport(isStream)
        } catch (e: JRException) {
            e.printStackTrace()
        }
        return null
    }
}




import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.view.JasperViewer
import java.sql.Connection

fun abrirJasperViewer(jrxml: String, connection: Connection) {
    val report: JasperReport? = compilarJrxml(jrxml)
    try {
        val print: JasperPrint = JasperFillManager.fillReport(report, params, connection)
        val viewer = JasperViewer(print)
        viewer.isVisible = true
    } catch (e: JRException) {
        e.printStackTrace()
    }
}


import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.view.JasperViewer
import java.io.InputStream
import java.util.HashMap

fun abrirJasperViewer(jrxml: String, params: Map<String, Any>) {
    val report: JasperReport? = compilarJrxml(jrxml)
    try {
        val print: JasperPrint = JasperFillManager.fillReport(report, params, null)
        val viewer = JasperViewer(print)
        viewer.isVisible = true
    } catch (e: JRException) {
        e.printStackTrace()
    }
}

fun compilarJrxml(arquivo: String): JasperReport? {
    try {
        val isStream: InputStream? = object {}.javaClass.classLoader.getResourceAsStream(arquivo)
        return net.sf.jasperreports.engine.JasperCompileManager.compileReport(isStream)
    } catch (e: JRException) {
        e.printStackTrace()
    }
    return null
}

fun main() {
    val params: MutableMap<String, Any> = HashMap()
    params["param1"] = "Valor 1"
    params["param2"] = 123

    abrirJasperViewer("seu_arquivo.jasper", params)
}

