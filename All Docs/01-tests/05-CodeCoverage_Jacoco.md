# LibraryAPI - Java Code Coverage - Jacoco

[TOC]

## Cobertura de código (Code Coverage)

Métrica para medir a quantidade de código testado nos teste. O objetivo, demonstrar de % o quanto de código foi testado e aprovado. Há ferramentas que dizem de forma gráfica ou numérica a porcentagem e partes do nosso código que foi testada. Usaremos o plugin JaCoCo (Java Code Coverage Library)

Exemplo de dependência

````xml
<!-- Plugin Jacoco (Java Code Coverage) -->
<plugin>
	<groupId>org.jacoco</groupId>
	<artifactId>jacoco-maven-plugin</artifactId>
	<version>0.8.2</version>
	<executions>
		<execution>
			<goals>
				<goal>prepare-agent</goal>
			</goals>
		</execution>
		<execution>
			<id>report</id>
			<phase>test</phase>
			<goals>
				<goal>report</goal>
			</goals>
		</execution>
	</executions>
</plugin>
````

Ao gerar o compile vai gerar dentro de `target/site/jacoco/index.html` vai gerar um relatório em HTML

Ele mostra-rá uma porcentagem  para cada arquivo/pacote e também as linhas que foram testadas ou não.

## Exluindo classes e pacotes

No nosos projeto adicionando o seguinte fragmento de xml para o library-api

````xml
<configuration>
	<excludes>
	  <!-- Exclui packages da parte de dados, pois o que nâo é testado é 
			coisas como 'ToString(), Equals() e etcc gerados pelo lombok -->
	  <exclude>br/com/rafanthx13/libraryapi/data/dto/**/*</exclude>
	  <exclude>br/com/rafanthx13/libraryapi/data/entity/**/*</exclude>
	</excludes>
</configuration>
````
**Mais exemplos de como remover certas classe/pacotes**

> https://ngeor.com/2018/04/21/exclude-class-from-jacoco-coverage.html

````xml
<plugin>
  <groupId>org.jacoco</groupId>
  <artifactId>jacoco-maven-plugin</artifactId>
  <version>0.8.5</version>
  <configuration>
    <excludes>
      <!-- exclude classes in package com.acme.models whose name starts with Spring -->
      <exclude>com/acme/models/Spring*</exclude>
      <!-- exclude classes in package com.acme.api whose name ends with Api -->
      <exclude>com/acme/api/*Api.class</exclude>
      <!-- exclude all classes in package com.acme.generated -->
      <exclude>com/acme/generated/**/*</exclude>
    </excludes>
  </configuration>
</plugin>
````


## Configurando CodeCov para dar a badge

Quando o Travis fizer o build, vai gerar a badge para esse codeCov

1. Faça o login no https://codecov.io/ e inscreva seu git lá
2. Buscando seu repo ele vai gerar uma chave para colocar nas configurações do CI (Continuos Integration). Você pega a chave e o valor e coloca nas 'Enviroment Variable' do Travis e aí 

Adiciona no `travis.yaml`

link: https://github.com/codecov/example-java

````
after_success:
 - bash <(curl -s https://codecov.io/bash)
````

Esse exemplo serve para o seguinte codigo jacoco

Depois de executar dinovo, vai em Sethings > Badges e pega a badge e da pra ver o link do relatório do codCov.
