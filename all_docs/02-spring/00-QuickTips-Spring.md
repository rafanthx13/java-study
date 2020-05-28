# QuickTips Spring

### Start Spring Project

(Spring Initializr)[https://start.spring.io/]

Group: Identifica o autor
+ Ex: br.rafanthx13
Arficatct = Name: Nome do projeto
+ Ex: spring-example; o '-' eh retirado depois

Dependencies
+ Recomendado: DevTools, Lombok (agilza muita coisa no spring), Web

### Execute Spring App

`mvn install`

`mvn spring-boot:run`

## Executar Spring direto

Usa-se `CommandLinerRunner` para executar direto quando inicializar o SPring.

VOcê pode usá-lo para executar as coisa diretamente pelo spring sem precisar ter que acessa ruma URL.

Também da pra passar coisasr diretas com @AutoWired para queilo que for @COMponete (@Controller,@Repository, @Service e etcc..)

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
