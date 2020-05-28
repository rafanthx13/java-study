# Mockito

## O que é `Mockito`?

O que é Mockito

É um framework que veio pra facilitar a criação de testes unitários, onde simulamos/manipulamos o comportamento de objetos para compor os cenários de testes.

## Usos



#### 01

```java
package br.com.rafanthx13;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

// 'RunWith' é necessário para '@Mock' vai funcionar
@RunWith( MockitoJUnitRunner.class)
public class MockitoTest {

	// Mockar uma 'List<String>', precisa do 'RunWith'
	// Substitui : List<String> lista = Mockito.mock(ArrayList.class); do código abaixo
	@Mock
	List<String> listaMockata;

	@Test
	public void firstTestMockito(){

		// O Mock cria uma instancia falsa do objeto que nâo tem funções reais
		// Quando for executar um método da instnacia mock, vocÊ tem que informar antes o que ele deve fazer
		List<String> lista = Mockito.mock(ArrayList.class);		

		// Os metodos de algo mockado so rodam em algo que foi especificado antes, se der um 'add' nao vai funcionar
		// Assim, quando chamar, vai responder
		Mockito.when( lista.size() ).thenReturn(20);
		int size = lista.size();
		Assertions.assertThat(size).isEqualTo(20);

		// Verifica se o método 'size' do objeto mockado foi executado
		// Uitl para identificar se realmente ocorreu chamadas de métodos, principlamente métodos voids
		Mokcito.verify(lista).size(); // Se 'size' não for invocado antes, vai dar erro e vai façhar o teste
		Mokcito.verify(lista, Mockito.times(2)).size(); // Verifica se executou duas vez
		Mokcito.verify(lista, Mockito.never()).size(); // Quando eu não quero que um método seja executado

		/* Usamo o objeto 'InOrder' para verificar se foi executado em Order
			Só funciona se houver a exec do método abaixo
			lista.add("");
			lista.size();
		*/
		InOrder = Mockito.inOrder(lista);
		InOrder.verify(lista).size();
		InOrder.verify(lista).add('')

	}
}
```

