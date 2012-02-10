/**
 * An interface that repersents the rules used in the game
 * you must register your rules class in static final LifeGame.rulesList
 * @author Gavin Yancey
 */
public interface Rules
{
    /**@return the default cell state*/
    int getDefaultCellState();
    /**@return an array of all the acceptable cell states*/
    int[] getAcceptableCellStates();
    /**@return the color of a cell based on it's cell state*/
    java.awt.Color getCellColor(int cellState);
    /**@return the amount any cell counts for in a neighbor count*/
    int getNeighborValue(int cellState);
    /**used when stepping
     * @return the next cell state, based on the curent cell state and the neighbor count*/
    int getCellState(int cellState, int neighborCount);
    /**used for changing rules, cell might not be an acceptable cell state, make sure it is one*/
    int getCellState(int cellState);
    /**used for reading the game from a file
     * @return the cell state represented by any charactor in a life file*/
    int getCellStateFromFile(char c);
    char getCellStateForFile(int cellState);
    /**used for changing the cell state when a cell is clicked on
     * @return the new cell state from a clicked cell*/
    int getCellStateFromClick(int cellState);
}
