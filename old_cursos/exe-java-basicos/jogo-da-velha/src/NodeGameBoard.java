import java.util.ArrayList;
import java.util.Arrays; // Classe que fornece metodos estaticos para tratar array criados, nao eh o ArrayList

public class NodeGameBoard {

    public static  final char MARK_X = 'X';
    public static  final char MARK_O = 'O';
    private static final char FULL_SPACE = 'f';
    private static final char EMPTY_SPACE = 'n';
    private static final int  SPACE = 9;

    // Pai
    private NodeGameBoard nodeFather;

    // numero de jogadas
    private int plays;

    // funcao utilidade
    private int utility;

    // Lista com quasi estao marcados
    private ArrayList<Integer> marked;

    //lista de qauis marcados com X, true quer dizer que tem o elmento la
    private boolean[] markX;

    //lsita de quais marcados com O
    private boolean[] markO;

    // O que esta realmente atribuido no jogo, lista de X/O
    private char[] gameBoard;

    // OPCIONAL : QUAL FOI A JOGADA
    private String play;

    // ---------------------------------------------------------------------------------------------

    // primeiro a ser executado
    public NodeGameBoard(){

        char[] gameBoardAux = new char[SPACE];
        Arrays.fill(gameBoardAux, '-'); //Coloca tudo com '-'
        this.gameBoard = (gameBoardAux);
        Arrays.fill(this.markO, false);
        Arrays.fill(this.markX, false);
        this.marked = new ArrayList<>();
        this.nodeFather = null;
        this.plays = 0;
        this.play = "Quadro Zerado";
    }

    public NodeGameBoard(NodeGameBoard nodeGameBoard){
        this.nodeFather = nodeGameBoard;
        this.markX = nodeGameBoard.markX;
        this.markO = nodeGameBoard.markO;
        this.gameBoard = nodeGameBoard.gameBoard;
        this.plays = nodeGameBoard.plays;
    }

    public void print_game(){
        System.out.println( "This Game:");
        System.out.println("\t" + gameBoard[0] + "|" + gameBoard[1] + "|" + gameBoard[2]); // X|X|X
        System.out.println("\t" + gameBoard[3] + "|" + gameBoard[4] + "|" + gameBoard[5]); // O|O|O
        System.out.println("\t" + gameBoard[6] + "|" + gameBoard[7] + "|" + gameBoard[8]); // O|X|O
    }


    public int utilityEndMax(){
        if( (markX[3] == markX[4]) == markX[5] )
            return 1;
        if( (markX[0] == markX[1]) == markX[2] )
            return 1;
        if( (markX[6] == markX[7]) == markX[8] )
            return 1;
        if( (markX[0] == markX[3]) == markX[6] )
            return 1;
        if( (markX[1] == markX[4]) == markX[7] )
            return 1;
        if( (markX[2] == markX[5]) == markX[8] )
            return 1;
        if( (markX[0] == markX[4]) == markX[8] )
            return 1;
        if( (markX[2] == markX[4]) == markX[6] )
            return 1;
        return 0;
    }


    public boolean anyWin(){
        // [0,1,2]
        if( gameBoard[0] == gameBoard[1] && gameBoard[0] == gameBoard[2])
            return true;
        // [3,4,5]
        if( gameBoard[3] == gameBoard[4] && gameBoard[4] == gameBoard[5])
            return true;
        // [6,7,8]
        if( gameBoard[6] == gameBoard[7] && gameBoard[7] == gameBoard[8])
            return true;
        // [0,3,6]
        if( gameBoard[0] == gameBoard[3] && gameBoard[0] == gameBoard[2])
            return true;
        // [1,4,7]
        if( gameBoard[1] == gameBoard[4] && gameBoard[4] == gameBoard[7])
            return true;
        // [2,5,8]
        if( gameBoard[2] == gameBoard[5] && gameBoard[5] == gameBoard[8])
            return true;
        // [0,4,8]
        if( gameBoard[0] == gameBoard[4] && gameBoard[4] == gameBoard[8])
            return true;
        // [2,4,6]
        if( gameBoard[2] == gameBoard[4] && gameBoard[4] == gameBoard[6])
            return true;
        return false;
    }

    // true se terminou, false se falta
    public int utilityEndMin(){
        if( (markO[0] == markO[1]) == markO[2] )
            return -1;
        if( (markO[3] == markO[4]) == markO[5] )
            return -1;
        if( (markO[6] == markO[7]) == markO[8] )
            return -1;
        if( (markO[0] == markO[3]) == markO[6] )
            return -1;
        if( (markO[1] == markO[4]) == markO[7] )
            return -1;
        if( (markO[2] == markO[5]) == markO[8] )
            return -1;
        if( (markO[0] == markO[4]) == markO[8] )
            return -1;
        if( (markO[2] == markO[4]) == markO[6] )
            return -1;
        return 0;
    }

    public int functionUtility(){
        utility = utilityEndMax() + utilityEndMin();
        return utility;
    }

    // --------------------------- Teste do FIM

    public boolean testFinishGame(){
        // verifica se faremos ou nao o test
        if( testEndGame()) {
            // volta se alguem venceu ou nao [if enorme]
            return anyWin();
        }else{
            // O jogo ta na metda, ninguem venceu ainda
            return false;
        }
    }

    /* verifica quando vai testar o test, tipo,
       serve pra nao chamar test quando ja sabemos
       que nao terminou
    */
    public boolean testEndGame(){
        if(plays >= 5)
            return true;
        else
            return false;
    }

    /* Se tudo igual, escolhe  a do meio, else, random
        1. Se impata, random
        2. Vatnagem no do meio
        3. Considerar quantos consegue
        4. Considerar quanto bloquia
            Positivo > + > Max > first a jogar
            Negativo > - > Min > second a jogar

     */
    public int calculeUtility(){
        return 0;
    }

    // ----------------------------------------

    public NodeGameBoard getNodeFather() {
        return nodeFather;
    }

    public void setNodeFather(NodeGameBoard nodeFather) {
        this.nodeFather = nodeFather;
    }

    public boolean[] getMarkX() {
        return markX;
    }

    public void setMarkX(boolean[] markX) {
        this.markX = markX;
    }

    public boolean[] getMarkO() {
        return markO;
    }

    public void setMarkO(boolean[] markO) {
        this.markO = markO;
    }

    public char[] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(char[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public ArrayList<Integer> getMarked() {
        return marked;
    }

    public void setMarked(ArrayList<Integer> marked) {
        this.marked = marked;
    }

    // Setar em um idex especifico
    public void setMarkOIndex(boolean mark, int index){
        this.markO[index] = mark;
    }

    public void setMarkXIndex(boolean mark, int index){
        this.markX[index] = mark;
    }

    public void setGameBoardIndex(char mark, int index){
        this.gameBoard[index] = mark;
    }
}
