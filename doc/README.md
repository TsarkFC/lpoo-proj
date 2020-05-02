
# LPOO_13 - VOYD TYRANT (card game)

Este projeto tem como inspiração o jogo "Void Tyrant", onde o utilizador tem como objetivo derrotar um enimigo, utilizando as cartas que tem ao seu dispor. 
Ambos os participantes possuem dois baralhos: um baralho de cartas normais e um baralho de cartas especiais cujas habilidades poderão ser ativadas com o decorrer do jogo.

As cartas normais possuem valores entre 1 a 6 e cada jogador tem como objetivo ir tirando cartas do baralho até que o valor somado das cartas retiradas esteja o mais próximo possível de 12.
Quando ambos os jogadores derem o seu turno por terminado, ataca o jogador com pontuação mais próxima de 12, um número de vezes correspondente à diferença de pontuações.

A ser desenvolvido por [João Cardoso](https://github.com/joaoalc) (up201806531@fe.up.pt) e [João Romão](https://github.com/TsarkFC) (up201806779@fe.up.pt).

## Features implementadas

 - Atualmente, o jogador e o computador podem fazer draw de cartas do baralho normal, há deteção de overflow do draw.
 
 - O computador pode apresentar, neste momento, três "personalidades" distintas:
    - Normal: Faz draw de cartas até ter 9 ou mais pontos;
    - Cuidadosa: Faz draw de cartas até ter 7 ou mais pontos;
    - Agressiva: Faz draw de cartas até ter 9 ou mais pontos, também faz draw se o jogador tiver os mesmos ou mais pontos.
 
 - O utilizador pode obter informação relativa às quatro cartas que tem na "mão" premindo {1, 2, 3, 4}.
 
 - O utilizador pode terminar o seu turno premindo ENTER.
 
 

## Features a implementar

 - Ativação de cartas especiais por parte do utilizador.

 - Ativação de cartas especiais por parte do computador, dependendo da personalidade do mesmo.

 - Cálculo do dano feito/recebido no final de cada turno, dependendo dos pontos e cartas do jogador e inimigo.

 - Existência de vários níveis, onde o computador teria diferentes comportamentos a jogar (mais agressivo, equilibrado passivo).
 
 - O jogador vai desbloqueando novas cartas à medida que vai derrotando inimigos.

## Design Patterns
 
 ####Observer

 ####Command
 
 **Problema:**
 
 No nosso código, existem certas ações que são genéricas (Exemplo: Fazer draw de cartas ou shuffle de um dos decks). Estas ações podem acontecer em várias situações. (Ambos o jogador e o inimigo podem fazer draw, o deck faz shuffle quando as cartas acabam ou, no futuro, possivelmente pelo efeito de alguma carta)
 
 Isto envolve repetição desnecessária de código.
 
 **Design pattern/ solução:**
 
 Este design pattern permite fazermos um comando genérico que faz a ação que queremos. Quando precisarmos de executar a ação, chamamos a classe do comando e depois chamamos a função execute.
 
 No nosso código, existe a interface Command, que é implementada pelo DeskShuffler e o DrawCardCommand
 
 ####Strategy
 
 **Problema:**
 
  Após implementarmos a possibilidade do inimigo fazer draw do seu deck principal, achamos que seria mais interessante se diferentes inimigos reagissem a certas situações de maneiras diferentes.
  
  Isso envolve ter um conjunto expansível de códigos diferentes, todos relativamente complicados, para todas as estratégias. Todos esses códigos são aplicados nas mesmas circunstâncias (No turno do oponente). Se colocassemos um switch case para as diferentes estratégias, acabariamos com um método extremamente "bloated".
  
 **Design Pattern:**
 
 Podemos usar o padrão "Strategy" para corrigir o problema.
  
  Este padrão envolve termos uma interface, que tem uma função para "executar" a função da estratégia. Quando se quer adicionar uma estratégia, cria-se uma classe que implementa essa função. Sendo assim, pode-se associar uma estratégia de jogo a um inimigo e executá-la quando for necessário (Neste caso, no turno do inimigo).
  
  
## Code Smells

 Large class

 Large method

## Refactorings

 Extract class
 
 Repalce method with method object
 
 Extract method

## Testes

 Estão desenvolvidos testes para algumas classes que fazem parte do modelo.

