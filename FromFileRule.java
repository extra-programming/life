import java.awt.Color;
import java.net.URL;
import java.util.HashMap;

/**
 * A Class that processes and represents a Rule loaded from a file
 * Should I use a static method to return a new reference and read xml there or should I do that in RulesFromFile <--Leaning slightly toward this
 * @see <a href="http://totheriver.com/learn/xml/xmltutorial.html">xml</a>
 * @author 1000000000 
 */
public class FromFileRule implements Rules
{
    private int defaultCellState;
    private int[] acceptableCellStates;
    private Color[] cellColors;
    private int[] neighborValues;
    private HashMap<Character,Integer> charToCellStatesTable = new HashMap<Character,Integer>();
    private HashMap<Integer,Integer> unsupportedCellStateConversionTable = new HashMap<Integer,Integer>();
    
    
    public FromFileRule(URL file) {
        String input = ComponentUtil.readStringFromFile(file);
        
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
        return unsupportedCellStateConversionTable.get(cellState);
    }
    
    
    public int readCellState(char c) {
        return charToCellStatesTable.get(c);
    }
    
    public boolean loadRule() {return false;}
    
    public boolean isDataValid() {
        return (acceptableCellStates != null && cellColors != null && isNeighborValuesValid());
    }
    
    private boolean isNeighborValuesValid() {
        return false;
    }
}
