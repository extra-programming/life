import java.awt.Color;
import java.util.HashMap;
public class CellState {
    private final int value;
    private final int neighborValue;
    private final Color cellColor;
    private final int defaultCellInteractionRes;
    private HashMap<Integer,Integer> cellInteractions = new HashMap<Integer,Integer>();
    
    public CellState(int val, int neighborVal, Color color, int defaultCellInteractionResult) {
        value = val;
        neighborValue = neighborVal;
        cellColor = color;
        defaultCellInteractionRes = defaultCellInteractionResult;
    }
    
    public int getInteractionResult(int neighborCount) {
        Integer interactionResult = cellInteractions.get(neighborCount);
        if(interactionResult == null) return defaultCellInteractionRes;
        return interactionResult;
    }
}
