import java.util.ArrayList;

public class PlayerMin extends Player {

    public PlayerMax playerMax;

    public int valueMin(NodeGameBoard nodeGameBoard){
        if(nodeGameBoard.testFinishGame()) {
            return nodeGameBoard.functionUtility();
        }
        int valueOut = 999;
        ArrayList<NodeGameBoard> allActionNodes = createAllBoards(nodeGameBoard, MARK_O);
        for(NodeGameBoard eachNode : allActionNodes){
            valueOut = detMaxOfNodes(valueOut, playerMax.valueMax(eachNode));
        }
        return valueOut;
    }

    public int detMaxOfNodes(int value, int valueOfNode){
        if(value <= valueOfNode)
            return value;
        else
            return valueOfNode;
    }

    public PlayerMax getPlayerMax() {
        return playerMax;
    }

    public void setPlayerMax(PlayerMax playerMax) {
        this.playerMax = playerMax;
    }
}
