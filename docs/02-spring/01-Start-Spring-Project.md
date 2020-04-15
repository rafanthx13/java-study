# Start Spring Project

## Como iniciar um Projeto Spring

Simplificando: Um projeto Spring é um Projeto que tem como gerenciador de dependencia o maven/gradle (tipo npm do node/js só que pra java) que já vem com a dependência do Spring. 

Em regra, acaba-se usando mais o maven que é um gerenciador de dependencias. Suas dependências não ficam numa pasta como `node_modules/` mas sim na pasta raiz `.m2`.

Vamos considera, para simplificar, sempre considerar como um projeto maven

## Pelo `spring.io`

(Spring Initialz)[https://start.spring.io/]

**groupId**: Algo como `com.br.rafanthx13`
**artifactID**: Algo como `first-project` (se colocar '-' vai ficar junto nas pastas)

Em geral coloque as dependências de `Spring Boot DevTools DEVELOPER` e `Spring Web`

Dê um start/run de inicio para o vsCode/eclipse/IntelliJ baixar mais alguma coisa necessária. Teste na porta 8080

A classe principal depois pode ser mudada para `Main/App/Application`

## Executar em linha de comando

http://www.appsdeveloperblog.com/run-spring-boot-app-from-a-command-line/

`mvn install`

e depois

`mvn spring-boot:run`

## Spring POM básico

````xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.6.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>br.com.rafanthx13</groupId>
	<artifactId>FirstSpringProject</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>FirstSpringProject</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
	</properties>

	<dependencies>
		<!-- Starter do Basico do Spring Boot -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<!-- Starter da parte Web do Spring Boot -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			
		</plugins>
	</build>

</project>

````

## O básico para subir aplicaâo Spring

````java
package br.com.rafanthx13.FirstSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringProjectApplication.class, args);
	}

}
````

### Criando um controlador REST

`@RestController`

Vai fazer com que a classe designada trate métodos REST.

Ela já trata o 'retorno' como JSON. Ele vai converter em JSON

**CONTROLLER**

````java
//Nao esta completmante , falata a parte do service
package com.cursodsousa.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // sem isso nao funciona
@RequestMapping("/api/clientes") // define como base
public class MeuResource {

    @PostMapping // Define como Método POST
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente ){
        //service.save(cliente);
        return cliente;
		// Tem mais features amais, pode retornarar Headers HTTP
		return new REsponseENtity(cliente, HTTpStataus.CREATED)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Long id ){
        //service.buscarPorId(id);
        //service.delete(cliente);
    }

    @PutMapping("{id}")
    public Cliente atualizar( @PathVariable Long id, @RequestBody Cliente cliente ){
        //service.buscaPorId(id);
        //service.update(cliente);
        return cliente;
    }

    @GetMapping("{id}")
    public Cliente obterDadosCliente( @PathVariable Long id ){
        System.out.println(String.format("Id recebido via url: %d", id));
        Cliente cliente = new Cliente("Fulano", "000.000.000-00");
        return cliente;
    }
}
````

**MODEL**

````java
package com.cursodsousa.api;

public class Cliente {

    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
```
