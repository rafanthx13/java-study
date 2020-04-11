# Maven

## Links

+http://www.dclick.com.br/2010/09/15/o-que-e-o-maven-e-seus-primeiros-passos-com-a-ferramenta/
+ https://pt.wikipedia.org/wiki/Apache_Maven
+ https://maven.apache.org/what-is-maven.html
+ https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

## Instalar Maven

????

### Verificar se tem o Maven INstalado

`mvn --version`


## O que é o Maven?

O Maven é uma ferramenta desenvolvida pela Apache, ela serve para gerenciar as dependências e automatizar seus builds.

O Maven é uma ferramenta de integração de projetos. É responsável por gerenciar dependências, controlar versão de artefatos, gerar relatórios de produtividade, garantir execução de testes, manter nível de qualidade do código dentre outras.

O Maven utiliza um arquivo XML (POM) para descrever o projeto de software sendo construído, suas dependências sobre módulos e componentes externos, a ordem de compilação, diretórios e plug-ins necessários. Ele vem com objetivos pré-definidos para realizar certas tarefas bem definidas como compilação de código e seu empacotamento.

O Maven baixa bibliotecas Java e seus plug-ins dinamicamente de um ou mais repositórios, como o Maven 2 Central Repository, e armazena-os em uma área de cache local `/Home/.m2`. Este cache local de artefatos baixados pode também ser atualizado com artefatos criados por projetos locais. Repositórios públicos podem também ser atualizados.

**Quote da documentação**

O objetivo principal do Maven é permitir que um desenvolvedor compreenda o estado completo de um esforço de desenvolvimento no menor período de tempo. Para atingir esse objetivo, o Maven lida com várias áreas de preocupação:

+ Facilitando o processo de compilação
  - Embora o uso do Maven não elimine a necessidade de conhecer os mecanismos subjacentes, o Maven protege os desenvolvedores de muitos detalhes.
+ Fornecendo um sistema de construção uniforme
  - O Maven cria um projeto usando seu modelo de objeto de projeto (POM) e um conjunto de plugins. Depois de se familiarizar com um projeto Maven, você sabe como todos os projetos Maven são criados. Isso economiza tempo ao navegar por muitos projetos.
+ Fornecendo informações de qualidade do projeto
  - O Maven fornece informações úteis sobre o projeto, que são em parte retiradas do seu POM e em parte geradas nas fontes do seu projeto. Por exemplo, o Maven pode fornecer:
    - Log de alterações criado diretamente do controle de origem
    - Fontes de referência cruzada
    - Listas de discussão gerenciadas pelo projeto
    - Dependências usadas pelo projeto
    - Relatórios de testes de unidade, incluindo cobertura
  - Os produtos de análise de código de terceiros também fornecem plug-ins do Maven que adicionam seus relatórios às informações padrão fornecidas pelo Maven.
+ Incentivar melhores práticas de desenvolvimento
  - O Maven visa reunir os princípios atuais para o desenvolvimento de melhores práticas e facilitar a orientação de um projeto nessa direção.

Por exemplo, especificação, execução e relatório de testes de unidade fazem parte do ciclo normal de construção usando o Maven. As melhores práticas atuais de teste de unidade foram usadas como diretrizes:

+ Mantendo o código-fonte de teste em uma árvore de origem separada, mas paralela
+ Usando Convenções de Nomenclatura de Casos de Teste para Localizar e Executar Testes
+ Tendo os casos de teste configurando seu ambiente em vez de customizar a construção para a preparação do teste

O Maven também auxilia no fluxo de trabalho do projeto, como gerenciamento de versões e problemas.

Maven também sugere algumas diretrizes sobre como planejar a estrutura de diretórios do seu projeto. Depois de aprender o layout, você pode navegar facilmente por outros projetos que usam o Maven.

Embora adote uma abordagem opinativa do layout do projeto, alguns projetos podem não se encaixar nessa estrutura por razões históricas. Embora o Maven seja projetado para ser flexível às necessidades de diferentes projetos, ele não pode atender a todas as situações sem comprometer seus objetivos.

Se o seu projeto possui uma estrutura de compilação incomum que não pode ser reorganizada, talvez você precise renunciar a alguns recursos ou ao uso do Maven por completo.

**RESUMO**

Ferramenta pra facilitar gerenciar dependencias e facilitar compilação e tudo o mais para projetos Java.

## O que é `pom.xml`

Project Object Model se trata do ponto de partida para o maven executar seu lifecycle. Nada mais é do que um arquivo XML que descreve propriedades e características do projeto, como por exemplo a versão do compilador que será usa

**Resummo**

Como o `package.json`

### Estrutura do arquivo `pom.xml`

````xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>Maven Quick Start Archetype</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
````

### Artefato (artifact)

Principais partes que definem um Artefato e que provavelmente terá que colocar ao iniciar o projeto

- **groupId**: é como o endereço do site ao contrário, como `br.com.starcode`, `org.apache`, `com.google`, `com.ibm`, etc.
  - A universally unique identifier for a project. It is normal to use a fully-qualified package name to distinguish it from other projects with a similar name (eg. `org.apache.maven`).
- **artifactId**: geralmente é o nome do projeto. Ele deve ser único por grupo.
  - The identifier for this artifact that is unique within the group given by the group ID. An artifact is something that is either produced or used by a project. Examples of artifacts produced by Maven for a project include: JARs, source and binary distributions, and WARs.
- **version**: a versão do projeto, como `1.4.2` ou `3.0`. Se houver o sufixo `-SNAPSHOT` (`0.0.1-SNAPSHOT`, por exemplo) significa que o projeto está em desenvolvimento e o pacote pode ser alterado.
  - The current version of the artifact produced by this project.
- **packaging**: jar, war, ear, pom (projeto de configuração).
  - The type of artifact this project produces, for example `jar` `war` `ear` `pom`. Plugins can create their own packaging, and therefore their own packaging types, so this list does not contain all possible types.**Default value is**: `jar`.
- **Classificador**: identificação opcional para diferenciar variações da mesma versão. Por exemplo, se o programa é compilado para diferentes versões do Java podemos usar os classificadores `jdk4` e `jdk6`. Se há variações específicas para Sistemas Operacionais, podemos ter os classificadores `linux` e `windows`.

**Resumo**

**groupID**: Identificador único, no meu causo, poderia ser `br.com.rafanthx13`

**artifactId**: Nome do projeto pode ser `FirstSPringProjectApp`

**version:** `0.0.1-SNAPSHOT`

## Estrutura do Projeto Maven

#### Estrutura padrão de um projeto Maven

A estrutura padrão do projeto inclui boas práticas (como separar as classes de teste das classes do sistema) e facilita aos novos desenvolvedores encontrar o que eles querem, já que todos os projetos seguirão uma estrutura semelhante.

Veja a seguir os principais diretórios utilizados:

- `src/main/java`: aqui fica o código-fonte do sistema ou biblioteca.
- `src/main/resources`: arquivos auxiliares do sistema, como `.properties`, XMLs e configurações.
- `src/main/webapp`: se for uma aplicação web, os arquivos JSP, HTML, JavaScript CSS vão aqui, incuindo o `web.xml`.
- `src/test/java`: as classes com seus testes unitários ficam aqui e são executadas automaticamente com JUnit e TestNG. Outros frameworks podem exigir configuração adicional.
- `src/test/resources`: arquivos auxiliares usados nos testes. Você pode ter properties e configurações alternativas, por exemplo.
- `pom.xml`: é o arquivo que concentra as informações do seu projeto.
- `target`: é o diretório onde fica tudo que é gerado, isto é, onde vão parar os arquivos compilados, JARs, WARs, JavaDoc, etc.

### Criar projeto maven

```
mvn -B archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DgroupId=com.mycompany.app \
  -DartifactId=my-app
```

Para projetos spring há o `spring boot initialzr`

[Spring Starter](https://start.spring.io/)

### Como é um projeto maven

```
my-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- mycompany
    |               `-- app
    |                   `-- App.java
    `-- test
        `-- java
            `-- com
                `-- mycompany
                    `-- app
                        `-- AppTest.java
```



## Ciclo de Vida do Maven

#### Ciclo de vida padrão do Maven

O Maven possui um ciclo de vida padrão. Cada passo do ciclo de vida é chamado de *Goal* e possui plugins específicos. Os mais importantes são:

- **`validate`**: valida o projeto e se as informações necessárias para os próximos passos estão disponíveis, como as dependências por exemplo.
  -  valida que os ‘**poms**‘ dos projetos involvidos estão corretamente escritos e que todas as informações necessárias para o build estão presentes;
- **`compile`**: compila o código-fonte.
  - compila todos os códigos do projeto, inclusive os códigos das classes de teste;
- **`test`**: executa os testes unitários (JUnit, por exemplo).
  - roda os testes que estão em ‘**src/test/java**‘, e certifica-se que todos estão passando, caso contrário o build é interrompido;
- **`package`**: empacota o código compilado em um JAR, WAR, etc.
  - usa o código compilado e testado que está em ‘**src/main/java**‘ e cria um arquivo reusável, por exemplo jar;
- **`integration-test`**: executa os testes de integração.
- **`install`**: adiciona o pacote gerado ao repositório local, assim outros projetos na mesma máquina podem usar essa dependência.
  - copia o arquivo gerado para o repositório local para que esteja disponível localmente para outros projetos;
- **`deploy`**: copia o pacote final para o repositório remoto, disponibilizando-o para outros desenvolvedores e outros projetos.
  - copia o arquivo gerado para um repositório na rede ou remoto, para que esteja disponível para outros desenvolvedores.



Outros

+ `verify`: roda as verificações necessárias para se certificar que os pacotes gerados estão corretos e passam nos critérios de qualidade;
+ `clean`: limpa a pasta target que é os arquivo compialdos que serâo executados (as classes .class do Java)
  + cleans up artifacts created by prior builds
+ `site`: generates site documentation for this project

Os itens acima, nessa ordem, são passos comuns para geração de uma versão de qualquer sistema, não é mesmo?

No Maven, você pode configurar detalhadamente cada um desses passos e até incluir novos. Por exemplo, alguns frameworks que geram código-fonte usam um *goal* `generate-sources` para fazer isso.

Além disso, não é necessário executar todos os passos sempre. Você pode escolher qual deseja executar num determinado momento, mas o Maven sempre executará todos os passos anteriores.

Por exemplo, enquanto você está desenvolvendo um módulo, a cada alteração pode executar o passo `test` para executar a validação, compilação e então os testes unitários. Então você só precisa executar os passos posteriores quando tiver concluído o trabalho.

Para executar qualquer um dele basta dar na pasta raiz (onde tem o `pom.xml`)

```
mvn install (ou qualquer outro comando)
```

**RESUMO**

Em gerel usamos clena antes de todaos, sendo assim `mvn clean compile` por exmeplo
