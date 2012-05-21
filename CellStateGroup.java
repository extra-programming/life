
public class CellStateGroup {
    private CellState defaultState;
    private HashSet<CellState> cellStates = new HashSet<CellState>();
    private HashMap<Character,Integer> readTable = new HashMap<Character,Integer>();
    private HashMap<Integer,Integer> ruleConversionTable = new HashMap<Integer,Integer>();

    public CellStateGroup() {
        
    }
}
