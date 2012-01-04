import java.awt.Color;
public class OurRules implements Rules
{
    public int[] acceptableCellStates(){
        return new int[]{0,1,2};
    }
    public Color cellColor(int cellState){
        if(cellState==0) return Color.WHITE;
        if(cellState==1) return Color.BLACK;
        return Color.RED;
    }
    public int neighborValue(int cellState){
        if(cellState==0) return 0;
        return 1;
    }
    public int cellState(int cellState, int neighborCount){
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
}
