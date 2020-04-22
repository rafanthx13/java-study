# LibraryAPI - Monitorar Aplicação: `actuator`, logfile e SpringBootAdmin

[TOC]

## Spring Boot Actuator:  Monitorar Aplicação

### O que é Actuator

>  https://medium.com/@gcbrandao/monitore-sua-api-com-spring-boot-admin-e-actuator-40e73a5e50b0

Hoje vamos ver uma solução bem simples e fácil de configurar de como podemos usar o poder do Framework Spring para termos um monitoramento e gerenciamento de nossas API’s. Principalmente se você tiver dezenas de clientes ou estiver trabalhando com a arquitetura de microserviços, monitorar o funcionamento dessas api’s pode se tornar uma tarefa impossível.

Para resolver esse problema o pessoal do SpringBoot criou um projeto chamado actuator. E vamos nesse artigo aprender a usar esse projeto.
O Spring Boot Actuator está disponível desde abril de 2014, juntamente com o primeiro lançamento do Spring Boot. Com o lançamento do Spring Boot 2, o Actuator foi redesenhado e novos endpoints interessantes foram adicionados.

O que é o Actuator?

A principio o Actuator traz alguns recursos prontos para serem usados em produção para nossa aplicação. Monitorar nossa aplicação, coletar métricas, entender o tráfego ou o estado do nosso banco de dados se torna facil trivial com essa dependência. O principal benefício desta biblioteca é que podemos obter ferramentas de monitoramento de nível de produção sem precisar implementar esses recursos pois eles ja vem prontos com o Spring Boot.

### Como usar Actuator

Monitorar: Threads, se ela está de pé.

````xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
````

Depois disso é necessário

`/resources/application.properties`

````properties
#indica que health deve mostrar detalhes sempre
management.endpoint.health.show-details=always

#alem do health e do info ele sempre vai export todos endpoints disponiveis
management.endpoints.web.exposure.include=*
````

Onde acessar: *http://localhost:8080/actuator*

**O que faz:** Retorna um JSON com algumas configurações de deploy e de funcionamento

Isso vai gerar um monte de dados, mais que o anterior.

Vai mostra tudo que está sendo rodado pelo spring no momento

## Inserir logFile para registra log

Em `application.properties`, eu adiciono

````properties
logging.file=appfile.log
````

Assim vai gerar os logs no arquivo `appfile.log` e vocÊ pode acessar esse arquivo pelo actuator através de: 
http://localhost:8080/actuator/logfile

Acessando o Actuator, ele vai adicionar um atributo no JSON de logfile, acessando ela você acessa o logfile que você criou. Esses dados serão fixados em um arquivo.

### Adicionar Logs manuais durante a execução de nossa API

O lombok já tem notação para inserir informações em Log: Slf4j

**Slf4j**: Simple Logging Facade for Java (abbreviated SLF4J) – acts as a facade for different logging frameworks (e.g. java.util.logging, logback, Log4j). It offers a generic API making the logging independent of the actual implementation.

`@Slf4j`
+ Ao inserir essa notação no controller, podemos acessar o objeto `log` e inserir dados no logFile.

````java
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BookController {
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a book")
    public BookDTO create( @RequestBody @Valid BookDTO dto ){
        log.info(" creating a book for isbn: {} ", dto.getIsbn());
        Book entity = modelMapper.map( dto, Book.class );
        entity = service.save(entity);
        return modelMapper.map(entity, BookDTO.class);
    }
}
````


## Spring Boot Admin: Monitorar com GUI

Praticamente fazer o passo a passo do link abaixo

> https://medium.com/@gcbrandao/monitore-sua-api-com-spring-boot-admin-e-actuator-40e73a5e50b0

### O que é Spring Boot Admin

O [***Spring Boot Admin\***](https://github.com/codecentric/spring-boot-admin) é um aplicativo Web, usado para gerenciar e monitorar aplicativos Spring Boot. Cada aplicativo é considerado como um *cliente* e se registra no servidor de administração do ***Spring Boot Admin\***. Nos bastidores, a mágica é feita pelos endpoints do *Spring Boot Actuator*. Veremos agora as etapas para configurar um servidor Spring Boot Admin e como o nosso aplicativo acima se torna um cliente.

Quem desenvolveu essa ferramenta foi uma empresa alemã chamada **codecentric’s**. É uma aplicação web, usando Spring Boot, que consegue consumir e monitorar outras aplicações que estejam usando o Spring Boot, com o *Spring Boot actuator* ativado. Ele te fornece informações sobre jvm, data source, acesso ao banco de dados, hd, informações sobre as requisições web, consegue até te enviar alertas por e-mail, integrando com outras ferramentas.

**Resumo:** Agente não adiciona ele à aplicação. Agente sobe esse admin separado e ele vai se inscrever em outras app spring que estão rodando e vai monitora-las.

### Como aplicar Spring Boot Admin

#### Serve: SpringBootAdmin Project para monitorar

Cria projeto [ Spring Initalizr ](https://start.spring.io/)  com dependência web e com as seguintes dependências

```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

	<!-- SpringBootAdmin Server Dependency -->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.2.0</version>
    </dependency>

</dependencies>
```

Na aplicação main: coloque

```java
// SpringBootAdminApplication.java

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class SpringBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }

}
```

Como nosso projeto de testes já esta configurado para rodar na porta padrão 8080 vamos configurar esse servidor para rodar na *porta 8081,* para isso vamos incluir a instrução `server.port=8081` no arquivo `application.properties` que fica na pasta `resources`.

```properties
# application.properties
server.port=8081
```

E depois, **comente o arquivo de Test** que vem por padrão em um projeto Spring/Maven. Poi senão vai acusar ao dar o `mvn install`

#### Client: Minha Aplicação a ser monitorada

Na aplicação que será gerenciada, devemos colocar a seguinte dependência para ser gerenciada pelo SpringBootAdmin.

````xml
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-client</artifactId>
	<version>2.2.0</version>
</dependency>
````
alem disso, coloca a seguinte configuração no client em `application.proprietis`

```properties
# application.properties
spring.boot.admin.client.url=http://localhost:8081/
```

Agora eu devo rodar as duas; 

Para acessar o SpringBootAdmin: acesse por : http://localhost:8081/. Por lá você vai ter acesso a sua aplicação spring e a todas as outras que estiverem rodando

### Vai mostrar coisas como ...

+ Beans subidos
+ LogFile
+ Schefule
+ Mapeamento do sistema: incluindo `actuator` e `swaager`

É uma interface gráfica bem rica que monitora todas as aplicações Spring Boot
