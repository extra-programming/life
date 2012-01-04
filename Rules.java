public interface Rules
{
    int[] acceptableCellStates();
    java.awt.Color cellColor(int cellState);
    int neighborValue(int cellState);
    int cellState(int cellState, int neighborCount);
}
