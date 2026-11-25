> **Nota:** este roteiro descreve a primeira versão do laboratório (Singleton + Strategy + Observer).
> Depois foram acrescentados **Template Method** (`ProcessamentoVenda`, `VendaPresencial`, `VendaOnline`)
> e **Adapter** (`DocumentoPdf` adaptando `ComponentePdf`), e o `Programa` atual não usa mais
> `Auditoria` e `DescontoFuncionario`. O diagrama em `uml/` também é da primeira versão.

# Laboratório — Singleton Integrado (Singleton + Strategy + Observer)

Sistema de venda de veículos em Java. O `Vendedor` é o Singleton central, usa uma
`EstrategiaDesconto` (Strategy) para calcular o preço final e notifica os setores
interessados (`ObservadorVenda`, modelo **pull**) quando uma venda é concluída.

## Estrutura

```
src/
├── Vendedor.java              Singleton + contexto do Strategy + sujeito concreto do Observer
├── EstrategiaDesconto.java    interface Strategy
├── DescontoNormal.java        2% de desconto
├── DescontoPromocional.java   15% de desconto
├── DescontoFuncionario.java   20% de desconto   (questão rápida — extra)
├── ObservadorVenda.java       interface Observer — atualizar() sem parâmetros
├── Estoque.java               observador concreto
├── Financeiro.java            observador concreto
├── Auditoria.java             observador concreto (questão rápida — extra)
└── Programa.java              classe de teste
uml/
├── diagrama.png / diagrama.svg   diagrama de classes UML
└── diagrama.dot                  fonte Graphviz do diagrama
```

## Como compilar e executar

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Programa
```

> No terminal do Windows, se os acentos saírem trocados, rode `chcp 65001` antes,
> ou execute com `java -Dstdout.encoding=UTF-8 -cp out Programa`.

## Saída obtida

```
Inicializando vendedor...
Vendedor: Carlos Silva | Endereço: Av. Brasil, 1000 - Uberlândia/MG | Email: carlos@concessionaria.com
Venda: Veículo A — preço final normal (2%): R$ 98.000,00
Estoque notificado: Veículo A (baixa no estoque)
Financeiro notificado: Veículo A — receita de R$ 98.000,00
Venda: Veículo B — preço final promocional (15%): R$ 68.000,00
Estoque notificado: Veículo B (baixa no estoque)
Financeiro notificado: Veículo B — receita de R$ 68.000,00
Mesmo vendedor? true

--- Extras: DescontoFuncionario + Auditoria ---
Venda: Veículo C — preço final funcionário (20%): R$ 40.000,00
Estoque notificado: Veículo C (baixa no estoque)
Financeiro notificado: Veículo C — receita de R$ 40.000,00
Auditoria notificada: Veículo C — registrado em log com valor R$ 40.000,00
```

"Inicializando vendedor..." aparece **uma única vez**, mesmo com duas chamadas a
`getInstance()` — evidência da criação lazy e única.

## Checklist das restrições

| Restrição | Onde está |
|---|---|
| Construtor `private` (sem `new Vendedor()` fora da classe) | `private Vendedor()` — `new Vendedor()` em outra classe gera *"Vendedor() has private access"* |
| Instância em `private static Vendedor instancia` | atributo de `Vendedor` |
| Acesso por `public static Vendedor getInstance()` | cria somente quando `instancia == null` (lazy) |
| Métodos de negócio não `static` | o único membro `static` de `Vendedor` é `instancia` + `getInstance()` |
| Sem `if/else` para escolher o desconto | `calcularPrecoFinal` apenas delega para `estrategia.calcular(preco)` |
| Strategy trocável em tempo de execução | `setEstrategia(...)` entre a 1ª e a 2ª venda |
| `atualizar()` sem parâmetros (pull) | observadores consultam `getUltimoVeiculoVendido()` e `getUltimoPrecoFinal()` |
| Observadores guardam referência ao `Vendedor` recebida no construtor | campo `private final Vendedor vendedor` |
| Ordem em `concluirVenda`: calcula → armazena → notifica | comentada passo a passo no método |
| `Vendedor` conhece apenas a interface `ObservadorVenda` | coleção `List<ObservadorVenda>`; nenhuma menção a `Estoque`/`Financeiro` |

Decisão de projeto: `Vendedor` **não** instancia nenhuma estratégia concreta (nem
como padrão), para não depender de `DescontoNormal`. Se nenhuma estratégia for
definida, `calcularPrecoFinal` falha com uma mensagem clara (`Objects.requireNonNull`).

## Questões para preparação

**1. Por que o construtor de `Vendedor` deve ser `private`?**
Para que nenhuma outra classe consiga executar `new Vendedor()`. Sem isso, qualquer
parte do código poderia criar outros vendedores e a garantia de instância única
deixaria de existir. O controle da criação fica exclusivamente dentro da classe.

**2. Qual é o papel de `private static Vendedor instancia`?**
Guardar a única instância. Por ser `static`, pertence à classe (e não a um objeto),
então sobrevive entre chamadas a `getInstance()` e é compartilhada por todo o sistema.
Por ser `private`, ninguém de fora consegue sobrescrevê-la (por exemplo, com `null`).

**3. Por que `getInstance()` precisa ser `static`, enquanto os métodos de negócio normalmente não são?**
Porque ele é chamado *antes* de existir um objeto — e o construtor é privado, então
não há outra forma de obter a referência. Um método de instância exigiria já ter um
`Vendedor` para chamá-lo (problema do ovo e da galinha). Já os métodos de negócio
operam sobre o estado do objeto (nome, estratégia, observadores, última venda), por isso
são de instância.

**4. O que aconteceria se `getInstance()` criasse um novo objeto em todas as chamadas?**
Deixaria de ser Singleton e viraria uma simples fábrica. Cada chamada devolveria um
vendedor "vazio": sem nome, sem estratégia e sem observadores cadastrados. `v1 == v2`
seria `false`, e uma venda feita por `v2` não notificaria `Estoque` e `Financeiro`,
que foram cadastrados em `v1` — o estado ficaria fragmentado e inconsistente.
"Inicializando vendedor..." também apareceria várias vezes.

**5. Por que `v1 == v2` deve retornar `true`?**
Porque `==` compara referências, e as duas chamadas a `getInstance()` devolvem o mesmo
objeto armazenado em `instancia` — ele só é criado na primeira chamada. É a prova de
que existe uma única instância.

**6. Qual problema de projeto é resolvido por Strategy neste sistema?**
A variação da regra de desconto. Sem Strategy, `Vendedor` teria um `if/else` ou `switch`
crescente com cada política, violando o princípio aberto/fechado e misturando
responsabilidades. Com Strategy, cada política é uma classe isolada e intercambiável
em tempo de execução, e `Vendedor` apenas delega o cálculo.

**7. Por que `Vendedor` não deveria conhecer detalhes internos de `Estoque` e `Financeiro`?**
Para manter baixo acoplamento. `Vendedor` só depende da interface `ObservadorVenda`
e do método `atualizar()`. O que cada setor faz com a informação (dar baixa, registrar
receita) é responsabilidade dele. Assim é possível alterar, remover ou acrescentar setores
sem modificar `Vendedor`, e testar cada parte isoladamente.

**8. O que muda no sistema quando uma nova estratégia de desconto é acrescentada?**
Apenas se cria uma nova classe que implementa `EstrategiaDesconto` (ex.:
`DescontoFuncionario`) e se chama `setEstrategia(new DescontoFuncionario())` onde ela
for usada. `Vendedor`, os observadores e as outras estratégias permanecem intocados
— o sistema é estendido sem modificação (aberto/fechado).

**9. O que muda quando um novo observador, por exemplo `Auditoria`, é acrescentado?**
Cria-se a classe `Auditoria implements ObservadorVenda`, que recebe o `Vendedor` no
construtor e, em `atualizar()`, consulta os getters da última venda. Depois basta
cadastrá-la com `adicionarObservador(...)`. `Vendedor` não muda, porque percorre a
coleção conhecendo apenas a interface.

**10. Qual seria a diferença entre esta solução Singleton e usar somente atributos e métodos `static`?**
Uma classe só com membros `static` não é um objeto: não pode ser passada como
referência (como é feito nos construtores de `Estoque` e `Financeiro`), não pode
implementar interfaces de forma polimórfica, não pode ser substituída por um mock em
testes e não permite criação lazy controlada (é inicializada quando a classe é carregada).
O Singleton mantém um objeto de verdade — com estado encapsulado, herança/interfaces e
um ponto de acesso controlado — e permite mudar a política de criação no futuro (por
exemplo, tornar thread-safe ou permitir mais de uma instância) sem alterar quem usa
`getInstance()`.

## Questões rápidas do laboratório (já implementadas)

**Nova Strategy `DescontoFuncionario`** — classe `DescontoFuncionario` (20%), usada na
venda do "Veículo C" em `Programa`.

**Novo Observer `Auditoria`** — recebe o `Vendedor` no construtor, é cadastrado com
`adicionarObservador(...)` e, em `atualizar()`, consulta `getUltimoVeiculoVendido()` e
`getUltimoPrecoFinal()`. Por ter sido cadastrado só depois das duas primeiras vendas,
ele aparece apenas na venda C.

Se o professor pedir para fazer ao vivo, basta remover as classes extras e o bloco
"Extras" do `Programa` e refazê-los — são cerca de 15 linhas cada.

## Observação sobre threads

A criação lazy usada aqui (`if (instancia == null)`) é a pedida no enunciado e é
correta para um programa de uma única thread. Em ambiente concorrente, duas threads
poderiam criar duas instâncias; a solução seria `synchronized` em `getInstance()`,
*double-checked locking* com `volatile` ou o idiom *Initialization-on-demand holder*.
