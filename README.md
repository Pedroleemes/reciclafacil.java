# ReciclaFácil — Testes Automatizados de API

## Objetivo

Validar as APIs do ReciclaFácil utilizando BDD, Gherkin,
Cucumber, Rest Assured e JSON Schema.

## Tecnologias

- Java
- Spring Boot
- Maven
- JUnit
- Cucumber e Gherkin
- Rest Assured
- JSON Schema
- H2 para o ambiente de testes
- GitHub Actions para integração contínua

## Requisitos

- JDK compatível com o projeto
- VS Code
- Maven Wrapper do repositório

O projeto declara Java 17 no pom.xml. O JDK 21 também
pode executar esse projeto, desde que as configurações
de compilação sejam mantidas consistentes.

## Executar todos os testes

No terminal PowerShell, na raiz do projeto:

    .\mvnw.cmd clean test

## Executar somente os cenários BDD

    .\mvnw.cmd -Dtest=RunCucumberTest test

## Relatórios

Após a execução, consultar:

- target/cucumber-report.html
- target/cucumber-report.json
- target/surefire-reports/

## Cenários BDD

1. Login válido.
2. Login inválido.
3. Listagem de pontos de coleta.
4. Registro de descarte válido.
5. Rejeição de peso inválido.
6. Rejeição de ponto inexistente.
7. Atualização de agendamento.
8. Cancelamento de agendamento.

## Contratos JSON

- login-schema.json
- pontos-coleta-schema.json
- descarte-validation-schema.json

## Integração contínua

O workflow localizado em
.github/workflows/maven.yml executa os testes Maven
nos eventos configurados no GitHub Actions.

## Banco de testes

A configuração de testes utiliza H2 em memória.
Ela não deve ser confundida com a configuração Oracle
utilizada pela aplicação fora dos testes.