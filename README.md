# Trabalho Final - Caixeiro Viajante   
**Aluna: Fernanada Takao**
**Data de Entrega: 2025-06-23**  
**Disciplina: Sistemas Distribuídos, EC48A, turma C81**
**Professor: Rogério Santos Pozza**


## Descrição do Problema

O problema do Caixeiro Viajante (TSP) consiste em encontrar o caminho de menor custo para que um vendedor percorra todas as cidades exatamente uma vez e retorne à cidade inicial.

---

## Estrutura do Projeto

```plaintext
CaixeiroViajante_FernandaTakao/
├── src/                        # Código-fonte Java
│   ├── AppSequencial.java
│   ├── AppParalelo.java
│   ├── ThreadTSP.java
│   ├── AppCliente.java
│   ├── ThreadServidor.java
│   └── TSPLogica.java
├── relatorio.pdf              # Relatório explicativo do trabalho
└── README.md                  # Instruções de execução e organização
```

---

## Como Executar

### ✅ Compilação

Para compilar todos os arquivos:

```bash
javac src/*.java
```

###  Execução

#### Versão Sequencial

```bash
java -cp src AppSequencial
```

#### Versão Paralela

```bash
java -cp src AppParalelo
```

#### Versão Distribuída

**1. Em um terminal, execute o servidor:**

```bash
java -cp src AppServidor
```

**2. Em dois terminais diferentes, execute o cliente (duas vezes):**

```bash
java -cp src AppCliente
```

---

## Comparativo de Desempenho

| Abordagem      | Tempo Estimado (ms) | Observações                       |
|-------------|------------------------|-----------------------------------|
| Sequencial  |        7 ms        | Custo computacional elevado       |
| Paralela    |        3 ms        | Uso de 2 threads                  |
| Distribuída |        5 ms        | Processamento em 2 sockets        |

> Os tempos foram medidos em uma máquina com processador Intel i5, 8 GB de RAM, Windows 10.

A versão paralela mostrou ganho expressivo sobre a execução sequencial, graças à divisão equilibrada das permutações entre múltiplas threads, o que aproveitou melhor os núcleos da CPU.

A versão distribuída, por outro lado, apresentou, na prática, um tempo real muito superior (cerca de 20.000 ms), devido à sobrecarga de serialização de objetos grandes, estabelecimento de conexões TCP e tempo de espera para a resposta de cada cliente. No entanto, em condições ideais — como execução em máquinas distintas com comunicação otimizada —, espera-se que esse tempo seja significativamente menor, superando a versão sequencial e aproximando-se da eficiência da versão paralela.

Os resultados evidenciam que a distribuição de tarefas é vantajosa para cargas computacionais maiores, mas exige atenção especial à comunicação entre processos e à arquitetura da solução.


## Observações Técnicas

- A matriz de distâncias foi fixa para 8 cidades.
- Foram usadas permutações simples (força bruta).
- O foco do trabalho é comparar diferentes estratégias de paralelismo e distribuição.
- O projeto pode ser facilmente ampliado para mais threads ou mais clientes.

---


## Observação sobre uso de IA

Utilizou-se da ajuda de IA para revisar o código, isto é, para identificar redundâncias, variáveis inúteis e pontos que valessem a pena ser comentados. Além disso, foi solicitado a ela que auxiliasse a formatar este README, de forma a torná-lo o mais claro e preciso possível.
