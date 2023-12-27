# Library API

[![codecov](https://codecov.io/gh/PabloaRuiz/library-api.v2/branch/main/graph/badge.svg)](https://codecov.io/gh/PabloaRuiz/library-api.v2/branch/main)

Este projeto é uma API de biblioteca desenvolvida utilizando o Quarkus, o Supersônico Subatômico Java Framework.

Se você deseja aprender mais sobre o Quarkus, visite o site oficial: [Quarkus](https://quarkus.io/).

## Sobre o Projeto

O objetivo principal deste projeto é fornecer uma API de biblioteca reescrita utilizando os princípios do Domain-Driven Design (DDD). O DDD é uma abordagem de design de software que enfatiza a modelagem do domínio do negócio para garantir uma implementação mais alinhada com as necessidades reais da aplicação.

## Executando a Aplicação em Modo de Desenvolvimento

Você pode executar a aplicação em modo de desenvolvimento, que permite a codificação ao vivo, usando o seguinte comando:

```shell script
./mvnw compile quarkus:dev
```

NOTA: Quarkus inclui uma interface de usuário de desenvolvimento disponível
      apenas no modo de desenvolvimento em http://localhost:8080/q/dev/.

Empacotando e Executando a Aplicação
A aplicação pode ser empacotada usando o seguinte comando:

```shell script
./mvnw package
```
Isso produz o arquivo quarkus-run.jar no diretório target/quarkus-app/.
Este não é um über-jar, pois as dependências são copiadas para o diretório target/quarkus-app/lib/.

A aplicação agora é executável usando java -jar target/quarkus-app/quarkus-run.jar.

Se você quiser construir um über-jar, execute o seguinte comando:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```
A aplicação, agora empacotada como um über-jar, pode ser executada usando java -jar target/*-runner.jar.

Criando um Executável Nativo
Você pode criar um executável nativo usando:

```shell script
./mvnw package -Dnative
```
Ou, se você não tiver o GraalVM instalado, pode executar a compilação do executável nativo em um contêiner usando:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```
Em seguida, você pode executar seu executável nativo com: 

```shell script
./target/library-1.0.2-SNAPSHOT-runner
```

Para obter mais informações sobre a construção de executáveis nativos, consulte Quarkus - Maven Tooling.
