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



Se os arquivos `.jrxml` estiverem no diretório `resources`, você precisará acessar esses arquivos a partir do classpath em vez de usar caminhos de arquivo direto. Isso é necessário porque, ao rodar a aplicação, os recursos são empacotados no JAR e devem ser acessados de forma diferente.

### Passo 1: Adicione as dependências no seu `build.gradle.kts`

Assegure-se de que seu `build.gradle.kts` contenha as dependências necessárias:

```kotlin
plugins {
    kotlin("jvm") version "1.5.31"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.sf.jasperreports:jasperreports:6.19.0") // Verifique se há uma versão mais recente
}

application {
    mainClass.set("com.seuprojeto.MainKt")
}
```

### Passo 2: Coloque o arquivo `.jrxml` no diretório de recursos

Coloque seu arquivo `.jrxml` na pasta `src/main/resources`.

### Passo 3: Crie um script Kotlin para compilar o arquivo `.jasper`

No seu diretório `src/main/kotlin`, crie um arquivo Kotlin, por exemplo, `JasperCompile.kt`:

```kotlin
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperCompileManager
import java.io.File
import java.io.InputStream

fun main() {
    val inputFileName = "relatorio.jrxml"
    val outputFileName = "src/main/resources/relatorio.jasper"
    
    val inputStream: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(inputFileName)
    if (inputStream == null) {
        println("Arquivo .jrxml não encontrado!")
        return
    }
    
    try {
        // Compila o relatório a partir do InputStream
        val jasperReport = JasperCompileManager.compileReport(inputStream)
        // Salva o relatório compilado em um arquivo .jasper
        JasperCompileManager.writeReportToXmlFile(jasperReport, outputFileName)
        println("Arquivo .jasper gerado com sucesso!")
    } catch (e: JRException) {
        e.printStackTrace()
    }
}
```

### Passo 4: Execute o script de compilação

Você pode configurar uma task do Gradle para executar o script de compilação. Adicione a task no seu `build.gradle.kts`:

```kotlin
tasks.register("compileJasper") {
    doLast {
        javaexec {
            mainClass.set("com.seuprojeto.JasperCompileKt")
            classpath = sourceSets["main"].runtimeClasspath
        }
    }
}
```

Para executar a task, use:

```sh
./gradlew compileJasper
```

### Passo 5: Verifique a saída

Após executar a task, verifique se o arquivo `relatorio.jasper` foi gerado na pasta `src/main/resources`.

### Considerações Finais

- Assegure-se de que os nomes dos arquivos e caminhos estejam corretos.
- `Thread.currentThread().contextClassLoader.getResourceAsStream` é usado para acessar arquivos no classpath. Certifique-se de que seu arquivo `.jrxml` esteja na pasta `src/main/resources` para que seja incluído corretamente no classpath.

Seguindo esses passos, você conseguirá compilar arquivos `.jasper` a partir de arquivos `.jrxml` que estão na pasta `resources` usando Kotlin e Gradle.

