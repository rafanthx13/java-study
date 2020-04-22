# LibraryAPI - Deploy e In com Travis

[TOC]

## Integração contínua (Continuous Integration - CI)

### O que é CI

> Youtube - Codigo Fonte TV

Em Resumo: Automatizaçâo de testse e Builds quando se é feito commits. Trabalha que as atualizações das features sejam integradas corretamente ao projeto principal.  Precisa de:
1. Sistema de controle versionamento; 
2. automzatizçao de build (toda vez que for gerada uma versâo nova, vai automaticamente mudar a anteriro); 
3. Self-Testing Build: Testse automatizados => TDD; 
4. Integração diária (fazer commit e pull diários)
+ Ferramentas: Jenkis, (Travis CI : travis.yml), gitLab CI, Hudson (antecessor do Jenkis), Bamboo.

> https://www.opus-software.com.br/o-que-e-integracao-continua/

Integração contínua (continuous integration) é uma prática de desenvolvimento de software que visa tornar a integração de código mais eficiente, através de builds e testes automatizados.

As metodologias ágeis propõem que o processo de desenvolvimento de software seja realizado com entregas mais frequentes.

Como consequência, o trabalho necessário para reunir, integrar e testar todo o código desenvolvido pela equipe em um repositório central também se tornou mais frequente.

Nesse contexto, a Integração Contínua surge para reduzir o trabalho manual necessário nesse processo de integração de código.

Além disso, com a automação de testes, o trabalho de correção de bugs é facilitado, garantindo que o software esteja funcionando após cada alteração.

Para adotar a Integração Contínua, é fundamental utilizar ferramentas de controle de versões, como o Gitlab ou Github.

Soluções como Jenkins e Hudson também são importantes para coordenar os builds, testes e integrações de código.

### Benefícios de CI

Para o time de desenvolvimento, as práticas de integração, entrega e implantação contínua permitem que o trabalho minimizado, reduzindo erros e viabilizando a disponibilização do software em ciclos menores.

Para o negócio, o principal benefício do uso dessas abordagens é a diminuição do tempo necessário para lançar atualizações do produto.

Em mercados competitivos, isso representa uma grande vantagem para empresas que desejam atender rapidamente as demandas de seus usuários.

Além disso, ao disponibilizar novas features para os usuários com maior frequência, a empresa tem a possibilidade de tornar seu ciclo de aprendizado mais eficiente.

## Usando Travis

### Deploy Travis CI

fonética: /trêvis ssiai

O que é: O Travis CI é um serviço de integração contínua hospedado, usado para criar e testar projetos de software hospedados no GitHub e Bitbucket. Resumo: você faz o push no remote e automaticamente vai detectar, executa os procedimento e gerar o build e se tudo der sucesso, faz o deploy, tudo isso automaticamente.

### Como fazer

Cria o arquivo `.travis.yml`


````yml
language: java
jdk:
  - oraclejdk8
dist: trusty
before_install:
  - chmod +x mvnw
````
Onde
+ linguagem: java (pois suporta várias)
+ Jdk será o Java8
+ Dist: trusty (Serve para dizer que essa distribuição é uma distribuição confiável), pois o Travis vai rodar nas versões Java masi atuais
+ `before_install: \n - chmod +x mvnw`: Isso permite que no build lá no travis possa acessar esse arquivo, pois ele vai pedir para exeuctar e não terá acesso
