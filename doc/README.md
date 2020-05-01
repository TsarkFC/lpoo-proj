
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
 
 - Observer

 - Command
 
 - Strategy

## Code Smells

 Large class

 Large method

## Refactorings

 Extract class
 
 Repalce method with method object
 
 Extract method

## Testes

 Estão desenvolvidos testes para algumas classes que fazem parte do modelo.

