## A loja do "seu" Manuel
Seu Manuel deseja expandir seus negócios e vender *online*, para isso ele te contratou para tocar o desenvolvimento do sistema que vai dar suporte à operação da loja. São listados abaixo os serviços que devem ser disponibilizados pelo sistema.

### Produtos
Serviço de produtos para que seja possível cadastrar, recuperar, atualizar e deletar os produtos de sua loja.

Os produtos serão identificados por um **código** e terá as informações de **nome**, **descrição**, **estoque**, **preço** e mais alguns **atributos** que variam de produto para produto (customizáveis).

### Pedidos
Serviço de pedidos que permitirá registrar, recuperar e atualizar as vendas dos seus produtos. As informações dos pedido consistem em um **código** identificador, **data** da compra, nome do **comprador**, **estado** (ex: novo, aprovado, entregue e cancelado), valor do **frete** e a lista de **itens** que foram vendidos - cada item possui: **código** do produto; **quantidade**; e o **preço** de venda.

### Relatórios
Por fim, o sistema também deve fornecer a possibilidade de extrair um relatório que informa o **ticket médio** dado um intervalo de tempo (data inicial e final) - a definição de ticket médio é facilmente encontrada na web, mas fique à vontade para indicar a definição utilizada na solução.

## Considerações
Considere que o sistema deve:

- Recusar a criação de um pedido com item não cadastrado;
- Decrementar o estoque do(s) produto(s) sempre que um pedido é realizado;
- Evitar o cadastro de pedidos cujo item não tem estoque suficiente.


## Ferramentas utilizadas no desenvolvimento
- EclipeEE IDE para edição de códigos (Linguagem Java);
- MySQL Workbench para modelagem e criação do banco de dados (MariaDB);
- Apache Tomcat para execução e disponibilidade dos serviços Web;
- Postman para testar as entradas e saídas de dados dos serviços REST através de JSON;
- Maven para controle de dependências;
- Junit5 para execução de testes;
- Jersey para implementação de RESTfull web service;
- JavaFaker para gerar dados para inserir no banco de dados;


# Intalação
O projeto é estruturado como projeto Maven, sendo assim basta importá-lo no EclipseEE através do menu: **"File->Import"** e em seguida escolher **"Existing Maven Project"** na lista de tipos de projetos disponibilizada. Após a importação do projeto, o Maven baixará todas as dependências identificadas no arquivo **pom.xml** (isso pode demorar um pouco na primeira vez).

Para executar o projeto é necessário configurar no Eclpse o endereço local da pasta onde se encontram os arquivos do servidor Tomcat, através do menu: **Window->Preferences->Server->Runtime Environments**, veja mais detalhes no link: <https://cursos.alura.com.br/forum/topico-como-executar-diretamente-no-eclipse-o-servidor-do-tomcat-33541>.

A execução também pode ser feita copiando-se o arquivo **challenge-loja-seu-manuel.war**, que encontra-se no diretório **raiz** no projeto, colando-o no diretório **diretorioApacheTomcat/webapps** do servidor. Em seguida inicia-se o servidor através do executável **startup.sh (startup.bat em sistemas Windows)**, contido no diretório **diretorioApacheTomcat/bin**.

É necessário também importar o banco de dados para o servidor local por meio do arquivo **bd_loja_seu_manuel.sql**, que encontra-se na pasta **bd_loja_seu_manuel** na raiz do projeto. A importação pode ser feita com MySQL Workbench, phpmyadmin, mysqldump, etc.

No pacote **br.leo.lojaSeuManuel.util** existe uma classe chamada **PovoarBanco**, ela pode ser executada para preencher o banco de dados com alguns cadastros fictícios para testes e demonstrações.


# Acessando e manipulando os dados
Contanto que o projeto esteja sendo executado sem problemas no servidor podemos acessar os serviços REST da seguinte forma:


## Produtos

**Objetivo:** listar todos os produtos cadastrados

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/produtos/>

**Requisição:** GET

**Entrada:** não se aplica

**Saída:** uma lista de produtos estruturada em formato JSON, como visto em seguida

```JSON
[
    {
        "atributosCustomizaveis": [
            {
                "id": 1,
                "nome": "voluptatem",
                "valor": "vero"
            },
            {
                "id": 2,
                "nome": "provident",
                "valor": "illo"
            }
        ],
        "codigo": "prod838",
        "descricao": "Consequuntur velit saepe.",
        "estoque": 178,
        "id": 1,
        "nome": "Incredible Wool Table",
        "preco": 22.95
    },
    {
        "atributosCustomizaveis": [
            {
                "id": 3,
                "nome": "dolorem",
                "valor": "architecto"
            },
            {
                "id": 4,
                "nome": "aut",
                "valor": "aliquid"
            }
        ],
        "codigo": "prod217",
        "descricao": "Dolorem minima laborum sint et non magni rem.",
        "estoque": 113,
        "id": 2,
        "nome": "Incredible Cotton Bag",
        "preco": 79.44
    }
]
```


**Objetivo:** buscar um produto em particular através de seu ID

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/produtos/[idProduto]>
 
**Requisição:** GET

**Entrada:** id do produto na URL

**Saída:** um produto estruturado em formato JSON, como visto em seguida

```JSON
{
    "atributosCustomizaveis": [
        {
            "id": 51,
            "nome": "velit",
            "valor": "voluptatum"
        },
        {
            "id": 52,
            "nome": "voluptas",
            "valor": "inventore"
        }
    ],
    "codigo": "prod522",
    "descricao": "Et omnis voluptates repudiandae nihil ut necessitatibus.",
    "estoque": 177,
    "id": 26,
    "nome": "Practical Wool Knife",
    "preco": 86.33
}

```


**Objetivo:** inserir um produto no banco de dados

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/produtos/>
 
**Requisição:** POST

**Entrada:** dados de um produto em formato JSON no BODY da requisição, como visto em seguida

```JSON

{
    "atributosCustomizaveis": [
        {
            "nome": "velit",
            "valor": "voluptatum"
        },
        {
            "nome": "voluptas",
            "valor": "inventore"
        }
    ],
    "codigo": "prod522",
    "descricao": "Et omnis voluptates repudiandae nihil ut necessitatibus.",
    "estoque": 177,
    "nome": "Practical Wool Knife",
    "preco": 86.33
}

```

**Saída:** uma mensagem de sucesso ou erro.


**Objetivo:** atualizar os dados de um produto através de seu ID

**URL:** http://localhost:8080/challenge-loja-seu-manuel/rest/produtos/[diProduto]
 
**Requisição:** PUT

**Entrada:** dados do produto em formato JSON no BODY da requisição e o id do id do produto na URL, como visto em seguida

```JSON

{
    "atributosCustomizaveis": [
        {
            "nome": "velit",
            "valor": "voluptatum"
        },
        {
            "nome": "voluptas",
            "valor": "inventore"
        }
    ],
    "codigo": "prod522",
    "descricao": "Et omnis voluptates repudiandae nihil ut necessitatibus.",
    "estoque": 177,
    "nome": "Practical Wool Knife",
    "preco": 86.33
}

```

**Saída:** uma mensagem de sucesso ou erro.




**Objetivo:** Excluir um produto através de seu ID

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/produtos/[diProduto]>
 
**Requisição:** DELETE

**Entrada:** o id do produto na URL

**Saída:** uma mensagem de sucesso ou erro




## Pedidos

**Objetivo:** listar todos os pedidos cadastrados

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/pedidos>
 
**Requisição:** GET

**Entrada:** não se aplica

**Saída:** uma lista de pedidos estrutura em JSON, como se segue

```JSON
[
    {
        "codigo": "ped125",
        "dataCompra": "2012-12-08",
        "estado": "aprovado",
        "id": 1,
        "itensDoPedido": [
            {
                "codigoProduto": "prod476",
                "id": 1,
                "idProduto": 48,
                "nomeProduto": "Ergonomic Bronze Keyboard",
                "precoProdutoVenda": 35.98,
                "quantidade": 2,
                "valorParcial": 71.96
            },
            {
                "codigoProduto": "prod742",
                "id": 2,
                "idProduto": 43,
                "nomeProduto": "Mediocre Marble Hat",
                "precoProdutoVenda": 86.25,
                "quantidade": 4,
                "valorParcial": 345
            },
            {
                "codigoProduto": "prod425",
                "id": 3,
                "idProduto": 31,
                "nomeProduto": "Aerodynamic Rubber Bench",
                "precoProdutoVenda": 91.88,
                "quantidade": 4,
                "valorParcial": 367.52
            }
        ],
        "nomeComprador": "Frederico Franco Nogueira Neto",
        "valorFrete": 11.64,
        "valorTotal": 796.12
    },
    {
        "codigo": "ped126",
        "dataCompra": "2008-11-24",
        "estado": "aprovado",
        "id": 2,
        "itensDoPedido": [
            {
                "codigoProduto": "prod912",
                "id": 4,
                "idProduto": 40,
                "nomeProduto": "Lightweight Copper Coat",
                "precoProdutoVenda": 82.67,
                "quantidade": 3,
                "valorParcial": 248.01
            },
            {
                "codigoProduto": "prod714",
                "id": 5,
                "idProduto": 66,
                "nomeProduto": "Heavy Duty Cotton Wallet",
                "precoProdutoVenda": 37.24,
                "quantidade": 3,
                "valorParcial": 111.72
            },
            {
                "codigoProduto": "prod722",
                "id": 6,
                "idProduto": 5,
                "nomeProduto": "Rustic Rubber Hat",
                "precoProdutoVenda": 28.52,
                "quantidade": 3,
                "valorParcial": 85.56
            },
            {
                "codigoProduto": "prod330",
                "id": 7,
                "idProduto": 45,
                "nomeProduto": "Rustic Aluminum Shirt",
                "precoProdutoVenda": 29.07,
                "quantidade": 2,
                "valorParcial": 58.14
            },
            {
                "codigoProduto": "prod401",
                "id": 8,
                "idProduto": 44,
                "nomeProduto": "Rustic Silk Shoes",
                "precoProdutoVenda": 89.26,
                "quantidade": 4,
                "valorParcial": 357.04
            },
            {
                "codigoProduto": "prod476",
                "id": 9,
                "idProduto": 48,
                "nomeProduto": "Ergonomic Bronze Keyboard",
                "precoProdutoVenda": 35.98,
                "quantidade": 3,
                "valorParcial": 107.94
            }
        ],
        "nomeComprador": "Valentina Moreira Veloso Filho",
        "valorFrete": 10.73,
        "valorTotal": 979.14
    }
]
```


**Objetivo:** buscar o registro de um item em particular por meio de seu id

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/pedidos/idPedido>
 
**Requisição:** GET

**Entrada:** o id do pedido na URL

**Saída:** um pedido estruturado em JSON

```JSON
{
    "codigo": "ped194",
    "dataCompra": "2011-06-07",
    "estado": "novo",
    "id": 8,
    "itensDoPedido": [
        {
            "codigoProduto": "prod714",
            "id": 39,
            "idProduto": 66,
            "nomeProduto": "Heavy Duty Cotton Wallet",
            "precoProdutoVenda": 37.24,
            "quantidade": 3,
            "valorParcial": 111.72
        },
        {
            "codigoProduto": "prod766",
            "id": 40,
            "idProduto": 17,
            "nomeProduto": "Incredible Wooden Clock",
            "precoProdutoVenda": 25.17,
            "quantidade": 4,
            "valorParcial": 100.68
        },
        {
            "codigoProduto": "prod454",
            "id": 41,
            "idProduto": 65,
            "nomeProduto": "Awesome Marble Lamp",
            "precoProdutoVenda": 97.14,
            "quantidade": 3,
            "valorParcial": 291.42
        },
        {
            "codigoProduto": "prod336",
            "id": 42,
            "idProduto": 59,
            "nomeProduto": "Awesome Paper Lamp",
            "precoProdutoVenda": 81.9,
            "quantidade": 3,
            "valorParcial": 245.7
        },
        {
            "codigoProduto": "prod239",
            "id": 43,
            "idProduto": 83,
            "nomeProduto": "Ergonomic Steel Bag",
            "precoProdutoVenda": 96.49,
            "quantidade": 3,
            "valorParcial": 289.47
        }
    ],
    "nomeComprador": "Eduardo Macedo Nunes",
    "valorFrete": 11.7,
    "valorTotal": 1050.69
}
```


**Objetivo:** cadastrar um novo pedido

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/pedidos>
 
**Requisição:** POST

**Entrada:** dados do novo pedido em formato JSON no BODY da requisição 

```JSON
{
    "codigo": "ped304",
    "dataCompra": "2017-08-20",
    "estado": "novo",
    "itensDoPedido": [
        {
            "idProduto": 66,
            "quantidade": 10
        },
        {
            "idProduto": 100,
            "quantidade": 3
        },
        {
            "idProduto": 88,
            "quantidade": 2
        }
    ],
    "nomeComprador": "Isaac Moreira Castro",
    "valorFrete": 39.21
}
```

**Saída:** uma mensagem de sucesso ou erro




**Objetivo:** atualizar os dados de um pedido cadastrado no banco de dados

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/pedidos/idPedido>
 
**Requisição:** PUT

**Entrada:** dados do pedido em formato JSON no BODY da requisição e o id do pedido na URL

```JSON
{
    "codigo": "ped304",
    "dataCompra": "2017-08-20",
    "estado": "entregue",
    "itensDoPedido": [
        {
        	"id": 224,
            "idProduto": 66,
            "quantidade": 1
        },
        {
        	"id": 225,
            "idProduto": 100,
            "quantidade": 4
        },
        {
        	"id": 226,
            "idProduto": 88,
            "quantidade": 2
        }
    ],
    "nomeComprador": "Isaac Moreira Castro",
    "valorFrete": 49.21
}
```

**Saída:** uma mensagem de sucesso ou erro  




## Relatórios

**Objetivo:** gerar um relatório de ticket médio relacionado à quantidade de **vendas** e o somatório total do valor de cada venda

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/relatorios/ticketmedioporvendas>
 
**Requisição:** POST

**OBS:** a definição de ticket médio usada encontra-se em: <https://www.agendor.com.br/blog/o-que-e-ticket-medio/>

**Entrada:** um período com data inicial e final do relatório em formato JSON

```JSON
{
    "dataInicial": "2010-01-01",
    "dataFinal": "2017-12-31"
}
```
 

**Saída:** o relatório em JSON

```JSON
{
    "type": "relatorioTicketMedioPorVendas",
    "periodo": {
        "dataFinal": "2017-12-31",
        "dataInicial": "2010-01-01"
    },
    "valorTotalVendas": 19785.56,
    "quantidadeVendas": 30,
    "ticketMedio": 659.52
}
```


**Objetivo:** gerar um relatório de ticket médio relacionado à quantidade de **compradores** e o somatório total do valor de cada venda

**URL:** <http://localhost:8080/challenge-loja-seu-manuel/rest/relatorios/ticketmedioporcompradores>
 
**Requisição:** POST

**OBS:** a definição de ticket médio usada encontra-se em: <https://www.agendor.com.br/blog/o-que-e-ticket-medio/>

**Entrada:** um período com data inicial e final do relatório em formato JSON

```JSON
{
    "dataInicial": "2016-01-01",
    "dataFinal": "2016-12-31"
}
```
 

**Saída:** o relatório em JSON

```JSON
{
    "type": "relatorioTicketMedioPorCompradores",
    "periodo": {
        "dataFinal": "2016-12-31",
        "dataInicial": "2016-01-01"
    },
    "valorTotalVendas": 3689.38,
    "quantidadeCompradores": 5,
    "ticketMedio": 737.88
}
```


