import java.util.ArrayList;

public class PlayerMax extends Player{

    public PlayerMin playerMin;

    public int valueMax(NodeGameBoard nodeGameBoard){
        if(nodeGameBoard.testFinishGame()) {
            return nodeGameBoard.functionUtility();
        }
        int valueOut = -999;
        ArrayList<NodeGameBoard> allActionNodes = createAllBoards(nodeGameBoard, MARK_X);
        for(NodeGameBoard eachNode : allActionNodes){
            valueOut = detMinOfNodes(valueOut, playerMin.valueMin(eachNode));
        }
        return valueOut;
    }

    public int detMinOfNodes(int value, int valueOfNode){
        if(value <= valueOfNode)
            return value;
        else
            return valueOfNode;
    }

    public PlayerMin getPlayerMin() {
        return playerMin;
    }

    public void setPlayerMin(PlayerMin playerMin) {
        this.playerMin = playerMin;
    }
}
