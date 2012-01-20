import java.awt.Color;
import java.net.URL;
/**
 * Write a description of class RulesFromFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RulesFromFile implements Rules
{
    public int defaultCellState;
    public int[] acceptableCellStates;
    
    
    public RulesFromFile(URL file) {
        
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
}
