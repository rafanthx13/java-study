# Curso Udemy: Design de API's RestFul com Spring Boot, TDD e o novo JUnit5

**Descrição:** API's RestFul de alto nível com Spring Boot utilizando TDD e o novo JUnit 5.

**Link:** https://www.udemy.com/course/design-de-apis-restful-com-tdd-spring-boot-e-junit-5/

**O que você aprenderá:**
+ Criar aplicações do zero com Spring Boot
+ Desenvolver utilizando técnicas TDD e BDD
+ Desenvolver uma arquitetura de aplicação em camadas
+ Cobertura de Código
+ Integração do Github com Codecov e Travis CI
+ Modelar e desenvolver API's RestFUL, utilizar adequadamente os métodos HTTP, códigos de status, etc
+ Criar serviço de agendamento de tarefas e envio de emails com o Java
+ Criar testes unitários e de integração com JUnit 5, AssertJ, Mockito, etc
+ Documentar a API's com Swagger
+ Configuração de Deploy Contínuo
+ Gerar o build da aplicação e fazer o deploy de sua API na nuvem

## Descriçâo do Projeto Realizado

### API REST Library

Livraria para cadastro e empréstimo de livros

+ Controllers: Implementa a primeira camada do REST, REcebe as solicitações. Testes Unitários

+ Services: IMplementadaos regras de negócio. Testes Unitários

+ REpository: Cmada de modelo e acesso ao BD. Testse de INtegração

## Estrutura de pastas

dto (Data transfer object)
+ Representa o JSON de uma entidade. É o que é recebido e enviado de volta pelo controller. EM geral, acaba sendo convertido par auma `entity` que em memória é o que vai ser salvo na banco ou trabalhado
+ Representa qualquer tipo de JONS ao ser mandao pra Base, como por exemplo ReturnedLoanDTO que só tem uma única ṕropriedade para fazer o path (atualizar) um único campo
+ Porque usar DTO
````
Data Transfer Object (DTO) ou simplesmente Transfer Object é um padrão de projetos bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias ou processos de um sistema distribuído ou diferentes sistemas via serialização.

A ideia consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.

Numa chamada remota, seria ineficiente passar cada atributo individualmente. Da mesma forma seria ineficiente ou até causaria erros passar uma entidade mais complexa.

Além disso, muitas vezes os dados usados na comunicação não refletem exatamente os atributos do seu modelo. Então, um DTO seria uma classe que provê exatamente aquilo que é necessário para um determinado processo.

Em alguns casos, usa-se o DTO ou TO para mapear informações obtidas do banco de dados e então usar numa View (MVC). Isso não é completamente errado, e até pode otimizar a apresentação dos dados, afinal o Controller já recebe as informações prontas para uso. Entretanto, isso pode também acabar em um modelo muito poluído com informações redundantes.

Quando tenho um domínio bem estruturado, prefiro criar beans que representam esse modelo. Esses beans geralmente são chamados de Entidades. Aí, em determinados casos (como em certas pesquisas no banco de dados baseadas em views ou que possuem joins ), crio um tipo de TO ou DTO para facilitar o transporte desses dados.
```

entity
+ Estrutura gerenciar uma entidade na aplicaçâo java. Geralmente é convertida do dto quando chega a req e geralmemnte vira uma dto ao sair para o response
+ Não mandasmo entity para o cliente para nâo exibir certas propriedadea. Observe os "Get filted" para ver como é a conversão

data.respotirry
+ ENtidade para fazer acesso ao banco
+ Feita de interfaces que implementam  'JpaREpository' que recebem por aprametos na sua definiçao o tipo deo objeto do banco e o tipo de seu ID.
+ Sâo interfaces e nâo precisam de implementaçâo pois o JPA já faz isso

service
+ interface com os métodos a serem implementados

service.impl
+ classe que implementa um service, é nela que havera impleemntaçâo de métodos. Posusi internamente o repository para fazer as operações no banco

controller
+ Repsonde as req e dá a res
+ ApplicationControllerAdvice.java
    - Esses métodos serâo globais para toda a API
	- Tem @RestControllerAdvice: (Essa classe tem configuraçêos globais para todos os controllers) Permite inserirr configs a todos os controllers. Nele colocamoms @ExceptionHandler, ou seja, tratamos de exceptions que darem no Controller

----

TESTS

ControllerTest
+ Testa a chamada da URL. Faz HTTP Request. Passa-se DTO. Faz MOck do service

ServiceTest
+ Testa o Service. Não faz HTTP Request, é como se já estive-se dentro do backEnd. Passa-se a entidade @JPA. Faz Mock de repository

REpositoryTest
+ Testa Repository. Nâo faz HTTP REQUEST. Usa-se Entity @JPA
+ Usa o banco H2 ; Notaçâo @DataJpaTest. Agente faz CREATE, UPDATE, DELETE, READ direto desse banco e testamos a nossa entidade de repository pra saber se TUDO QUE O REPOSITORY FAZ ESTÁ FAZENDO CORRETMANETE DEPOIS QUE ELE FAZ.


Famos assim pois, ao fazer o TDD de um Controller, por exemplo. Eu nâo preciso implementar o service, apenas simular com o MOck. Isso faz com que **CADA TESTE DE CADA TIPO (CONTROLLER/SERVICE/REPOSITORY) FIQUEM BEM SEPARADOS E INDEMEPENTES. ASSIM, QUANDO EU FAÇO O TEST DE CONTROLLER EU NÂO PRECIO DO SERVICE/REPOSITORY FUNCIONANDO 100% IMPLEMENTADO, PRECISO QUE APENAS EXISTA COM OS MÉTODOS (DEIXAR SOMENTE COM AS ASSINATURAS DA INTERFACE). ASSIM AS COISAS FICÃO BEM SEPARADAS NÂO DEPENDENDO DA IMPLEMENTAÇÂO DOS OUTROS TIPOS PARA FAZER SEU PRÓPRIO TESTE**.

Assim, para fazer um COntrollerTest, você só precisa está implementado O respectivo controller, nao presia do service e nem do repository, pois estse sâo mockados. Asism, permite o deenolviemtno do TDD fazendo

ControllerTest -> Controller
ServiceTest -> Service e ServiceImpl
REpositoryTest -> Repository

Os testes do Repsoitory sao os úncios que relamente usa o banco, mas o banco a ser usado é soemtne um banco dememória
---

# OUTRAS COISAS QUE VOU COLOCAR AQUI POR ENQUNATO

## Lombok
obs: precisa de plugin

````
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
````
OBS: Lombok é uma dependeic amas tamme é um plug-in no inteliJ. Isos tem que ser colado porque ele gera métodos em tempo de execuçâo, entâo, se nao colcoar o plugin, vai acusar porque vc vai chamar metodos que nao exsitem, mas que so existem no lobok.

Deve-se instalar um plugin na IDE que vai fazer o build, senao da erro de sintaxe e de compilaçÂo tambem (Sei que existe para VScode (extensao) e INteliJ (plugin))

@Data
-> cria getres, setre, toString e isEquals e HasCode
@Getter
@Setter
@Builder
-> Gera um método que permite setar os dados de forma mais legível
-> coisas como `Book.builder().id(10l).author("Artur").title("As aventuras").isbn("001").build();`
@NoArgsContructor
@AllArgsContructor

#### @RequiredArgsConstructor

Com essa notaçâp, nao precisa fazer o contrutor da classe nela. Já vai ta tudo pronto
`@RequiredArgsConstructor`

Isso pode ser convertido nisso
````
  // BookController.java
  private BookService service;
  private ModelMapper modelMapper;

  public BookController(BookService service, ModelMapper modelMapper){
    this.service = service;
    this.modelMapper = modelMapper;
  }
````

````
@RequiredArgsConstructor // Com esas notação: LoanService, BookService e ModelMapper já vao ser criados e injetados
public class LoanController {

	private final LoanService service;
    private final BookService bookService;
    private final ModelMapper modelMapper;
````


## ModelMapper

http://modelmapper.org/getting-started/

Mapeia de uma classe para outra quando tem atributo muito aprecidos

````
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>2.3.0</version>
</dependency>
````

Para usar temos que crirar na main

````
	// O spring vai gera um único Singleton apra toda a aplicação
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
````

Depois costuma-se usar nos Controllers. COmo no exemplo abaixo: converto de dto (dto representa o json da entidade) em uma entidade (entity é a entidade em memoria) e depois converto de volta na saida


````java
@RestController // Controlador Rest
@RequestMapping("/api/book") // Base URL
public class BookController {

  private BookService service;
  private ModelMapper modelMapper;

  public BookController(BookService service, ModelMapper modelMapper){
    this.service = service;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookDTO create(@RequestBody BookDTO dto){
    // converto dto em uma entity Book !!!!!!!!!!!!!!!!!!!!! AQUI
    Book entity = modelMapper.map(dto, Book.class);
    entity = service.save(entity);
    // AO voltar, converto de uma entity apra dtp !!!!!!!!! AQUI
    return modelMapper.map(entity, BookDTO.class);
    

    
  }
  
}
````

Está convertendo dois objetos

````java
package br.com.rafanthx13.libraryapi.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// lobok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  private Long id;

  private String title;

  private String author;

  private String isbn;

}
````

````java
package br.com.rafanthx13.libraryapi.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 compilação
@Getter
@Setter
@Builder 
@NoArgsConstructor // Gera um construtor sem argumento
@AllArgsConstructor
public class BookDTO {
    private Long id;

    private String title;

    private String author;

    private String isbn;
  
}
````

## Outros detalhes


### LocalDate

Caracteriza uma data
````
import java.time.LocalDate; // Data
...
@Column
    private LocalDate loanDate;
````



-----
-----
-----
-----

Servidor de Email: AGENDAMENTO DE TAREFAS: VOu configurar para todo dai executar uma funçâo. Vmaos fazer com que todos os dias sejam enviados um email para quem pegou um livro emprestado mas nâo devolveu.

Vamos usar uma API De Schedule

Adiciono
+ @EnableScheduling no Main
+ @Schedule: Notaçâo que identifica que é uma tarefa agendada
+ @Schedule(cron = "0 4 13 1/1 * ?") Define quando será executado. Ele é definido através de uma pequena expressão. É escrito ao crontŕaio: vocÊ escreve em ordem:
	segundos - minutos - hora
+ http://www.cronmaker.com/
	Exemplo do Crom-maker
	0 4 13 1/1 * ?
	ai você coloca só 6 caracteres
	0 4 13 1/1 *
+ Aí, quando der esse horário, ele vai executar essa tarefa

````@Schedule(cron = "0 4 13 1/1 * ?")
public void testesSchedule(){
	System.out.println("All");
}


