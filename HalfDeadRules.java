/**A rule set where cells can be "Half Dead," denoted by being red
 * @author Gavin Yancey
 */
import java.awt.Color;
public class HalfDeadRules implements Rules
{
    public int getDefaultCellState(){
        return 0;
    }
    public int[] getAcceptableCellStates(){
        return new int[]{0,1,2};
    }
    public Color getCellColor(int cellState){
        if(cellState==0) return Color.WHITE;
        if(cellState==2) return Color.BLACK;
        return Color.RED;
    }
    public int getNeighborValue(int cellState){
        return cellState;
    }
    public int getCellState(int cellState, int neighborCount){
        if(cellState==0){
            if(neighborCount==5) return 1;
            return 0;
        }else if(cellState==1){
            if(neighborCount<4) return 0;
            if(neighborCount>7) return 0;
            if(neighborCount==5) return 2;
            return 1;
        }else{
            if(neighborCount<4) return 1;
            if(neighborCount>7) return 1;
            return 2;
        }
    }
    public int getCellState(int cellState){
        //if(cellState==2||cellState==1||cellState==0) return cellState;
        if(cellState==0) return 0;
        if(cellState==1) return 2;
        //System.out.println(cellState+" is not acceptable for HalfDeadRules");
        return 1;
    }
    public int getCellStateFromFile(char c){
        switch(c){
            case '0':
            case '.': return 0;
            case '1':
            case 'a': return 1;
            case '2':
            case 'A': return 2;
            default: throw new IllegalArgumentException(c+" is not a valid input for HalfDeadRules");
        }
    }
    public boolean initRule(){
        return true;
    }
    public int getCellStateFromClick(int cellState){
        return (cellState+1)%3;
    }
}
