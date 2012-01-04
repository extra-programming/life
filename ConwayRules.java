import java.awt.Color;
public class ConwayRules implements Rules
{
    public int[] acceptableCellStates(){
        return new int[]{0,1};
    }
    public Color cellColor(int cellState){
        if(cellState==0) return Color.WHITE;
        return Color.BLACK;
    }
    public int neighborValue(int cellState){
        return cellState;
    }
    public int cellState(int cellState, int neighborCount){
        if(cellState==0){
            if(neighborCount==3) return 1;
            return 0;
        }else{
            if(neighborCount<2) return 0;
            if(neighborCount>3) return 0;
            return 1;
        }
    }
    public int readFile(char c){
        if(c=='.' || c=='0' || (c>='a' && c<='z')) return 0;
        if((c>'0' && c<='9') || (c>='A' && c<='Z')) return 1;
        throw new IllegalArgumentException(c+" is not a valid input for OurRules");
    }
}
