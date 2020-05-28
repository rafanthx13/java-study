# Dependências



### Lombok

**Usada em:** library-api no curso https://www.udemy.com/course/design-de-apis-restful-com-tdd-spring-boot-e-junit-5/

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

**@RequiredArgsConstructor**

Com essa notação, não precisa fazer o construtor da classe nela. Já vai ta tudo pronto
`@RequiredArgsConstructor`

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
**Usada em:** library-api no curso https://www.udemy.com/course/design-de-apis-restful-com-tdd-spring-boot-e-junit-5/
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
