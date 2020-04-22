import java.util.ArrayList;

public abstract class Player {

    public static  final char MARK_X = 'X';
    public static  final char MARK_O = 'O';

    // Para um no estado, gera todas as posibilidades
    public ArrayList<NodeGameBoard> createAllBoards(NodeGameBoard nodeGameBoard, char mark){
        ArrayList<NodeGameBoard> allNodes = new ArrayList<>();
        ArrayList<Integer> allPosibilities = new ArrayList<>();

        /*  Vou adicionar as açoes de acordo com o criterio
            de que nao estou marcadas
        */
        for(int index = 0; index < 9; index++){
            if(!nodeGameBoard.getMarked().contains(index)){
                allNodes.add(createOneGameBoard(nodeGameBoard, mark, index));
            }
        }
        return allNodes;
    }

    // Retorna um no feito a partir de uma jogada
    public NodeGameBoard createOneGameBoard(NodeGameBoard nodeGameBoard, char mark, int index) {
        NodeGameBoard nodeGameBoardAux = new NodeGameBoard(nodeGameBoard);
        nodeGameBoardAux.setGameBoardIndex(mark,index);
        if (mark == MARK_O) {
            // Pode nao esta funcionando, por conta de tratar de um getter, nao sei, VERIFICA AI
            nodeGameBoard.getMarked().add(index);
            nodeGameBoardAux.setMarkOIndex(true, index);
            nodeGameBoardAux.setPlay("Jogador Min pois O em " + index);
        }else {
            nodeGameBoard.getMarked().add(index);
            nodeGameBoardAux.setMarkXIndex(true, index);
            nodeGameBoardAux.setPlay("Jogador Max pois X em " + index);
        }
        nodeGameBoardAux.setPlays(nodeGameBoardAux.getPlays() + 1);
        return nodeGameBoardAux;
    }
}
