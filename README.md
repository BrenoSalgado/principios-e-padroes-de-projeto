# Princípios e Padrões de Projeto

Laboratórios em Java feitos na disciplina de **Princípios e Padrões de Projeto (PPP)**.
Cada pasta é um projeto independente que estuda um ou mais padrões do catálogo GoF
(e a Simple Factory, que é um idioma muito usado, não um padrão GoF).

| Pasta | Tema | Padrões |
|---|---|---|
| [`LabSingleton-Concessionaria`](LabSingleton-Concessionaria) | Venda de veículos | Singleton, Strategy, Observer, Template Method, Adapter |
| [`Observer-JornalDigital`](Observer-JornalDigital) | Jornal que notifica apps de assinantes | Observer, Strategy |
| [`RPG-Arena`](RPG-Arena) | Arena de personagens de RPG | Simple Factory, Strategy, Adapter |
| [`Lab09-Cafeteria`](Lab09-Cafeteria) | Pedidos em uma cafeteria | Command, Simple Factory, Factory Method |

---

## Padrões estudados

### Singleton (criacional)
Garante que uma classe tenha **uma única instância** e oferece um ponto de acesso global a ela.
O construtor é `private`, a instância fica em um atributo `static` e é obtida por `getInstance()`.

- `LabSingleton-Concessionaria`: `Vendedor` é o único vendedor do sistema; `Vendedor.getInstance()`
  cria o objeto na primeira chamada (lazy) e devolve sempre o mesmo (`v1 == v2` é `true`).

### Strategy (comportamental)
Encapsula uma família de algoritmos em classes separadas e intercambiáveis. O objeto que usa o
algoritmo (contexto) só conhece a interface, e a estratégia pode ser **trocada em tempo de execução**,
sem `if/else` para escolher o comportamento.

- `LabSingleton-Concessionaria`: `EstrategiaDesconto` → `DescontoNormal`, `DescontoPromocional`, `DescontoFuncionario`.
- `Observer-JornalDigital`: `EstrategiaApresentacao` → `ApresentacaoResumida`, `ApresentacaoDetalhada`.
- `RPG-Arena`: `EstrategiaAtaque` → `AtaqueNormal`, `AtaqueEspecial` (o guerreiro troca de ataque na mesma instância).

### Observer (comportamental)
Define uma relação um-para-muitos: quando o **sujeito** muda de estado, todos os **observadores**
cadastrados são notificados automaticamente. O sujeito só conhece a interface do observador.
Nos dois labs foi usado o modelo **pull**: `atualizar()` não recebe parâmetros e o observador
consulta o sujeito para obter os dados.

- `LabSingleton-Concessionaria`: `Vendedor` (sujeito) avisa `Estoque`, `Financeiro` e `Auditoria` (`ObservadorVenda`) a cada venda.
- `Observer-JornalDigital`: `LeFigaroDigital` (`JornalDigital`) avisa `AppCelular`, `AppTablet` e `AppWeb` (`AssinanteApp`) a cada notícia; assinantes podem se inscrever e cancelar.

### Template Method (comportamental)
Define o **esqueleto de um algoritmo** em um método `final` da superclasse e deixa alguns passos
para as subclasses implementarem, sem que elas possam mudar a ordem geral.

- `LabSingleton-Concessionaria`: `ProcessamentoVenda.executarVenda()` fixa o fluxo
  (preparar → calcular preço → concluir venda → gerar comprovante); `VendaPresencial` e
  `VendaOnline` implementam apenas `prepararVenda()`.

### Adapter (estrutural)
Converte a interface de uma classe existente (geralmente legada ou de terceiros, que não pode ser
alterada) na interface que o cliente espera, por meio de um objeto intermediário.

- `LabSingleton-Concessionaria`: `DocumentoPdf` adapta o componente legado `ComponentePdf` à interface `Documento`.
- `RPG-Arena`: `MonstroAdaptado` faz o legado `MonstroAntigo` funcionar como um `Personagem`.

### Simple Factory (idioma de criação)
Centraliza a criação de objetos em uma classe/método que decide **qual classe concreta instanciar**
a partir de um parâmetro, tirando o `new` (e o `switch`) do código cliente.

- `RPG-Arena`: `FabricaPersonagens.criarPersonagem("mago", ...)`.
- `Lab09-Cafeteria`: `PedidoFactory.criarPedido("CAFE", ...)`.

### Factory Method (criacional)
Define um método abstrato de criação na superclasse e deixa **cada subclasse decidir** qual objeto
concreto criar. Para um novo produto, cria-se um novo criador, sem alterar os existentes.

- `Lab09-Cafeteria`: `CriadorPedido` → `CriadorCafe`, `CriadorCha`, `CriadorChocolate`.

### Command (comportamental)
Encapsula uma requisição como um objeto, com `execute()` e `undo()`. Quem pede a ação (invocador)
não precisa saber quem a executa (receptor) nem como; isso permite filas, histórico e desfazer.

- `Lab09-Cafeteria`: `Garcom` (invocador) leva e cancela pedidos; `PedidoCafe`, `PedidoCha` e
  `PedidoChocolate` (comandos) chamam o `Cozinheiro` (receptor).

---

## Princípios aplicados

- **Programar para interfaces, não para implementações**: contextos e sujeitos dependem só de
  `EstrategiaDesconto`, `ObservadorVenda`, `Documento`, `Command` etc.
- **Aberto/Fechado (OCP)**: novas estratégias, observadores, pedidos e personagens são acrescentados
  criando classes novas, sem modificar as existentes.
- **Composição em vez de herança**: o comportamento variável (desconto, ataque, apresentação) é
  um objeto guardado no contexto, não uma subclasse.
- **Responsabilidade única (SRP)** e **baixo acoplamento**: cada setor/estratégia/comando cuida de uma
  coisa, e o sujeito não conhece os detalhes de quem o observa.

---

## Como executar

Requer JDK 8 ou superior. Em cada pasta:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out <ClassePrincipal>
```

| Pasta | Classe principal |
|---|---|
| `LabSingleton-Concessionaria` | `Programa` |
| `Observer-JornalDigital` | `Teste1` ou `Teste2` |
| `RPG-Arena` | `Programa` (pede os nomes dos personagens no terminal) |
| `Lab09-Cafeteria` | `CafeteriaApp` |

> No terminal do Windows, se os acentos saírem trocados, rode `chcp 65001` antes
> ou use `java -Dstdout.encoding=UTF-8 -cp out <ClassePrincipal>`.
