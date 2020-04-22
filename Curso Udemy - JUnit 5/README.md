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

## Descrição do Projeto Realizado

### API REST Library

Livraria Simples para cadastro e empréstimo de livros incluindo testes unitários para cada parte:

+ Controllers: Implementa a primeira camada do REST, recebe as solicitações.

+ Services: Implementados regras de negócio.

+ Repository: Camada de modelo e acesso ao BD (inclui Testes de Integração)

## Estrutura de pastas

**data.dto (Data transfer object)**

+ Representa o JSON de uma entidade ou de qualquer coisa enviada ou retornada da API. É o que é recebido e enviado de volta pelo controller. EM geral, acaba sendo convertido para uma `entity` que em memória é o que vai ser salvo na banco ou trabalhado
  + Exemplo ReturnedLoanDTO que só tem uma única propriedade para fazer o path (atualizar) um único campo
+ Porque usar DTO?
  - Data Transfer Object (DTO) ou simplesmente Transfer Object é um padrão de projetos bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias ou processos de um sistema distribuído ou diferentes sistemas via serialização.

  - A ideia consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.

  - Numa chamada remota, seria ineficiente passar cada atributo individualmente. Da mesma forma seria ineficiente ou até causaria erros passar uma entidade mais complexa.

  - Além disso, muitas vezes os dados usados na comunicação não refletem exatamente os atributos do seu modelo. Então, um DTO seria uma classe que provê exatamente aquilo que é necessário para um determinado processo.

**data.entity**

+ Estrutura para gerenciar uma entidade na aplicação java. Geralmente é convertida no dto quando chega a requisição e na resposta da entidade em dto
  + Não mandamos `entity` para o cliente para não exibir certas propriedade. Observe os "Get filted" para ver como é a conversão

**data.respotory**

+ Entidade para fazer acesso ao banco
+ Feita em interfaces que implementam  'JPARepository' que recebem por parâmetos na sua definição o tipo de objeto do banco e o tipo de seu ID.
+ São interfaces e não precisam de implementação pois o JPA já faz isso

**service**

+ interface com os métodos a serem implementados
+ Porque usar `Service` e `ServiceImpl`?
  + O uso de interfaces no java visa atender à uma característica da POO que é o polimorfismo. Você possui uma estrutura básica que poderá ser implementada de N maneiras. Como exemplo, a interface List do java.util que pode implementar ArrayList, por exemplo.
    Além disso, caso você esteja utilizando um framework de IoC, como o Spring, precisará manter uma estrutura condizente com a injeção dos objetos, necessárias para o framework.

**service.impl**

+ classe que implementa um service, é nela que havera implementação de métodos. Possui internamente o repository para fazer as operações no banco

**controller**

+ Responde as *requisições* e dá a *response*
+ ApplicationControllerAdvice.java
    - Possui handles globais para todos os controllers
	- Tem @RestControllerAdvice: (Essa classe tem configurações globais para todos os controllers) Permite inserir configs a todos os controllers. Nele colocamos `@ExceptionHandler`, ou seja, tratamos de exceptions que darem no Controller

----

**Estruturas dos Testes**

ControllerTests
+ Testa a chamada da URL. Faz HTTP Request. Passa-se DTO e esparece voltar DTO (JSON). 
+ Faz Mock do service

ServiceTests
+ Testa o Service. Não faz HTTP Request, é como se já estive-se dentro do BackEnd, na hora em que o ControllerTest chama o service e utiliza uma entidade
+ Faz Mock de repository

RepositoryTest
+ Testa Repository. Não faz HTTP Request. Usa-se Entidade
+ Usa o banco H2 ; Notação @DataJpaTest. Agente faz CREATE, UPDATE, DELETE, READ direto desse banco e testamos a nossa entidade de repository pra saber se TUDO QUE O REPOSITORY FAZ ESTÁ FAZENDO CORRETAMENTE DEPOIS QUE ELE FAZ.

**Como fica então essa estrutura de testes**


Ao fazer o TDD de um Controller, por exemplo. Eu não preciso implementar o service, apenas simular com o Mock o seu retorno ou algum erro que der. Isso faz com que cada teste de cada tipo (controller, service, repository) fiquem bem separados e independentes. assim, quando eu faço o test de controller eu no precio do service/repository funcionando 100% implementado, preciso que apenas exista com os métodos (deixar somente com as assinaturas da interface). assim as coisas ficão bem separadas não dependendo da implementação dos outros tipos para fazer seu próprio teste.

Assim, para fazer um ControllerTest, você só precisa que o respectivo controller esteja implementado O respectivo controller, não precisa do service e nem do repository, pois estes são mockados. 

Assim, permite o desenvolvimento do TDD na seguinte ordem para cada funcionalidade da api:

ControllerTest -> Controller
ServiceTest -> Service e ServiceImpl
RepositoryTest -> Repository

Os testes do Repository são os únicos que realmente usa o banco, mas o banco a ser usado é somente um banco de memória



## Exemplos dos Tests para Salvar um Livro

### ControllerTest

```java
@Test
@DisplayName("Deve criar um livro com sucesso")
public void createBookTest() throws Exception{

    BookDTO dto = createNewBook();

    Book savedBook = Book.builder().id(10l).author("Artur")
        .title("As aventuras").isbn("001").build();

    BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(savedBook);

    String json = new ObjectMapper().writeValueAsString(dto);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .post(BOOK_API)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json)
        ;
	// Verifica se retornou o JSON desejado
    mvc
        .perform(request) 
        .andExpect( status().isCreated() )
        .andExpect( jsonPath("id").value(10l) )
        .andExpect( jsonPath("title").value(dto.getTitle()) )
        .andExpect( jsonPath("author").value(dto.getAuthor()) )
        .andExpect( jsonPath("isbn").value(dto.getIsbn()) )
        ;
}
```

### Controller

```java
@RestController // Controlador Rest
@RequestMapping("/api/books") // Base URL
public class BookController {

  private BookService service;
  private ModelMapper modelMapper; // Mapeia de DTO a Entity

  public BookController(BookService service, ModelMapper modelMapper){
    this.service = service;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookDTO create(@RequestBody @Valid BookDTO dto){
    Book entity = modelMapper.map(dto, Book.class);
    entity = service.save(entity); // retorna o dado inserido
    return modelMapper.map(entity, BookDTO.class);
  }
```

### ServiceTest

````java
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

  BookService service;

  @MockBean
  BookRepository repository;

  @BeforeEach
  public void setUp(){
    this.service = new BookServiceImpl(repository);
  }

  @Test
  @DisplayName("Deve salvar um livro")
  public void saveBookTest() {
      // cenario
      Book book = createValidBook(); 
      Mockito.when(repository.existsByIsbn(Mockito.anyString()) ).thenReturn(false);
    
      Mockito.when(repository.save(book)).thenReturn(
              Book.builder().id(1l)
                      .isbn("123")
                      .author("Fulano")
                      .title("As aventuras").build()
      );
      //execucao
      Book savedBook = service.save(book);
      //verificacao
      assertThat(savedBook.getId()).isNotNull();
      assertThat(savedBook.getIsbn()).isEqualTo("123");
      assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
      assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
  }
}
````
### Service

````java
@Service
public class BookServiceImpl implements BookService {
  
  private BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override // Indica que sobrescreveu metodo da interface
  public Book save(Book book) {
      if( repository.existsByIsbn(book.getIsbn()) ){
          throw new BusinessException("Isbn já cadastrado.");
      }
      return repository.save(book);
  }
}
````
### RepositoryTest

````java
@ExtendWith(SpringExtension.class) 
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {
   
	@Autowired 
    TestEntityManager entityManager;
    
    @Autowired
    BookRepository repository;
    
    @Test
    @DisplayName("Deve salvar um livro.")
    public void saveBookTest(){
        Book book = createNewBook("123");
        Book savedBook = repository.save(book); 
        assertThat( savedBook.getId() ).isNotNull(); // verifica se retornou livro
    }
}
````
### Repository

````java
public interface BookRepository extends JpaRepository<Book, Long> {

	// Não há metodo save, pois este já está dentro do JPA

  boolean existsByIsbn(String isbn);

  Optional<Book> findByIsbn( String isbn);

  
}
````

## Dependências

### Lombok

Plugin Maven

````xml
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
````

Lombok é uma dependência mas também é um plugin no inteliJ/VSCode para não dá erro de sintaxe e nem de `build`. Isso tem que ser colado porque ele gera métodos em tempo de execuçâo, entâo, se nao colocar o plugin, vai acusar porque você vai chamar métodos que não existem, mas que só existem no lobok.

Injeção de Dependências do Lombok

@Data   -> cria getres, setre, toString e isEquals e HasCode
@Getter -> Gera getters
@Setter -> Gera setters
@Builder
|==> Gera um método que permite setar os dados de forma mais legível
|==> coisas como `Book.builder().id(10l).author("Artur").title("As aventuras").isbn("001").build();`
@NoArgsContructor
@AllArgsContructor

#### @RequiredArgsConstructor

> https://www.baeldung.com/spring-injection-lombok

Com essa notação, não precisa fazer o construtor da classe nela. Já vai ta tudo pronto
`@RequiredArgsConstructor`



**@RequiredArgsConstructor // Injeta objtos nos atributos privados. O obejto tem que ser 'FINAL'**
**SO FUNCIONAN EM OBJETOS FINAL**

Isso pode ser convertido disso (em `BookController.java`).

````java
  // 
  private BookService service;
  private ModelMapper modelMapper;

  public BookController(BookService service, ModelMapper modelMapper){
    this.service = service;
    this.modelMapper = modelMapper;
  }
````

Para isso 

````java
@RequiredArgsConstructor // Com esas notação: LoanService, BookService e ModelMapper já vao ser criados e injetados
public class LoanController {

	private final LoanService service;
	private final BookService bookService;
	private final ModelMapper modelMapper;
````


### ModelMapper

http://modelmapper.org/getting-started/

Mapeia de uma classe para outra quando tem atributo muito aprecidos

````xml
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>2.3.0</version>
</dependency>
````

Para usar temos que criar um BEAN no main

````java
@SpringBootApplication
public class LibraryApiApplication {
	
	@Bean // O spring vai gera um único Singleton apra toda a aplicação
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
````

Depois costuma-se usar nos Controllers. Como no exemplo abaixo: converto de dto (dto representa o json da entidade recebida) em uma entidade (e depois converto de volta na saida.

````java
@RestController 
@RequestMapping("/api/book") 
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
    // !!! Converto de dto em uma entity
    Book entity = modelMapper.map(dto, Book.class);
    entity = service.save(entity);
    // !!! Converto de entity para DTO
    return modelMapper.map(entity, BookDTO.class);
  }

}
````
