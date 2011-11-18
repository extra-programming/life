// file LifeBoard.java
// For some reason bbedit file needs "Western (Mac OS Roman)" with "Mac (CR)" to compile with javac. (Maybe have to save as new file with utf16?)
// Good: "Western (Mac OS Roman)" with "Mac (CR)"
// Bad: UTF-8 with Unicode linebreak
// Bad: UTF-16 with unicode linebreak
// Bad: UTF-8 with unix LF
// Bad: UTF-8 with Mac CR

// package <default>

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.applet.*;


/**
 * @author Mike Roam
 */
public class LifeGame extends JApplet /* was Applet */ {

    private static final long serialVersionUID = 42L;  // makes serializable happy

    static JFrame mikeFrame = null;  // gui window for standalone, containing everything
    /* static */ Container myContentPane = null; // for applets and standalone
    public static final int FRAMEWIDTH = 500;
    public static final int FRAMEHEIGHT = 500;


    LifeBoard theBoard = null;  
    /* uh-oh: this is JPanel within my borderLayout, 
     * but when I want to replace it with a new one,
     * I'm not sure whether to get rid of the old before adding the new? */

    JButton getFileBtn = new JButton( "Get 'life' File..." );
    //Button getFileBtn = new Button( "Get 'life' File..." );

    JButton randomBoardBtn = new JButton( "Random Board" );
    //Button randomBoardBtn = new Button( "Random Board" );

    JButton stepOneBtn = new JButton( "Step" );
    //Button goMoreBtn = new Button( "Step" );
    JButton stepTenBtn = new JButton( "Step 10" );
    JButton stepContinuouslyBtn = new JButton( "Run" );

    JButton quitBtn = new JButton( "Quit" );
    //Button quitBtn = new Button( "Quit" );
    
    JSlider delaySlider = null; /* is instantiated in buildDelayPanel( ) */
    
    Thread stepperThread = new Thread(){
            public void run() {
                while(true) {
                    if ( stepContinuouslyBtn.getText().equals("Pause") ) {
                    	lifeStep(1);
                    }
                    try {
                        sleep( delaySlider.getValue() );
                    } catch( Exception e ) {
                        e.printStackTrace( System.err );
                    } /* end of try */
                } /* end of while */
            } /* run( ) */
        }; /* instance stepperThread */




    /**
    * default constructor, called by main
    */
    public LifeGame( ) {
        myContentPane = this.getContentPane( );
        init( );
    } // end of default constructor


    public static void main(String args[]) {
        LifeGame myLifeGame = new LifeGame( );
        JFrame frame = new JFrame(" Life Game ");
        frame.getContentPane().add( myLifeGame );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        /* myLifeGame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }); 
        */
        frame.setSize( FRAMEWIDTH, FRAMEHEIGHT );
        // myLifeGame.init( ); the constructor calls init!
        myLifeGame.start( ); // what's this??
        frame.setVisible(true);
    } // main( )




    /**
    * For starting up Applet
    * (Also called by main and constructor so standalone acts like applet)
    */
    public void init( ) {
//      myContentPane.add( "Center", this ); // necessary?
        if (myContentPane == null) {
//          // so I guess we're an applet
            myContentPane = getContentPane( );
        }
        /* Start life with a random board */
        theBoard = new LifeBoard(/*cellsAcross:*/ 20, /*cellsDown*/20 );
        this.buildGUI( );
    } // init( )

    
    public int getBoardX(int x){
        return x;
    }
    
    public int getBoardY(int y){
        return y;
    }
    
    public void changeCellVal(int x, int y){
        
    }

    /**
    * applets and standalones call constructor which calls init which calls this.
    */
    public void buildGUI() {
        // this.setLayout( new BorderLayout( ) ); / /JApplet have this by default
        JPanel setupPanel = new JPanel();
        setupPanel.setBorder(BorderFactory.createEmptyBorder( /* TLRB */ 10, 10, 10, 10) );
        /* TLRB means "top, left, bot, right" */
        setupPanel.setLayout(new GridLayout(/* rows (height) */ 0, /* cols (width) */ 1));
        //paramPanel.setLayout( new FlowLayout() );
        setupPanel.setBackground( Color.BLUE );
        setupPanel.add( getFileBtn );
        setupPanel.add( randomBoardBtn );
        
        JPanel controlPanel = new JPanel( );
        controlPanel.setBorder(BorderFactory.createEmptyBorder( /* TLRB */ 10, 10, 10, 10) );
        /* using default flowLayout */
        JPanel stepPanel = new JPanel( );
        stepPanel.setBackground( Color.lightGray );
        stepPanel.add( stepOneBtn );
        stepPanel.add( stepTenBtn );
        stepPanel.add( stepContinuouslyBtn );
        controlPanel.add( stepPanel );
        controlPanel.add( buildDelayPanel( ) );
        controlPanel.add( quitBtn );
        
        //this.getContentadd( "North", paramPanel );
        if (myContentPane == null) {
            System.out.println("myContentPane is still null");
        }
        myContentPane.add( new JLabel("page_start"), BorderLayout.PAGE_START);
        myContentPane.add( new JLabel("line_start"), BorderLayout.LINE_START);
        myContentPane.add( theBoard, BorderLayout.CENTER);
        //myContentPane.add("East", paramPanel);
        myContentPane.add( setupPanel, BorderLayout.LINE_END);
        
        myContentPane.add( controlPanel, BorderLayout.PAGE_END );
        
        addActionListenersToMyButtons( );
        
        stepperThread.start();
    } // buildGUI( )


    void addActionListenersToMyButtons( ) {
        
        this.addMouseListener (new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int x = e.getX(), y = e.getY();
                System.out.println("mouse clicked on "+x+", "+y);
                int bx=getBoardX(x), by=getBoardY(y);
                if(bx!=-1&&by!=-1) changeCellVal(getBoardX(x),getBoardY(y));
            }
        });
        
        getFileBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("getFileBtn");
                myContentPane.remove(theBoard);
                newBoardFromFile( );
                // no, bad! myContentPane.removeAll();
                myContentPane.add( theBoard, BorderLayout.CENTER);
                myContentPane.validate(); 
                myContentPane.repaint();
            }
        }); // end of addActionListener
        
        randomBoardBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("randomBoard");
                myContentPane.remove(theBoard);
                
                theBoard = new LifeBoard( 16, 16 );
                theBoard.setBackground( Color.GREEN );
                // no, bad! myContentPane.removeAll();
                myContentPane.add( theBoard, BorderLayout.CENTER);
                //theBoard.paintImmediately(theBoard.getVisibleRect());
                myContentPane.validate();
                //myContentPane.repaint();
                stepContinuouslyBtn.setText("Run");
                /* [ ] ?? we should have variable for what kind of stepping !! */
                repaint();
            }
        }); // end of addActionListener
        
        stepOneBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("step");
                lifeStep( 1 );
            }
        }); // end of addActionListener
        
        stepTenBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("step10");
                lifeStep( 10 );
            }
        }); // end of addActionListener
        
        stepContinuouslyBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("stepContinuously");
                stepContinuously();
            }
        }); // end of addActionListener

        quitBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("quit");
                System.exit( 0 );
            }
        }); // end of addActionListener
    } // addActionListenersToMyButtons( )
    
    
    /**
     * Initialize the delay slider and decorate it!
     */
    JPanel buildDelayPanel( ) {
        delaySlider = new JSlider(JSlider.HORIZONTAL, /*min:*/0, /*max*/200, /*startVal:*/0);
        JPanel delayPanel = new JPanel( );
        delayPanel.add( new JLabel("Delay:") );
        delayPanel.add( delaySlider );
        delaySlider.setToolTipText("Milliseconds delay...");
        //Turn on labels at major tick marks.
        delaySlider.setMajorTickSpacing(100);
        delaySlider.setMinorTickSpacing(10);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        return delayPanel;
    } /* buildDelayPanel( ) */
    
    
/*
Don't sub-components automatically get painted? 
    public void paint( Graphics g ) {
        this.setVisible( true); // necessary??
        if ( theBoard != null ) {
            theBoard.paint( g );
            // theBoard.repaint( ); // which is better??
        }
    } // paint( )
*/

    void newBoardFromFile(  ) {
        try {
            myContentPane.remove(theBoard);
            theBoard = new LifeBoard( /* to find parent frame */ this );
            resize( theBoard.wholePictureWidth, theBoard.wholePictureHeight );
            // no, bad!  myContentPane.removeAll();
            myContentPane.add( theBoard, BorderLayout.CENTER);
            /* myContentPane.*/validate(); 
            /* myContentPane.*/repaint();
        } catch( Exception ex ) {
            System.out.println( "Error '" + ex + "' while reading file" );
        }
        repaint();
        stepContinuouslyBtn.setText("Run");
    } //newBoardFromFile
    
    
	/**
	* From Gavin 
	* [ ] ??? We should have variable, not use button name as flag!
	*/
    void stepContinuously(){
        if (stepContinuouslyBtn.getText().equals("Pause")) {
            stepContinuouslyBtn.setText("Run");
        } else {
            stepContinuouslyBtn.setText("Pause");
        }
    } /* stepContinuously( ) */
    
    
    /**
     * Tells the board to live life for a few turns.
     */
    void lifeStep ( int howManySteps ) {
        for ( int i = 0; i < howManySteps; ++i ) {
                theBoard.step();
                // theBoard.quickDraw( g );   // need to get g from somewhere!
                //System.out.println( i + " " );;
        } // for howManySteps
        repaint( );
    } // lifeStep()


} // Class LifeGame