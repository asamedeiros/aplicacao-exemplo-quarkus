Quer contribuir para o projeto? Que ótimo! Basta seguir algumas poucas orientações:

# Orientações Gerais
- Ao iniciar uma contribuição, faça um Fork, faça suas modificações, e depois um Pull Request.
- Recomendo contribuir com base em uma das [issues](https://github.com/rinaldodev/aplicacao-exemplo-quarkus/issues) já criadas, elas geralmente dão a direção do projeto.
- Tenha certeza de que já leu todo o [README](https://github.com/rinaldodev/aplicacao-exemplo-quarkus/blob/master/README.md) e já consegue executar seu projeto com sucesso.
- Lembre-se de atualizar JavaDocs, comentários e o README, se for o caso.

# Checks
- Os pushes e pull requests serão validados pelo [Workflow](https://github.com/rinaldodev/aplicacao-exemplo-quarkus/blob/master/.github/workflows/maven.yml) configurado no [GitHub Actions](https://github.com/rinaldodev/aplicacao-exemplo-quarkus/actions) do repositório. Ele garante:
  - Compilação e Empacotamento do código funcionando.
  - Testes unitários passando e cobrindo 90% de classes Rest.
  - Testes de mutação passando e cobrindo 90% das mutações.
  - Testes de integração passando.
  - Quality Gate do Sonar passando.
- Caso qualquer um desses itens não seja validado, o Push não passará pelos Checks, e vou pedir pra você melhorar o Pull Request. :D
