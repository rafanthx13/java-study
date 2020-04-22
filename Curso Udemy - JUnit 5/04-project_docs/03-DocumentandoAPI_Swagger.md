# LibraryAPI - Documentado API - Swagger

## Documentando API (Swagger em JAVA)

**Porque documentar API:** Isso ajuda os devs que entram no projeto ou pra quem consumir a API, principalmente para front-end para conhecer o que enviar e o que receber.

Dependências a serem inseridas

````xml
<!-- Swagger -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.6.1</version>
</dependency>

<!-- Interface Gráfica em HTML -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.6.1</version>
</dependency>
````



## Configurando e Subindo o `swagger`

````java
@EnableSwagger2 // Habilitar Swagger
@Configuration // Para o springBoot reconhecer que essa é uma classe de configração
public class SwaggerConfig {

    // Docket é arquivo para configuração do Swagger. 
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2) 
            .select() 
            .apis( RequestHandlerSelectors.basePackage("br.com.rafanthx13.libraryapi.controller") 
                 ) 
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo()); // Inserindo as informações
    }

    // 'ApiInfo' Objeto que tem as informações básicas. Inserido no Docket
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("Library API")
            .description("API do Projeto de controle de aluguel de livros")
            .version("1.0")
            .contact(contact())
            .build();
    }

    // 'Contact' tem as informaçôes de contato. Inserida em ApiInfo
    private Contact contact(){
        return new Contact("Douglas Sousa",
                "http://github.com/dougllasfps",
                "cursodsousa@gmail.com");
    }
}
````

Criará uma página HTML junto com a API ao subir o projeto

**Para acessar UI do swagger:**  *localhost:8080/swagger-ui.html*

## Customizando Swagger

Podemos usar colocar algumas notações nos controllers customizar o swagger.

**Notações**

`@Api("Book API") // Titulo para a API`

+ Colocado na classe do @Controller

`@ApiOperation("Create a book")`
+ Colocado no método do controller que faz a `req` HTTP.
+ Descreve o que a ação do método faz

`@ApiResponses({@ApiResponse(code = 204, message = "Book succesfully deleted")})`
+ Colocado nos métodos do controller
+ No `swagger` será a `Reason` dos códigos, ou seja, o sentido de receber o código. 
  - Por exemplo, sabemos que ao fazer um delete e der certo volta um 204 (NO\_CONTENT) mas podemos colocar uma `reason` no Swagger para especificar melhor que ao retornar esse código é NO\_CONTENT mas porque "O livro foi devidamente deletado".

