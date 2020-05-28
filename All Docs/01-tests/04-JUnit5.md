# JUnit5

Só trabalha com Java 8 pra frente

## Dependências para usar JUnit5

````xml
<properties>
 <maven.compiler.sorce>1.8</maven.compiler.sorce>
 <maven.compiler.target>1.8</maven.compiler.target>
<properties/>


<dependencies>

	<!-- JUNIT 5 -->
	</dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-api</artifactId>
		<version>5.5.2</version>
		<scope>test</scope>
	</dependency>

	<!-- Mockito -->
	</dependency>
		<groupId>org.mockito</groupId>
		<artifactId>mockito-junit-jupiter</artifactId>
		<version>2.23.0</version>
		<scope>test</scope>
	</dependency>

</dependencies>
````

O pacote de JUnit deixa de ser

`import org.junit.Test;`

para ser

`import org.junit.jupiter.api.Test;`

## Diferenças entre as versões

#### `expected`

Não há mais expected, agora é por `Asserions.assertThrows`

No código abaixo Eu espero que vai sair uma Exception de 'cadastroPessoas.adicionar'

````
org.junit.jupiter.api.Assertions.assertThrows(PessoaSemNomeException.class, () -> cadastroPessoas.adicionar());

````

----

### `@Before`

no JUnit4 usamos o `@Before`

+ @BeforeEach (executa uma vez para cada teste) 
+ @BeforeAll (executa uma vez antes de começar todos os testes



----

### @RunWith

@RunWith(MocktioJUnitRunner.class)

agora é

@ExtendsWith(MockitoExtension.class)

## Novidades
### @DisplayName

Isso é melhor do que deixar o nome como o método do testes que é camelCase

````java
@Test
@DisplayName("Nome  mais agradavel para entender testes")
public void nomeMaisAgardavelQueOMetodo(){
}
````


