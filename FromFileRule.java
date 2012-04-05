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
    private String name;
    private int defaultCellState;
    private int[] acceptableCellStates; //Do we really need this it is the key set of most of our HashMaps
    private HashMap<Integer,Color> cellStateColors = new HashMap<Integer,Color>();
    private HashMap<Integer,Integer> cellStateNeighborVals = new HashMap<Integer,Integer>();
    private HashMap<Character,Integer> readTable = new HashMap<Character,Integer>();
    private HashMap<Integer,Integer> ruleConversionTable = new HashMap<Integer,Integer>();
    //would it be better to use Arrays and have one HashMap as a sort of phonebook for the Array vaules?
    
    private FromFileRule() {}
    
    public static FromFileRule loadRuleFile(URL ruleFile) {
        System.err.println("Unimplemented");
        return null;
    }
    
    
    public int getDefaultCellState() {
        return defaultCellState;
    }
    
    
    public int[] getAcceptableCellStates() {
        return acceptableCellStates;
    }
    
    
    public Color getCellColor(int cellState) {
        return cellStateColors.get(cellState);
    }
    
    
    public int getNeighborValue(int cellState) {
        return cellStateNeighborVals.get(cellState);
    }
    
    
    public int getCellState(int cellState, int neighborCount) {
        return 0;
    }
    
    
    public int getCellState(int cellState) {
        return ruleConversionTable.get(cellState);
    }
    
    
    public int readCellState(char c) {
        return readTable.get(c);
    }
    
    public boolean loadRule() {return false;}
    
    public boolean isDataValid() {
        return (acceptableCellStates != null && cellStateColors.size() == acceptableCellStates.length && isNeighborValuesValid());
    }
    
    private boolean isNeighborValuesValid() {
        return false;
    }
    
    public String toString() {
        return name;
    }
}
