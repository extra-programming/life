import java.awt.Color;
import java.net.URL;
/**
 * A Class that processes and represents a Rule loaded from a file
 * 
 * @author 1000000000 
 */
public class FromFileRule implements Rules
{
    public int defaultCellState;
    public int[] acceptableCellStates;
    
    
    public FromFileRule(URL file) {
        
    }
    
    
    public int getDefaultCellState() {
        return defaultCellState;
    }
    
    
    public int[] getAcceptableCellStates() {
        return acceptableCellStates;
    }
    
    
    public Color getCellColor(int cellState) {
        return null;
    }
    
    
    public int getNeighborValue(int cellState) {
        return 0;
    }
    
    
    public int getCellState(int cellState, int neighborCount) {
        return 0;
    }
    
    
    public int getCellState(int cellState) {
        return 0;
    }
    
    
    public int readCellState(char c) {
        return 0;
    }
    
    public void doRuleSelectorUI() {}
}
