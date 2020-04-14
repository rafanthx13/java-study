package br.com.rafanthx13;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockitoTest {

	@Test
	public void firstTestMockito(){
		// O Mock cria uma instancia falsa do objeto que nâo tem suas funções reais
		List<String> lista = Mockito.mock(ArrayList.class);
		// Quando for executar um método da instnacia mock, vocÊ tem que informar antes o que ele deve fazer

		// Os metodos de algo mockatod so rodam em algo que foi expecificado antes, se der um 'add.' nao vai
		Mockito.when( lista.size() ).thenReturn(20);
		// Assim, quando chamar, vai responder
		int size = lista.size();
		Assertions.assertThat(size).isEqualTo(20);
	}
}