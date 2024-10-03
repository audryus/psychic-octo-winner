# Desafio Ekan 


## Arquitetura
![Alt text](https://herbertograca.com/wp-content/uploads/2017/03/2008-onion-architecture5.png)

cotroller -> usecase

usecase -> domain serviceS
> usecase: Regras da aplicação

domain service -> domain repo
> domain Service: Regra do dominio

A ideia é que cada dominio possa ser separados em um "nano-serviço", pensando em `clustering`.

### Erros
Erro oriundos de regras irão retornar um JSON padrão:

```json
{
    "rpl": "ERR_*"
}
```

Cada "regra" pode ter um status code diferente.
Este retorno pode ser usado pelo `client` para fazer algum tratamento adequado.

### Qualidade
Durante o desenvolvimento, na IDE, foi utilizado o SonarLint.

## Execução
> docker compose up
