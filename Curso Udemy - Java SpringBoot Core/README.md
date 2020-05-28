# Spring Boot Expert: JPA, RESTfull API, Security (JWT)

Link: ()[https://www.udemy.com/course/spring-boot-expert/]

mvn install

mvn spring-boot:run

para restartar so preciso do `run`


## O que vamos ver no projeto

**Spring Boot e Spring Framework**
+ Criar e entender a estrutura de um Projeto Spring
+ Entender a arquiterura de Spring Boot
+ Fazer Configurações utilizando classes de configuração
+ Entender como funciona o container e Injeção de Dependências
+ Utilizar ocnfiguração externalizada
+ Trabalhar com múltiplos ambientes
+ Criar `annotations` customizadas


**Spring Data**
+ COnfigurar e Conectar ao BD
+ Acessar BD em memória pelo browser
+ Utilizar SQL com SPring JDBC apra fazer operações
+ Mapear entidade JPA e utilizar `EntityManager` para realizar as operações na BD
+ Trabalhar com JPA `Repositories`
+ Fazer consultas utilizando `QueryMethods` e `@Query`

**Spring Web - RESTful API**
+ Implementar uma REST API de um sistema completo
 + Iremos fazer um sistema d evendas
+ Mpaear recursos e subrecuros REST utilizando RESTFul
+ Tratar erros de forma simples e bem especializdo
+ Fazer operaçôes sobre métodos HTTP: POST/ DELETE/GET; PUT e retonrar status_code
+ Trabalhar com LOMBOK no projeto
+ Utilzia Spring Boot Dev Tools
  + Fazer alteraçôes no código e refletir na aplicação

**Bean Validation**
+ Utilizar as especificaoa Bean Validation
+ Criar Annotation e validador CUstomizado
+ Configurar e trabalhar com Internacionalização

**Spring Segurity with JWT**
+ Adicionar Segurança na sua API
+ Entender como funciona e como configurar Spring Security
+ Faz Autenticação
+ Autorizar URs de cadoro com perfis
+ Implementar Autenticação Basic
+ Conhecer e implementar autenticação com JWT em conjunto com Spring Security

**Outros**
+ Git
+ DOcumentar com swagger
+ Deploy 


## Spring Boot FrameWork e Spring Core

É conveçâo a classe principal ter "Application" no nome dela. Classes em Camel Cas

````
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringExpertCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExpertCoreApplication.class, args);
	}

}
````

Fzendo um Hello WOrld simples

````
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringExpertCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExpertCoreApplication.class, args);
	}

	@GetMapping("/hello")
	public String helloWorld(){
		return "Hello World";
	}

}

````

### Entendendo o Spring

É do `parent` do `pom.xml` qye vem boa parte da configuração automática do spring. Os `starter` da `dependency`.

Pra funcionar é necessaŕio o parent e o starter básico

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.0.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

O restantes dos módulos são outra coisas

TOdos os staters começam com

data-jpa
security
test

### COnfigurations e Beans

Para configura mais o SpringBoot você cria classe que tenha a annotation `@Configuraion`. O Spring , ao inicializar ele lẽ todas as classes e as trata de forma diferente para cada Annotation que tiver na classe.

Nâo há necessidade de se preocupar com Ordem. A sua classe de configuraçâ pode está em qualquer lugar e com QUalquer nome, mas ela deverá ter a annotation, só asism que dá para reconhecer.

Os beans é a forma de acessar configuraçôes e modificalas

````java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }

    @Bean(name = "outraConfiguracao")
    public String outraConfiguracao(){
        return "Sistema de Vendas";
    }
}
````

No exemplo acima, estou dizneo: crei essa strning apra eu pdoer usála em outtos locais.

Para usar na minha aplicaçâo eu usao @Autowired, para auto-injetar e indico pelo @QUalifier

````java
@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
````

## COntainer IOC (Inversion Of COntroller/Dependencias)

Esse container é responsável por várias coisasa na nossa aplicaçâo. É um container que armazena os Beans da nossa aplicaçâo:
@Component, @Configuration ou @Service, @Bean.

@COmpoennt
+ É uma classe que tem método uteis para aplicaçâo
+ Há 3 subtipos tipos
  - @Service: V
  - @COntroller: C
  - @REpository: M
 + Se você cria uma classe que nâo se encaixa em nehuma dessa 3, coloque  `@COmponet`


 VocÊ pode configurar também o escaneamento do SPring
 @ComponenteSacen(basePacakges = {br.rafanthx.13})

 EM geral, isos nao é necessário pois o spring ja escaneia onde a partir de onde está a classe APplication. Mas, se vocÊ inserir uma lib ou algo de terceior se dexar em um pacote separado do aplplcaition e ainda quiser que eleia, aí, será nexcessário esssa anottaiton

 ## Injeção de dependnecias

 degisn Pattters
 + Delegar a terceitros a criaçao das intancias/dependencais que eu vou usar. OU seja, é deelgar a outra mandar o obejto qpara que eu nâo tenha que fazer o `new` toda vez que for usar a classe

 Exemplo: Os controllers usam Services

Criar um objeto a todo momento gastaria muito tamepo. ENtao delegamos ao IOC. Qaundo inicializa uma app Spring, eu crio Singletons de cada componente que fica no IOC. quando solicitado, passa-se esse singleton, asism evita-se criar a todo mundo.

É passado aatravés do `@Autowiret` observe abaixo

````java
private ClienteRepository repository

@Autowired
// Metodo contrutor
public ClientesServices( ClientRepository rep){
	this.repository = rep;
}
````
PErceba que

No Spring, para marcar os pontos de injeção dentro da sua classe, você utiliza a anotação @Autowired.

Você pode utilizar essa anotação em:

+ Propriedades;
+ Construtores; e
+ Métodos (mais comumente, os setters)

**O que torna uma classe elegível de ser injetada**

Para que uma instância do tipo ClienteRepositorio possa ser injetada em algum dos pontos de injeção é preciso que ela se torne um bean Spring.

Fazemos isso anotando ela com a anotação @Component ou com qualquer uma de suas especializações:

@Respository
@Service
@Controller


O Spring chama essas quatro anotações de estereótipos, sendo que as três últimas são como anotações “filhas” da anotação @Component.

A anotação @Component é a mais geral e as outras, que são @Repository, @Service e @Controller, são para usos mais específicos em componentes de persistência, serviço e controlador, respectivamente.

Apesar de termos esses quatro estereótipos, eles não tem especificidades, com exceção da anotação @Repository, que pode adicionar um pequeno comportamento relacionado a tradução das exceções de persistência.

Ainda assim, é aconselhável que você as utilize para especificar seus componentes de persistência, serviço e controlador, por dois motivos.

Primeiro que o uso delas torna mais fácil a associação com aspectos (AOP) e segundo porque não está descartada a possibilidade de, no futuro, elas ganhem tratamentos específicos do framework.

IOC NO attr
````java
public class ClienteServico {
   
  @Autowired
  private ClienteRepositorio repositorio;
   
  ...
}
````

No cnstrutor
+ Tem q vantagem de que, se a classe for um @COMponent, você pode tirar o ?@Autowired que ele vai entender as dependecias e injetalas sem ter a notaçâo
````java
public class ClienteServico {
   
  private ClienteRepositorio repositorio;
   
  @Autowired
  public ClienteServico(ClienteRepositorio repositorio) {
    this.repositorio = repositorio;
  }
 
  ...
}
````

No método
````java
public class ClienteServico {
   
  private ClienteRepositorio repositorio;
   
  ...
 
  // Caso você preferisse, esse método poderia se 
  // chamar também "configurarRepositorio", mas
  // o mais comum é criar um método setter mesmo.
  @Autowired
  public void setRepositorio(ClienteRepositorio repositorio) {
    this.repositorio = repositorio;
  }
}
````
EM geral, colocamos no contrutor ou atrr

## Customizar Spring `application.propieties`

Fica na pasta `/resoruces`.

Os dados ai salvos sâo no modelo chave-valor

Exemplo
````
application.name=Sistema de Vendas
````

Eu uso com 

````java
@Value("${application.name}")
private String appName;

public Srting helloWorld(){
	return appName
}
````

Para modificar a porta
````
server.por=8081
server.servelet.conter-path=/sistema-vendas # Agora a aplicaçâo vai rodar com uma nova raiz /sistema-vendas
````

https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html

## Como trabalhar com difernetes ambientes no SpringBoot

VocÊ cria outros arquivos `.proprieties` como os abaixo?

+ `application-production.proprieties`
+ `application-test.proprieties`
+ `application-development.proprieties`
+ `application-staging.proprieties`

Em cada um deles vai ficar as configurações do seu respectivo ambiente. Depois, no `application.proprieties` principla você adiciona a linha que decide qual ambiente usar, sendo a primeira linha:

````
spring.profiles.active=production
````
Assim vai rodar o Spring com as configurações de  `production`

### `@Profile`

Eu posso especificar que um componente só será executado SE ESTIVER EM UM CERTO AMBIENTE.

No exmeplo abaixo, essa configuração só será rodada em ambiente "development"

````java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÃO DE DESENVOLVIMENTO");
        };
    }
}
````

## Criando annotations Customizadas

````java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Caracteristicos da annotation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// o que quremos da nossa annotation
@Configuration
@Profile("development") // vai herdar development, eaasim evita ter duas annotations e dessa forma basta eu mudar aqui que vai mudar em todo lugar
public @interface Development {
}
````
voce pode criar para:
+ agrupar annotaions
+ 

as ammaotations em geral dso compostas de outras annotation

---
---
---

## Executar Spring direto

Usa-se `CommandLinerRunner` para executar direto quando inicializar o SPring.

VOcê pode usá-lo para executar as coisa diretamente pelo spring sem precisar ter que acessa ruma URL.

````java
  @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.salvar(new Cliente("Dougllas"));
            clientes.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientes.buscarPorNome("Cli").forEach(System.out::println);

            System.out.println("deletando clientes");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }
````

----
----
----
----

## Spring Data JPA

Priemira coisa é colocar o stater do jpa.

````xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>

````

Configurando as cosias em `application.proprieties`

````
# Configurações do H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# Dialeto da JPA: basta modificar isso para mudar de banco
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Propriedades do banco em memória H2
spring.h2.console.enabled=true # Habilitando console
spring.h2.console.path=/h2-console # URL para acessar o H2 no browser (WorkBench)
````

Subindo o spring você pode acessar o h2 por /h2-console colocando as  mesmas configuraçôes de application.proprieties

### Já inserindo dados no H2

Você pode criar o arquivo `data.sql` junto de application.propries que ele será executado ao subir a aplicaçâo. Asism, podemos dar o star tno nosso H2 já colocando algumas tabelas

### Trabalhando com Banco 

#### JDBC

HÁ VÁRIAS FORMA DE ACESSAR O BANCO DE DADOS EM JAVA. UMA DAS MAIS PRIMÁRIAS É COM JDBC que é básica

NELA NÂO HÁ MAPEAMENTO JPA

Exemplo:

Entidade Cliente NÂO-MAPEADA (usada no JDBC) para agrupar dados
````java
public class Cliente {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
````

````java
@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update( INSERT, new Object[]{cliente.getNome()} );
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(), cliente.getId()} );
        return cliente;
    }
}
````

O JDBC 
#### JPA - Mapeamento de entidades com ENtity Manager

 

Mudamos a entidade Cliente e o repository Clientes.

````java
import javax.persistence.*;

@Entity
@Table( name = "cliente" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") // nao é obrigatorio colocar que tiver mesmo nome
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
````

Para fucnionar com o Repository

É necessário @Transacitonal para funcionar.

````java

import io.github.dougllasfps.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    @Autowired
    private EntityManager entityManager;

    @Transactional // Garante que sej auma transação
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find( Cliente.class, id );
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
       String jpql = " select c from Cliente c where c.nome like :nome ";
       TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
       query.setParameter("nome", "%" + nome +"%");
       return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
````

### Spring Data JPA

É a interface otimizada do Spring Data. Em vez de ter que definir cada método para cada coisa que agente fizer, agente só vai fazer o mapemaneot.

Para fazer isso a classe repository deve virar uma interface que extende JpaRepository<> passando como parametro A classe de mapeamento e o tipo de dado do ID

Classe CLiente Mapemada JPA
````java
import javax.persistence.*;

@Entity
@Table( name = "cliente" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    // Getter, Setter, toStrin Retirados
}
````

REPOSITORY SPRING

Automaticamente ao extender JpaRepository, agente já consegue vários métodos.

Todo JpaRepository já tem interno a notaçâo @Repository.

Hoje é bem utilziado, já  nâo vale mais apenas usar SQL nativo. E p

**query Methods**

Na interface que extend JpaRepository vocÊ pode construir métodos cuja implementaçao sao gerados dinamicamente .

`findByNomeLike`: como esta'mapeando para cliente o find == `select` por `nome` usando `like`.

Os parâmetros devem ser na ordem dos métodos

**Convençôes**
find :: list
exists :: boolean
save
delete

VocÊ tambem pode usar @Query para fazer consultas manuais e mais conplexas colocando nativeQuery = True

@Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)

Pois o comun é executar HQL que é a query padrão do Hibernete,



````java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer > {

    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome( @Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    List<Cliente> findByNomeLike (String nome);

    List<Cliente> findByNomeOrIdOrderById (String nome, Integer id);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );


}
````

Para sabeer o que é que está saindo do SPringJPA você pode em application.proprietes colcoar as seguintes linhas

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true

Isso vai facilitar ver a consulta para debugar

**FAZER JOIN**

@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );

Onde

````java
@Entity
@Table( name = "cliente" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

````

INTELIJ SHORTCURA:
CLTR+ ALT+ O : apagar imports desnecessáiro do aruqivo java

---------------------
--------------
---------------

## Spring Boot Rest Web

Criaçâo de controller (REpository e entity já foram criados)

Controller Básico

````java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @RequestMapping("/api/clientes/")
public class ClienteController {

    @RequestMapping(value = "/api/clientes/hello/{nome}", method = RequestMethod.GET,
        consumes = { "application/json", "application/xml"})
    @ResponseBody
    public String helloCliente( @PathVariable("nome") String nomeCliente ){
        return String.format("Hello %s ", nomeCliente);
    }

}
````

+ `@Controller`: Designa que vai receber requisições REST

+ REquestMapping maeia a URL
+ VOce poder por no icio para ser a base desse controller e especificar os métodos ou os subrecusos usando requestMaping em cada métoo
  - recebe
- value = "a url"
- method: qual metodo http : GET, PU,PST
- consume= array das coisas que recebe {applciation/json, application/xml}. Especifia o tipo de coisa que vai receber
- produces = especificar o tipo de coisa que vai retornar, possui os mesmos valores de consumes
+ ResponseBody: Sem isso nao volta como JSON

Evoluçâo par aum GET mais espeficifo

````java
import rafanthx13.springexpertcore.model.entity.Cliente;
import rafanthx13.springexpertcore.model.repository.Clientes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientesRepository;

    // ja vai auto-injetar
    public ClienteController( Clientes clientes ) {
        this.clientesRepository = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById( @PathVariable Integer id ){
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok( cliente.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
````

+ ResponseEntity:
 - Representa o corpo da resposta, é o envelope que você vai colocar o que retornar
 - NEle pode-se colocar o Status HTTP
 - Para o notFOund, é necessário o `.build()` para indicar que está mesmo retornando nada

 + Optional
  - É uma interface para podemos verificar se algo é nulo ou nâo de uma forma mais elegante usando `isPresent()`

  REfatorando tudo

  EM vez de usar a annotation @Controller, use@REsController, pois o RestCOntroller vair por @RepsonseBody em todos os seus métodos, o que vai economizar

  comoloar
  @RequestMapping("/api/clientes")
  e n os meotod agora vai ficar
  se precisar de algo depois
  @GetMapping("{id}"),
  se nao, se for so a base mesmo ai nem precisa especificar o value
  @PostMapping

  // buscamos pelo cliente, pegamos seu id e colocamos no novo cliente
    // O 'save' spring JPA sava e atualizar (se for nem um id existente)


---
Ṕorque ter service?
+ Para separar melhor a parte que recebe e retorna requisições ( controler) da parte de validaçâo e uso dos repositorios (service) tornando asism mai modularizado

Porque usar service e ServiceIMP?
+ É uma boa prática ser baseado por interface. Usamos service como interface pois vai ajudar a modularizar o código e a fazer testes unitarios (pois é pela interface, que ja obriga a ter certos metodos (mock))

----

DTO:
+ É o tempalte do dado de retonro, pois muitas veze o que é retonrando nâo é extamente igual À entidde

---- 

Lomobok

Evitar criar método geter/seter

so pondo annotations

Quando compilar, vai gerar os getters e setters.

É necessário a IDE ter o plugin pra ela nâo reclamar como se nâo tive-se

VOcÊ pode ver eles no `target`

@Getter
@Setter
@ToString
@NoArgsCOntructor
@AllConstructor
@Data = (getter, setter,  toString, EqualsHashCOde) economiza anotations

----

## BeanValidation

É a parte de validaçâo da entrada dos dados. Basta algumas annotations e tudo estará validado com identificaçâo de mensagem e lançamento de erros com mensagens

+ Utilizar as especificaoa Bean Validation
+ Criar Annotation e validador CUstomizado
+ Configurar e trabalhar com Internacionalização

### Onde é feito

**NAS CLASSES DE Entity e de Controller, COntroler Adivece, APIErros, validation/, config/INternacionalizaoa**

### Lista do bean validation
````
@NotEmpty(message = "{campo.descricao.obrigatorio}") // para string
@NotNull(message = "{campo.preco.obrigatorio}") // para numero
@CPF(message = "{campo.cpf.invalido}")
````

### Exemplos

Nesse caso `@NotEmpty` garatne que nâo seja vazio
````java
import org.hibernate.validator.constraints.br.CPF;

public class Cliente {

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    // @CPF valida o CPF brasileiro e já tem por padrão no Hibernate
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")

    private String cpf;

}
````

E nos controller vamos colocar @ valid

````java
// CLienteController.java
public void update( @PathVariable Integer id,
                        @RequestBody @Valid Cliente cliente ){
        clientes
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não encontrado") );
    }
````

Esse é o método que vai pegar as mensagens do BeanValidation pois é `MethodArgumentNotValidException` que ele lança

````java
@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

}
````

### Criando Uma annotation Customizada

É a pasta `validation`.

Há duas entidadea: a interface que marca o campo e a COnstraintValidation que faz mesmo a validação.


INTERFACE
+ Na interface tem que ter: `message, groups, payload`
````java
package io.github.dougllasfps.validation;

import io.github.dougllasfps.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Ser verifica em execução
@Target(ElementType.FIELD) // O Targe diz aonde eagnete pode colocar a anotation, entao, em um campo
@Constraint(validatedBy = NotEmptyListValidator.class) // Especifica qual será o validador
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia.";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

````

VALIDADOR
+ IMplementa o COntraintValidadtion recebe dois aprametros: a annotation e o tipo de dado que ele etme que validar
+ POr implementar uma annotation tenho que implementar `isVlaid` e `initialize`
````java
package io.github.dougllasfps.validation.constraintvalidation;

import io.github.dougllasfps.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(List list,
                           ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    // POde ser usadao ppra pegar mais alguma coisa
    @Override
    public void initialize( NotEmptyList constraintAnnotation ) {
    }
}

````

Onde será usado PEdidioDTO

````java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> items;

}
````

### Internacionalização

Como colocar todas as mensagesn de erro em um so lugar

Criamos messages.proprieties em resources e colocasmos os seguintes dados

````properties
campo.codigo-cliente.obrigatorio=Informe o código do cliente.
campo.total-pedido.obrigatorio=Campo Total do pedido é obrigatório.
campo.items-pedido.obrigatorio=Pedido não pode ser realizado sem itens.
campo.cpf.invalido=Informe um CPF válido.
campo.cpf.obrigatorio=Campo CPF é obrigatório.
campo.nome.obrigatorio=Campo nome é obrigatório.
campo.descricao.obrigatorio=Campo Descrição é obrigatório.
campo.preco.obrigatorio=Campo Preço é obrigatório.
````

EM proprieties, cada coisa é chave e valor, entâo nas mesasges do beanValidation, em vez de por uma string você coloca {chave}.

exemplo:

````java
@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> items;
````
Mas temos que dizer para o spring ler o messages.properties

````java
package io.github.dougllasfps.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    // Aqui define a fonte de mensagem
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // nome do arquivo, nao precisa colocar a extençâo propieties

        messageSource.setDefaultEncoding("ISO-8859-1");
        // Pode ser usado para as mensagesn mudarem de acordo com o local (en-fr-it,br,pt e etc...)
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    // Objeto responsavel por fazr a interpolao entre os {} pelos dados de message.proprieties
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
````

## Spring Security - JWT

O que vamos ver:
+ Adicionar Segurança na sua API
+ Entender como funciona e como configurar Spring Security
+ Faz Autenticação
+ Autorizar URs de cadoro com perfis
+ Implementar Autenticação Basic
+ Conhecer e implementar autenticação com JWT em conjunto com Spring Security


### Como começar

Inser starter-security
````xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
````

E para o JWT
````xml
 <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
````
==> Ao colocar isso, **POR DEFAULT** ao fazer o build, ele vai gerar uma senha nos logs. Salve ela, pois agora, para acessar a API será necessário colocar sempre um usuário e senha a seguir

````
user: user
pass: a_senha_que_sai_do_build_nos_logs
````

Sem isso não dá pra acessar API e só cairá em 401 Unauthorized

### Classe de configuraçâo do SpringSecurity

+ Ao implementar WebSecurityConfigurerAdapter que ter dois `configure`
  - `configure(AuthenticationManagerBuilder auth)`
    - Configura autenticaççao
  - `configure( HttpSecurity http )`
    - Configura autorizaçâo

````java
package io.github.dougllasfps.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //Nâo precisa por @COnfiguration, pois ela já tem uma. Ela também tem outras configuraçôes para autenticaçâo WEB 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    // Gerar O objeto para criptografar e descriptografar
    // tem dois método: encode (criptografa), matches (verifica se são inguais)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // bcrypt: ELe gera um Hash diferente para uma mesma senha
    }

    // Inserindo no SpringContext o INterceptadoHTTP JWTAuthFIlter
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    // AUTENTICAÇÃO
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            // Implementaçâo da interfcae do SPringSecurity UserDetailsService
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    // PERMISSÂO
    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .csrf().disable() // nao precisamos de csrf
            .authorizeRequests() // apartir dess emtodo, definimos que pode acessar o que
                .antMatchers("/api/clientes/**") // as rotas de clientes podem ser acessada por USER E ADMIN
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/pedidos/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                // Vamos permitir que qualuqer um possa fazer POST em usuário sem autenticaçâo ou autorização (livre)
                .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                    .permitAll()
                // Pra qualquer outra URL vamos colocar que tem que está autenticado (assim nao precisa colocar rota por rota)
                .anyRequest()
                    .authenticated()
            .and()
                /*.httpBasic(); agente passe os dados por header com Autorization; asism dá rpa fazer por POSTMAN
                // Basic Auth no POSTMAN: Já oferece campo de usuário e senha pra autenticar*/
                // Nossa APP será StateLess (toda requisiçâo eu tenho que passar o token)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                // Adiciona um filtro antes de procesguir, vamos adicionar o filtro ... 
                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }

}

````

### Carregar usuários com roles

+ `UserDetailsService`: Classe padrâo do Spring Security para gerenciar usuário. Requer a implementação do método `loadUserByUsername`. O objetivo dessa classe é carregar o usuário de acordo com seu login
  - COm isso, vou usar em configure

  **ENTYTY**

  ````java
  package io.github.dougllasfps.domain.entity;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column
    private boolean admin;

}

  ````
**SERVICE**

````java
@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches( usuario.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    // Autenticaçâo de usuário. Dado o user_name, retorno ele da Base para sere usado internamente pelo Spring
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

}

````

**REPOSITORY**

````java
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}

````


**CONTROLLER**

````java
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor // LOmbok que funciona nas variáveis com "private final"
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar( @RequestBody @Valid Usuario usuario ){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

    // URL para eunteticar usuário pela primeira vez
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            // Obtem Usuário ENtity
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            // Obtem usuário interno mesmo
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            // geramos o token mesmo
            String token = jwtService.gerarToken(usuario);
            // retorno TokenDTO
            return new TokenDTO(usuario.getLogin(), token);

        // Se der algum problem vai sir alguma dessa exceptions
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}

````

Send ... Dados enviados para retornar token
````java
@Data
@NoArgsConstructor
public class CredenciaisDTO {
    private String login;
    private String senha;
}

````
Receive ... Dados do TOken retornando ..

````java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String login;
    private String token;
}
````

### JWT (JSON Web Token)

O Token nâo é só feito com usuário e senha. ELe é baseado de acordo com um payload que é um objeto que pode ter umomnte de coisa.

Vamos usar a depencia jjwt para java

````xml
 <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
````

em application.proprietis
````
security.jwt.expiracao=30
security.jwt.chave-assinatura=YSBsZW1icmUgw6kgYnJhbmNh
````

````java
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken( Usuario usuario ){
        long expString = Long.valueOf(expiracao); // Gerando a expiração
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString); // A data de expiraçâo a partir de hoje
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant(); // Pegando a zona do brasil para gerar um Instant
        Date data = Date.from(instant); // A expiraçâo no jjwts é feita por um objeto Date. DO INstant gero o Date
        // Gerando o JWT

        // apartir de .setClaims() vocÊ pode colcoar mais cosias como PAYLOAD do JWT
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith( SignatureAlgorithm.HS512, chaveAssinatura )
                .compact();
    }

    // Claims é o PayLoad
    // ExpiredJwtException é o erro que podemo obeter se ele estiver venido
    private Claims obterClaims( String token ) throws ExpiredJwtException {
        return Jwts
                 .parser() // para decodificar
                 .setSigningKey(chaveAssinatura) // SECRET_KEY
                 .parseClaimsJws(token) // o token para decodificar
                 .getBody(); // 
    }

    // Validar Token
    public boolean tokenValido( String token ){
        try{
            // Obtenho o PayLoad
            Claims claims = obterClaims(token);
            // Decodificando Data de Date para LocalDateTime
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            // O token só é invalido se o data for depois de data (LocalDateTime)
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }

    // TESTANDO GERAÇÂO DE TOKEN E DEBUGANDO
    public static void public static void main(String[] args) {
        ConfugurableApplicationContext context = SpringApplication.run(VendasApplication.class);
        JwtService service = context.getBean(JwtService.class);
        Usuario usuario = Usuario.build().login("fulano").build();
        String token  =service.gerarToken(usuario);
        System.out.println(token);
    }
}

````

**FILTRO JWT: Ou seja, solicitar o JWT para acessar as coisas**
+ Vai extender OncePerRequestFilter
+ É auqi que vai recuperar do Header e validar JWT

````java
package io.github.dougllasfps.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl usuarioService;

    // Nao usamos Annotation pois ele terá um uso difernte
    public JwtAuthFilter( JwtService jwtService, UsuarioServiceImpl usuarioService ) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    // Estou intercptando uma requisiçâo e colocando um usuário autenticado no contexto do SpringSecurity
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        // Pegar Authorization
        String authorization = httpServletRequest.getHeader("Authorization");
        // Verifica se é um Token que tem um bom formato
        if( authorization != null && authorization.startsWith("Bearer")){
            // Separa Token JWT do "Bearer"
            String token = authorization.split(" ")[1];

            // Valido Token
            boolean isValid = jwtService.tokenValido(token);
            if(isValid){
                // Obtenho o login do usuário
                String loginUsuario = jwtService.obterLoginUsuario(token);
                // Carrego o meu usuário internmanete com susa permissões
                UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
                // Vamos por esse usuário no COntexto do SPringSecurity
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                // A linha abaisxo é pra deizer que é uma autenticação WEB
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                // INserindo no contxto do spring security
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        // Passando a requisição em diante. POde ou nâo ter o usuário gerado (isso vai ser resolvido depois)
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}

````

### Testa Spring pela IDE

É possível testar uma pequena parte rodando somente um método

```java
 // TESTANDO GERAÇÂO DE TOKEN E DEBUGANDO
    public static void public static void main(String[] args) {
        // Rodo a applicaçâo e obtenho o contexto
        ConfugurableApplicationContext context = SpringApplication.run(VendasApplication.class);
        // Do contexto consigo obeter qualquer Bean (@Service, @COntroler, @Repositoy e etc..)
        JwtService service = context.getBean(JwtService.class);
        Usuario usuario = Usuario.build().login("fulano").build();
        String token  =service.gerarToken(usuario);
        System.out.println(token);
    }
````

Em uma IDE como InteliJ, basta rodar só esse método que vai rodar o SPring e asism você pode testar poucas coisas

### REvisando SpringSecurity

+ JWTSercive: Code e decod token
+ JwtAuthFilter: Intercpetar requisiçôes HTTP e colocar o usuário se for encontrado no token válido
+ SecurityConfig: Configuramos tudo issio para funcionar
+ Usuário Controller: salva o usuário e tem UTL para autenticar

## Banco de dado MYSQL

Script para rodar n MYSQL WOrkBrench

````sql
create database vendas;

use vendas;

....
````

O restante é do arquivo .sql

Mudamos os dados de application.propieties

````
spring.datasource.url=jdbc:mysql://localhost:3306/vendas?useTimezone=true&serverTimezone=UTC
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true

security.jwt.expiracao=30
security.jwt.chave-assinatura=YSBsZW1icmUgw6kgYnJhbmNh
````

dependicna no pom.xml
````xml
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
````

Observaçôes ao passar par ao do H2 para MYSQL
+ Nas clases Entity , no ID, devemos usar `GenretaionType.IDENTITY` ao invez de AUTO, senao nao vai funcaionar

### Swagger

REASISTA AS AULAS PARA POR A PARTE DE SECURITY AO ACESSAR A API

````xml
<dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>

        ````


Clase que vai configurar

````java

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("io.github.dougllasfps.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Vendas API")
                .description("Api do projeto de Vendas")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact("Dougllas Sousa"
                , "http://github.com/cursodsousa",
                "dougllasfps@gmail.com");
    }

    public ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        SecurityReference reference = new SecurityReference("JWT", scopes);
        List<SecurityReference> auths = new ArrayList<>();
        auths.add(reference);
        return auths;
    }

}
````        

## Deploys

**JAR**
+ Por deault se gera o JAR
+ cOMANDOS: mvn clean package
+ para rodar a aplicaçâo JAR é: `java -jar ./arquivo.jar`

**WAR**
+ Lançar em servidores extenos como TOMCAT
+ Assita a aula: tem que inidicar que é um WAR e a dependencia do SPring Tomcat

........