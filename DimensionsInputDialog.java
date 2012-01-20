import javax.swing.JOptionPane;
import java.util.Arrays;
public class DimensionsInputDialog
{
    static int[] showDialog(){
        return showDialog("Please imput dimensions");
    }
    
    static int[] showDialog(String message){
        String[] dimensions = JOptionPane.showInputDialog(message).split(",");
        int[] rVal = null;
        try{
            rVal = new int[]{new Integer(dimensions[0]),new Integer(dimensions[1])};
        }catch(NumberFormatException nfe){
            System.err.println(Arrays.toString(dimensions)+" is not a valid input");
            rVal = DimensionsInputDialog.showDialog(message);
        }
        if(rVal.length!=2){
            System.err.println(Arrays.toString(dimensions)+" is not the right length");
            rVal = DimensionsInputDialog.showDialog(message);
        }
        if(!(rVal[0]>0&&rVal[1]>0)){
            System.err.println(Arrays.toString(dimensions)+" is out of bounds (too small)");
            rVal = DimensionsInputDialog.showDialog(message);
        }
        return rVal;
    }
}
