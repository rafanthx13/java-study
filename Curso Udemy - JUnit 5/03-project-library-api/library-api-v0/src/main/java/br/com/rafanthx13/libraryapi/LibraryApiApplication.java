package br.com.rafanthx13.libraryapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Necessário para por Schedule na nossa APP
public class LibraryApiApplication {

	// O spring vai gera um único Singleton apra toda a aplicação
	@Bean // Bean Global
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}

	// @Autowired
	// private EmailService emailService;

	// @Bean // Quano se cria o Bena de CommandLineRunner, ele exeucuta esse método primeiro ao subir o spring
	// public CommandLineRunner runner(){
	// 	return args -> {
	// 		List<String> email = Arrays.asList("libraryapi-84272hash@inbox.mailtrap.io"); // Aqui, deve-se abrir a conta do mail trap e gerar o email e colocar aqui
	// 		emailService.sendMails("Testando serviço", email);
	// 		System.out.println("Emails Enviados");
	// 	};
	// }

}
