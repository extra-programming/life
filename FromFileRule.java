import java.awt.Color;
import java.net.URL;
import java.util.HashMap;
/**
 * A Class that processes and represents a Rule loaded from a file
 * 
 * @author 1000000000 
 */
public class FromFileRule implements Rules
{
    public int defaultCellState;
    public int[] acceptableCellStates;
    public Color[] cellColors;
    public int[] neighborValues;
    public HashMap<Character,Integer> charToCellStatesTable = new HashMap<Character,Integer>();
    
    
    public FromFileRule(URL file) {
        
    }
    
    
    public int getDefaultCellState() {
        return defaultCellState;
    }
    
    
    public int[] getAcceptableCellStates() {
        return acceptableCellStates;
    }
    
    
    public Color getCellColor(int cellState) {
        return cellColors[cellState];
    }
    
    
    public int getNeighborValue(int cellState) {
        return neighborValues[cellState];
    }
    
    
    public int getCellState(int cellState, int neighborCount) {
        return 0;
    }
    
    
    public int getCellState(int cellState) {
        return 0;
    }
    
    
    public int readCellState(char c) {
        return charToCellStatesTable.get(c);
    }
    
    public boolean loadRule() {return false;}
}
