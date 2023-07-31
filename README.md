# hidden-godfather-customer-api
Api de Cadastro do Padrinho Secreto

![technology java](https://img.shields.io/badge/technology-Java-purple.svg)
![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)
![technology Webflux](https://img.shields.io/badge/tecnology-WebFlux-green)

# Vídeo do Projeto

| Padrinho Secreto |
|:---------------------------------------:|
|  ![][PadrinhoSecreto]  |
<details>
  <br> Videos<br>
    <br>- https://www.loom.com/share/47a9e7d05960488583d56b17e01ff911?sid=5b937b3f-3f93-468e-a91c-2cbde2ddd8c6
</details>

- ### Pré-requisitos
    - [**Java 17**](https://www.oracle.com/java/technologies/downloads/#java17)
    - [**Gradle**](https://docs.gradle.org/current/userguide/userguide.html) | _or use the wrapper ./gradlew_
    - [**Spring Boot 2**](https://spring.io/projects/spring-boot)
    - [**Docker**](https://docs.docker.com/docker-for-mac/install/#download-docker-for-mac)


## Gerando docker

````
./gradlew docker
````

## Rodando a aplicação:

A aplicação irá subir na porta 8080


### Instalando dependências

````
./gradlew clean build
````

### Rodando os testes

```
./gradlew clean test
```

### Via IDE

Em `Run/Debug Configuration`, crie uma configuração Gradle e define conforme as opções abaixo:

Gradle project

```
customer
```

Task

```
bootRun
```

### Via terminal

Execute o comando na pasta raiz do projeto

````
./gradlew dockerRun
````


### Executando os testes

Execute o comando abaixo para executar os testes da aplicação.

```./gradlew clean test```

## Documentação no swagger

 ````
http://localhost:8080/docs.html
````
[PadrinhoSecreto]: https://www.loom.com/share/47a9e7d05960488583d56b17e01ff911?sid=68d48a12-0b64-4eaf-b3ae-962b71710035