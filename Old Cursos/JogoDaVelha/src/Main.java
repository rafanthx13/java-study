public class Main {

    /*  Log de Versions : Rafael Assis

    03.12.2017 : Deu uma renovada nas ED dos Objetos, falta

    +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    O que falta a fazer:
		+ Contaegem de nos
        + Parte visual da saida, quando terminar
        + Necessidade de se ter MarkO e markX?
        + Testar a aplicaçao, nao tenho ideia se dar certo ou nao
		+ A inicializaçao dos atributos, se eu inicializerei todos na ora serta
		
	+++++++++++++++++++++++++++++++++++++++++++++++++++++++
	Etapas:
		+ 1. Dar start, monstar projeto e pensar em algi : 30min
		+ 2. Modelo de dados, pensar em como sera mesmo (folha e caneta)
			; Preparaçao de Ambiente e Conteudo: 2h
		+ 3. Mão na massa : Começar a mdela toda a parte de Classes, métodos
				e tudo mais, SEM TESTAR : 4h

     */

    public static void main(String[] args) {

        //create
        PlayerMax playerMax = new PlayerMax();
        PlayerMin playerMin = new PlayerMin();
        NodeGameBoard nodeGameBoard = new NodeGameBoard();

        //prepare to game
        playerMax.setPlayerMin(playerMin);
        playerMin.setPlayerMax(playerMax);

        //Begin of Hell
        playerMax.valueMax(nodeGameBoard);

    }


}
