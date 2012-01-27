import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Arrays;
/**
 * Write a description of class RulesFromFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RulesFromFile implements Rules
{
    private int rulePointer;
    private ArrayList<FromFileRule> loadedRules = new ArrayList<FromFileRule>();

    public int getDefaultCellState() {
        return loadedRules.get(rulePointer).getDefaultCellState();
    }

    public int[] getAcceptableCellStates() {
        return loadedRules.get(rulePointer).getAcceptableCellStates();
    }

    public Color getCellColor(int cellState) {
        return loadedRules.get(rulePointer).getCellColor(cellState);
    }

    public int getNeighborValue(int cellState) {
        return loadedRules.get(rulePointer).getNeighborValue(cellState);
    }

    public int getCellState(int cellState, int neighborCount) {
        return loadedRules.get(rulePointer).getCellState(cellState, neighborCount);
    }

    public int getCellState(int cellState) {
        return loadedRules.get(rulePointer).getCellState(cellState);
    }

    public int readCellState(char c) {
        return loadedRules.get(rulePointer).readCellState(c);
    }

    public boolean loadRule() {
        Object[] options = Arrays.copyOf(loadedRules.toArray(),loadedRules.size() + 1);
        options[options.length-1] = "Load New Rule File...";
        Object choice =JOptionPane.showInputDialog(null,
                "Testing 1,2,3","Test",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[rulePointer]);

        if(loadedRules.indexOf(choice) == -1) {
            URL file = ComponentUtil.getSomeOldFile(null);
            if(file == null) return false;
            choice = new FromFileRule(file);
            loadedRules.add((FromFileRule)choice);
        }
        rulePointer = loadedRules.indexOf(choice);
        return true;
    }
}

