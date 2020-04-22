# `Library-API` - Tarefas com Spring e enviando de Email com `mailtrap`

O Servidor de Email funcionará por AGENDAMENTO DE TAREFAS: Vou configurar para todo dia executar uma função. Vamos fazer com que todos os dias sejam enviados um email para quem pegou um livro emprestado mas não devolveu.

## Agendamento de Tarefas no Spring

### `@Scheduled` 

Vamos usar `@Scheduled` do Spring.

Para isso:
+ Uso notação `@EnableScheduling` na classe Main
+ Uso notação `@Scheduled` no método que será a tarega. 
  - Devo inserir também a notação `cron` que define quando ocorrerá
  - Exemplo: `@Schedule(cron = "0 4 13 1/1 * ?")`
  - Essa expressão é a escrita ao contrário: segundos, minutos, horas e dias
  - Existe um site que gera esse `cron`: http://www.cronmaker.com/
    * Nesse site usa-se 6 caracteres: então quando mostrar `0 4 13 1/1 * ?` deve usar `0 4 13 1/1 *`

### Exemplo de Task com `@Scheduled`

`LibraryApiApplication.java`: Aplicação `main`. Deve-se inserir `@EnableScheduling`

````java
@SpringBootApplication
@EnableScheduling
public class LibraryApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}
````

`ScheduleService.java`: Onde fica as tarefas agendadas

````java
@Service 
@RequiredArgsConstructor
public class ScheduleService {

    private static final String CRON_LATE_LOANS = "0 0 0 1/1 * ?"; // Executar Todo dia meio-dia

    @Value("${application.mail.lateloans.message}") // Injetado atraves de application.propieties
    private String message; // Mensagem a ser enviada

    private final LoanService loanService;
    private final EmailService emailService;
 
    @Scheduled(cron = CRON_LATE_LOANS)
    public void sendMailToLateLoans(){
        List<Loan> allLateLoans = loanService.getAllLateLoans(); 
        List<String> mailsList = allLateLoans.stream()
                .map(loan -> loan.getCustomerEmail())
                .collect(Collectors.toList());
        emailService.sendMails(message, mailsList);

    }
}
````

## Servidor de Email com Spring

Temos que inserir a seguinte dependência:

````xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-mail</artifactId>
</dependency>
````

A seguir é necessário buscar um servidor de email. Podemos usar o `MAILTRAP` para mandar email, ele é gratuito para o envio de email. Acessando-o e buscando os dados de configurações do servidor podemos colocar os dados em `resources/application.propieties`

````properties
# resources/application.propieties
# Mensagens que serão enviadas
application.mail.lateloans.message=Atenção! Você tem um emprestimo atrasado. Favor devolver o livro o mais rápido possível.
application.mail.default-remetent=mail@library-api.com
# Essas propriedades são do mailtrap
spring.mail.protocol=smtp
spring.mail.host=smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=user_name_of_mail_server
spring.mail.password=password_of_mail_server
# Configuraçôes de autenticação
spring.mail.properties.mail.smtp.auth = true
spring.mail.properties.mail.smtp.starttls.enable = true
````

Classe que envia email `EmailServiceImpl.java`

````java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor // Vai injetar javaMailSender
public class EmailServiceImpl implements EmailService {

    @Value("${application.mail.default-remetent}") 
    private String remetent;

	// Entidade que vai enviar email
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMails(String message, List<String> mailsList) {
		// Para quem vamos enviar: deve ser um array de String e nâo lista
        String[] mails = mailsList.toArray(new String[mailsList.size()]);
        // Construindo mensagem de email. Setamos cada parte
        SimpleMailMessage mailMessage = new SimpleMailMessage(); 
        mailMessage.setFrom(remetent); 
        mailMessage.setSubject("Livro com empréstimo atrasado");
        mailMessage.setText(message);
        mailMessage.setTo(mails); 
		// Envio real do email
        javaMailSender.send(mailMessage);
    }
}
````

