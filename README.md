# RinaldoDev - Aplicação de Exemplo Quarkus

Este projeto é uma base para iniciar novos projetos utilizando Quarkus.

Algumas escolhas são baseadas na minha opinião sobre alguns conceitos, como:

- Utilizar Panache com repositórios e não herdar as entidades de PanacheEntity.
- Focar na facilidade de executar testes unitários de verdade, separados dos testes de integração.
- Focar em um design que facilite a execução de testes unitários com Mocks.
- Entre outros que depois adiciono aqui.

Então não entenda como "a melhor forma" de criar um projeto Quarkus, mas sim como um exemplo de utilização de:

- Extensões do Quarkus.
- Especificações do MicroProfile.
- Bibliotecas que acho que "encaixam bem" com o Quarkus.

# Especificações MicroProfile, Extensões e Bibliotecas

A seguir é uma lista do que está sendo exemplificado neste projeto até o momento.

Especificação MicroProfile:
- JAX-RS
- JSON-B
- CDI
- MicroProfile Fault Tolerance
- MicroProfile Health Check
- MicroProfile Config

Extensões Quarkus (fora do MicroProfile):
- Hibernate Panache
- JDBC PostgreSQL

Bibliotecas externas:
- Log com SLF4J
- MapStruct

Testes Unitários:
- JUnit5/Jupiter
- Mockito

Testes integrados:
- Quarkus 
- Rest Assured
- TestContainers

Aceito sugestões de novas extensões/bibliotecas para exemplificar. :)

# Configuração de IDE

- Esse projeto usa Maven, então importe como projeto Maven na sua IDE.
- Por conta do uso do MapStruct, o projeto requer que Java Annotation Processing esteja habilitado na IDE. Veja aqui como habilitar nas principais IDEs: https://mapstruct.org/documentation/ide-support/

# Executando

Esse projeto é fortemente contruído ao redor do Quarkus! Veja mais em: https://quarkus.io/.

## Dependências

- Docker: É necessário ter o Docker instalado para rodar os testes integrados ou executar a aplicação, pois vários exemplos utilizam imagens do docker. Instalação do Docker: https://docs.docker.com/install/
- JDK 11: É necessário ter uma JDK 11 disponível localmente e configurada no JAVA_HOME.

## Testes unitários

Para executar os testes unitários:
```
./mvnw test
```

## Testes integrados

Para executar os testes unitários e integrados:
```
./mvnw verify
```

## Rodando a aplicação no modo dev

Primeiro inicie um banco PostgreSQL em um container Docker com o comando:
```
docker run -e POSTGRES_PASSWORD=quarkus_dev -e POSTGRES_USER=quarkus_dev -e POSTGRES_DB=frutas -p 127.0.0.1:5432:5432/tcp postgres:9.6.12
```

Depois execute a aplicação no modo dev, que permite live coding (salvou, tá visível), com o comando abaixo:
```
./mvnw quarkus:dev
```

## Empacotando

Para empacotar a aplicação utilize o comando `./mvnw package`.
Esse comando irá criar o jar `quarkus-example-app-X.X.X-runner.jar` no diretório `/target`.

Para executar a aplicação empacotada utilize o comando `java -jar target/quarkus-example-app-X.X.X-runner.jar`.

Perceba que a aplicação não precisa de implantada em um servidor.

## Executável nativo

Com o Quarkus é possível criar imagens nativas, super leves e com tempo de inicialização super rápido. 
A princípio, esse não é o objetivo desse projeto de exemplo. Caso queira saber mais, veja aqui: https://quarkus.io/guides/building-native-image-guide.

# Dúvidas de utilização

A maior parte do que está sendo usado contém documentação própria, então não convém explicar a utilização nesse projeto, apenas exemplificar.

## Dúvidas de Quarkus ou MicroProfile

- Quarkus: https://quarkus.io/
- MicroProfile: https://microprofile.io/

## Dúvidas nos Testes Unitários

- JUnit5/Jupiter: https://junit.org/junit5/docs/current/user-guide/
- Mockito: https://javadoc.io/static/org.mockito/mockito-core/3.2.4/org/mockito/Mockito.html

## Dúvidas nos Testes Integrados

- Quarkus: https://quarkus.io/guides/getting-started-testing
- Rest Assured: https://github.com/rest-assured/rest-assured/wiki/usage
- TestContainers: https://www.testcontainers.org/quickstart/junit_5_quickstart/
