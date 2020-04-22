# Deploy: JAR, WAR e Heroku

[TOC]

## Gerar JAR, WAR

#### Deploys na mão pra rodar local (no servidor da empresa)

Os artefatos podem ser JAR/WAR. O que define é a seguinte tag no `pom.xml`

````xml
<packaging>jar</packaging>
<!-- junto ao em <groupId> -->
````

Pra gerar:

```
mvn clean package
```

+ clean para limpar target
+ package para gerar jar/war

Vai executar os testes e se passar tudo vai gerar o WAR/JAR

### Executar e Gerar JAR

Como executar o jar

`java -jar nome_do_jar.jar`

Teste com o Swagger

### Executar e Gerar WAR

Para gerar um WAR corretamente, precisa adicionar a seguinte dependência 

````xml
<!-- ele ja entender que o seu server tem um tomcat -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
    
</dependency>
````

E depois vai no LibraryApplication (Onde tem o `main`) e coloque `extends SpringBootServletInitializer`

Agora `mvn clean pacakge`. Ele já não vai mais adicionar o tomcat integrado ao Arquivo empatado (como ocorre no .jar). Com o War você faz o deploy num servidor tomcat

### Diferenças entre JAR e WAR

WAR é o binário de projetos Java Web, que você roda de dentro de um servidor como o Tomcat.

+ Já o nome "WAR" vem de Web ARchive.
+ Também é um arquivo .zip, só que além dos arquivos .class, você pode colocar arquivos que o navegador entende como .html, .js, .css. Pode colocar classes Java especiais como as Servlets e arquivos especiais como .jsp, além de configurações.

JAR é o binário para projetos comuns, que não são Web.

+ O nome "JAR" vem de Java ARchive e, no fim das contas, é um .zip (só que com uma extensão .jar) com todos os .class compilados a partir dos seus arquivos .java. Há alguns outros arquivos de configuração.
+ O JAR pode ser duas coisas: - uma biblioteca: código de outras equipes ou empresas que você usa no seu projeto - um entregável para projetos Desktop ou de Linha de Comando: o arquivo que você manda para o seu usuário.

## Heroku

Pode faze o deploy tanto por 

+ Heroku CI
+ Git com Ferramenta de CI