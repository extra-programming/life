public interface Rules
{
    /** you must register your rules class in static final LifeGame.rulesList */
    int getDefaultCellState();
    int[] getAcceptableCellStates();
    java.awt.Color getCellColor(int cellState);
    int getNeighborValue(int cellState);
    int getCellState(int cellState, int neighborCount);
    int getCellState(int cellState); // used for changing rules, cell might not be an acceptable cell state, make sure it is one
    int readCellState(char c);
}
