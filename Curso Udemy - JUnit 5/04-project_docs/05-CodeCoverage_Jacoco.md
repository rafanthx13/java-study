# LibraryAPI - Java Code Coverage - Jacoco

[TOC]

## Cobertura de código (Code Coverage)

Métrica para medir a quantidade de código testado nos teste. O objetivo, demonstrar de % o quanto de código foi testado e aprovado. Há ferramentas que dizem de forma gráfica ou numérica a porcentagem e partes do nosso código que foi testada. Usaremos o plugin JaCoCo (Java Code Coverage Library)

Exemplo de dependência

````xml
<!-- Plugin Jacoco de Java Code Coverage -->
<!-- Quando executar mvn test, vai gerar o 'report' -->
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
