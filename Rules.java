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
}
