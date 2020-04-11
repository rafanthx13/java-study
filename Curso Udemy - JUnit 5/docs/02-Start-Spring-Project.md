# Start Spring Project

## Como iniciar um Projeto Spring

Simplificando: Um projeto Spring é um Projeto que tem como gerenciador de dependencia o maven/gradle (tipo npm do node/js só que pra java) que já vem com a dependência do Spring. 

Em regra, acaba-se usando mais o maven que é um gerenciador de dependencias. Suas dependências não ficam numa pasta como `node_modules/` mas sim na pasta raiz `.m2`.

Vamos considera, para simplificar, sempre considerar como um projeto maven

## Pelo `spring.io`

(Spring Initialz)[https://start.spring.io/]

**groupId**: Algo como `com.br.rafanthx13`
**artifactID**: Algo como `first-project` (se colocar '-' vai ficar junto nas pastas)

Em geral coloque as dependnecias de `Spring Boot DevTools DEVELOPER` e `Spring Web`

Dê um start/run de inicio para o vsCode/eclipse/IntelliJ baixar masi alguma coisa necessária. Teste na porta 8080

A classe principal depois pode ser mudada para `Main/App/Application`

## Executar em linha de comando

http://www.appsdeveloperblog.com/run-spring-boot-app-from-a-command-line/

`mvn install`

e depois

`mvn spring-boot:run`
