import javax.swing.JOptionPane;
import java.util.Arrays;
public class DimensionsInputDialog
{
    static int[] showDialog(){
        return showDialog("Please imput dimensions"+UsefulStrings.getLineSeparator()+"Input should be of the form # of cells in the X coordinate,# of cells in the Y coordinate.", "30,30");
    }
    
    static int[] showDialogWithMessage(String message){
        return showDialog(message, "30,30");
    }
    
    static int[] showDialogWithDefault(String defaultValue){
        return showDialog("Please imput dimensions"+UsefulStrings.getLineSeparator()+"Input should be of the form # of cells in the X coordinate,# of cells in the Y coordinate.", defaultValue);
    }
    
    static int[] showDialog(String message, String defaultValue){
        String[] dimensions = JOptionPane.showInputDialog(message, defaultValue).split(",");
        int[] rVal = null;
        try{
            rVal = new int[]{new Integer(dimensions[0]),new Integer(dimensions[1])};
        }catch(NumberFormatException nfe){
            System.err.println(Arrays.toString(dimensions)+" is not a valid input");
            rVal = DimensionsInputDialog.showDialog(message, defaultValue);
        }
        if(rVal.length!=2){
            System.err.println(Arrays.toString(dimensions)+" is not the right length");
            rVal = DimensionsInputDialog.showDialog(message, defaultValue);
        }
        if(!(rVal[0]>0&&rVal[1]>0)){
            System.err.println(Arrays.toString(dimensions)+" is out of bounds (too small)");
            rVal = DimensionsInputDialog.showDialog(message, defaultValue);
        }
        return rVal;
    }
}
