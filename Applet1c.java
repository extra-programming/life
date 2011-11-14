// An application and an applet.
// <applet code=Applet1c width=100 height=50>
// </applet>
import javax.swing.*;
import java.awt.*;
//import com.bruceeckel.swing.*;

public class Applet1c extends JApplet {
	public void init() {
		getContentPane().add(new JLabel("Applet!"));
	} // init( )


    // A main() for the application:
    public static void main(String[] args) {
    	JApplet applet = new Applet1c();
    	JFrame frame = new JFrame("Applet1c");
    	// To close the application:
    	// requires com.bruceeckel.swing.*; Console.setupClosing(frame);
    	frame.getContentPane().add(applet);
    	frame.setSize(100,50);
    	applet.init();
    	applet.start();
    	frame.setVisible(true);
    } // main

} // class Applet1c  