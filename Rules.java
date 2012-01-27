/** you must register your rules class in static final LifeGame.rulesList */
public interface Rules
{
    int getDefaultCellState();
    int[] getAcceptableCellStates();
    java.awt.Color getCellColor(int cellState);
    int getNeighborValue(int cellState);
    int getCellState(int cellState, int neighborCount);
    /**used for changing rules, cell might not be an acceptable cell state, make sure it is one*/
    int getCellState(int cellState);
    int readCellState(char c);
    /**
     * Used for more advanced rules that need to do things before they are used<br /><br />
     * If the rule does not need to do anything after being selected from the rules menu then do nothing here
     * @return true if life should go ahead with the rule switch, false if there is a problem encountered and life should not change rules
     */
    boolean loadRule();
}
