import java.awt.Color;
public class OurRules implements Rules
{
    public int getDefaultCellState(){
        return 0;
    }
    public int[] getAcceptableCellStates(){
        return new int[]{0,1,2};
    }
    public Color getCellColor(int cellState){
        if(cellState==0) return Color.WHITE;
        if(cellState==1) return Color.BLACK;
        return Color.RED;
    }
    public int getNeighborValue(int cellState){
        if(cellState==0) return 0;
        return 1;
    }
    public int getCellState(int cellState, int neighborCount){
        if(cellState==0){
            if(neighborCount==3) return 1;
            return 0;
        }else if(cellState==1){
            if(neighborCount==2) return 2;
            if(neighborCount==3) return 1;
            return 0;
        }else{
            if(neighborCount==2) return 1;
            if(neighborCount==3) return 2;
            return 0;
        }
    }
    public int getCellState(int cellState){
        if(cellState==2||cellState==1||cellState==0) return cellState;
        System.out.println(cellState+" is not acceptable for OurRules");
        return 0;
    }
    public int getCellStateFromFile(char c){
        switch(c){
            case '0':
            case '.': return 0;
            case '1':
            case 'A': return 1;
            case '2': return 2;
            default: throw new IllegalArgumentException(c+" is not a valid input for OurRules");
        }
    }
    public char getCellStateForFile(int cellState){
        if(cellState==this.getDefaultCellState()) return '.';
        else return Integer.toString(cellState).charAt(0);
    }
    public boolean initRule(){
        return true;
    }
    public int getCellStateFromClick(int cellState){
        return (cellState+1)%3;
    }
}
