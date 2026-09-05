# CafeteriaProjeto

API REST para gerenciamento de uma cafeteria, com bebidas pré-definidas, personalização de ingredientes, criação de pedidos, cálculo do valor final e controle do status do atendimento.

Este é um projeto acadêmico desenvolvido para solucionar um problema apresentado durante a aula de **Design de Software**. A proposta foi transformar as regras de uma cafeteria em uma aplicação organizada em camadas, separando responsabilidades e aplicando conceitos de modelagem, persistência, validação e tratamento de erros.

## Desafio proposto

O desafio consiste em criar um sistema para gerenciar uma cafeteria na qual o cliente possa escolher entre algumas bebidas pré-definidas:

- Café expresso
- Café com leite
- Cappuccino
- Chocolate quente

Além das opções do cardápio, o cliente deve poder personalizar sua bebida misturando ingredientes e escolhendo suas respectivas quantidades. Alguns exemplos possíveis são:

- Café expresso com chocolate quente.
- Café com leite com quantidade dupla de leite.
- Cappuccino com leite extra e uma dose de expresso.

O sistema deve receber essas combinações, considerar a quantidade de cada bebida e ingrediente e atualizar automaticamente o valor apresentado ao cliente final.

## Solução desenvolvida

A solução representa as bebidas principais como **itens** e os ingredientes extras como **adicionais**. Ao criar um pedido, cada item informa sua própria quantidade e recebe uma lista de adicionais, que também possuem quantidades independentes.

Essa modelagem permite montar diferentes combinações sem precisar criar uma nova classe ou rota para cada variação de bebida. Os preços cadastrados são consultados no banco de dados e usados para calcular o valor final de forma dinâmica.

## Funcionalidades

- Cadastrar, listar, atualizar e excluir itens do cardápio.
- Cadastrar, listar, atualizar e excluir adicionais.
- Representar as opções pré-definidas de café como itens do cardápio.
- Personalizar bebidas combinando diferentes ingredientes.
- Definir quantidades independentes para bebidas e adicionais.
- Criar pedidos com diferentes quantidades de itens e adicionais.
- Calcular automaticamente o valor final do pedido.
- Consultar um pedido pelo UUID.
- Alterar o status do pedido de `PENDENTE` para `FECHADO`.
- Retornar respostas adequadas para duplicidades, recursos inexistentes e pedidos já fechados.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA e Hibernate
- Jakarta Validation
- PostgreSQL
- Lombok
- Maven

## Organização do projeto

O código está dividido nas seguintes responsabilidades:

- `controller`: disponibiliza as rotas HTTP da API.
- `service`: concentra as regras de negócio e o cálculo dos pedidos.
- `database`: contém as entidades e os objetos armazenados no banco.
- `repository`: realiza o acesso aos dados com Spring Data JPA.
- `dto`: define os formatos de entrada e saída da API.
- `exception`: reúne as exceções e o tratamento global de erros.

## Modelo de pedido

Um pedido contém o nome do cliente e uma lista de itens. Cada item informa sua quantidade e pode possuir adicionais com quantidades próprias.

Exemplo:

```json
{
  "nomeCliente": "Maria Silva",
  "itens": [
    {
      "nome": "Cappuccino",
      "adicionais": [
        {
          "nome": "Leite vegetal",
          "quantidade": 2
        }
      ],
      "quantidade": 2
    }
  ]
}
```

O valor de cada grupo é calculado da seguinte forma:

```text
(valor do item + soma dos adicionais e suas quantidades) × quantidade do item
```

## Rotas

### Adicionais

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/v1/adicionais` | Cadastra um adicional |
| `GET` | `/v1/adicionais` | Lista os adicionais |
| `PUT` | `/v1/adicionais/id/{nome}` | Atualiza um adicional pelo nome |
| `DELETE` | `/v1/adicionais/id/{nome}` | Exclui um adicional pelo nome |

### Itens

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/v1/itens` | Cadastra um item |
| `GET` | `/v1/itens` | Lista os itens |
| `PUT` | `/v1/itens/id/{nome}` | Atualiza um item pelo nome |
| `DELETE` | `/v1/itens/id/{nome}` | Exclui um item pelo nome |

### Pedidos

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/v1/pedidos` | Cria um pedido e retorna seu UUID |
| `GET` | `/v1/pedidos/id/{uuid}` | Consulta um pedido |
| `PATCH` | `/v1/pedidos/id/{uuid}` | Fecha um pedido pendente |

## Como executar

### Pré-requisitos

- JDK 21
- Maven
- PostgreSQL
- Banco de dados chamado `cafeteria`

Confira e ajuste a URL, o usuário e a senha do PostgreSQL em [`application.yaml`](src/main/resources/application.yaml) antes de iniciar a aplicação.

Na raiz do projeto, execute:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

> **Atenção:** a configuração atual utiliza `spring.jpa.hibernate.ddl-auto: create-drop`. Por isso, as tabelas são recriadas ao iniciar e removidas ao encerrar a aplicação.

## Testes

Para executar os testes automatizados do Maven:

```bash
mvn test
```

O arquivo [`adicionais.http`](adicionais.http) contém uma suíte para o HTTP Client do IntelliJ IDEA. Ela prepara os dados, testa os fluxos principais e de erro, captura automaticamente o UUID do pedido e remove os itens e adicionais criados ao final.

Com a aplicação em execução, abra o arquivo no IntelliJ IDEA e execute as requisições na ordem em que aparecem.
# DesafioDesignCafeteria
