## Integração contínua com GitHub Actions

O workflow está localizado em `.github/workflows/ci-cd.yml`.

A execução ocorre automaticamente em atualizações da branch `main`
e em Pull Requests direcionados à branch `main`. Também pode ser
iniciada manualmente pela aba Actions do GitHub.

O pipeline possui duas etapas:

1. Executa os testes automatizados com Maven, incluindo os cenários
   BDD escritos em Gherkin.
2. Se os testes passarem, constrói a imagem Docker para validar
   a configuração do container.

O processo não depende de credenciais do Azure e não publica
a imagem em um registro externo nem realiza implantação.