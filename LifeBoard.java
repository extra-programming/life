// package <default>

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.*; /* provides "JPanel" class */
import java.awt.*;
//import java.awt.event.*;
import java.util.*; /* provides "Random( )" method */
import java.net.URL;


/**
 * Write a description of class LifeBoard here.
 * 
 * @author (Mike Roam) 
 * @version (2011 Oct 14, revised to use ints instead of booleans on the lifeboard)
 */
class LifeBoard extends JPanel implements MouseListener {
    /* Note: there's no such thing as JCanvas in Java 1.6 API 
    and advice online is to use JPanels and override their paintComponent( ) method,
    NOT overriding paint( ) like we used to do in awt.
   
    see <a href="http://www.oracle.com/technetwork/java/painting-140037.html">http://www.oracle.com/technetwork/java/painting-140037.html</a>
    */

    private static final long serialVersionUID = 42L;  // makes serializable happy
    private int cellsAcross = 20;
    private int cellsDown = 20;
    private int generationCount = 0;

    int cellWidth = 8;
    int cellHeight = 8;

    int topMargin = 0;
    int leftMargin = 0;

    int wholePictureWidth = 300;
    int wholePictureHeight = 300;
    Dimension minSize = new Dimension(wholePictureWidth, wholePictureHeight);
  
    //final int /*boolean*/ ALIVE2 = 2/*true*/;
    //final int /*boolean*/ ALIVE1 = 1/*true*/;
    //final int /*boolean*/ DEAD = 0/*false*/;             obselete now it uses a Rules class
    //final int maxLifeCellValue = 2;
    //final int minLifeCellValue = 0;

    final Color alive2Color = Color.black;
    final Color alive1Color = Color.red;
    final Color deadColor = Color.white;
    
    Rules theRules;
    
    /* two-dimensional array of ALIVE1 or ALIVE2 or DEAD*/ 
    int/*boolean*/[][] theData = null;
    /** holds data as we calculate new board */
    int/*boolean*/[][] newData = null;
    Point badPoint =  new Point( -1, -1);  /* used in badLoc */


    /**
    * Constructor for specified width and height
    * Creates a new board, loads it with random dots
    * ??( perhaps could get dots from a file!?)
    */
    LifeBoard( int newCellsAcross, int newCellsDown , Rules newRules) throws ArrayIndexOutOfBoundsException {
        super();
        generationCount=0;
        theRules=newRules;
        setBackground( Color.GRAY );
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        if (newCellsAcross < 0) {
            throw new ArrayIndexOutOfBoundsException("bad grid width " + newCellsAcross);
        }
        if (newCellsDown < 0) {
            throw new ArrayIndexOutOfBoundsException("bad grid height " + newCellsDown);
        }

        cellsAcross = newCellsAcross;
        cellsDown = newCellsDown;
        Random myNRG = new Random();
        theData = new int /*boolean*/ [ cellsAcross ] [ cellsDown ];
        newData = new int /*boolean*/ [ cellsAcross ] [ cellsDown ];
        int[] cellStates = theRules.getAcceptableCellStates();
        for ( int x = 0; x < cellsAcross; ++x ) {
            for ( int y = 0; y < cellsDown; ++y ) {
                /*if ( (myNRG.nextInt() % 3) == 1 ) {
                    theData[x][y] = ALIVE1;
                } else if ( (myNRG.nextInt() % 3) == 2 ) {
                    theData[x][y] = ALIVE2;
                } else {
                    theData[x][y] = DEAD;
                } */
                int randState = myNRG.nextInt(cellStates.length*2);
                if(randState>=cellStates.length){
                    theData[x][y]=theRules.getDefaultCellState();
                    continue;
                }
                theData[x][y] = cellStates[randState];
            } // for y
        } // for x
        addMouseListener(this);
        this.repaint( );
        //System.out.println( this.toString() );
    } // Lifeboard constructor



    /**
    * Constructor (for reading from a file?)
    */
    LifeBoard( Component theFrame ) {
        super();
        generationCount=0;
        setBackground( Color.GRAY );
        getBoardFromFile( theFrame ); // Filename is found by theFrame somehow
        this.repaint( );
        // System.out.println( this.toString() );
    } // Lifeboard constructor
    
    public int getGenerationCount(){
        return generationCount;
    }
    
    public void setRules(Rules newRules){
        theRules=newRules;
        for(int i=0;i<theData.length;i++){
            for(int j=0;j<theData[i].length;j++){
                if(badCellValue(theData[i][j])){
                    theData[i][j]=theRules.getCellState(theData[i][j]);
                }
            }
        }
        repaint();
    }
    
    public String getRulesName(){
        return theRules.getClass().getSimpleName();
    }
    
    public void mousePressed(MouseEvent e) {
       // e.getClickCount(), e);
    }

    
    public void mouseReleased(MouseEvent e) {
       // e.getClickCount(), e);
    }

    
    public void mouseEntered(MouseEvent e) {
       // saySomething("Mouse entered", e);
    }

    
    public void mouseExited(MouseEvent e) {
       // saySomething("Mouse exited", e);
    }

    
    public void mouseClicked(MouseEvent e) {
       // e.getClickCount() + ")", e);
       Point whereClicked = e.getPoint( );
       System.out.println("Click at (local):" + whereClicked);
    } /* mouseClicked( ) */

    
    
    /**
     * 
     */
    public int getCellsAcross( ) {
        return cellsAcross;
    }
    
    
    /**
     * 
     */
    public int getCellsDown( ) {
        return cellsDown;
    }
    
    
    /**
     * Given a Point representing the [x],[y] loc of the cell in the grid
     * tell us the value in that cell.
     * Note: the Point is NOT screenloc in pixels!
     * Throw exception if given a bad location that doesn't exist on the grid.
     * Note: ArrayIndexOutOfBoundsException is an "unchecked" exception, meaning
     * you don't have to specify that you throw it, and callers don't have to
     * worry about catching it.
     */
    public int getCellData( Point cellLoc ) throws ArrayIndexOutOfBoundsException {
        if ( badGridLoc( cellLoc) ) {
            throw new ArrayIndexOutOfBoundsException("bad gridloc " + cellLoc + " in grid with width=" + cellsAcross + ", height=" + cellsDown);
            //return badPoint;
        } else {
            return theRules.getCellState(theData[cellLoc.x][cellLoc.y]);
        }
    } // getCellData( )
    
    
    /**
     * Given a Point representing the [x],[y] loc of the cell in the grid
     * and a legit cell data value, put the value into that cell.
     * Note: the Point is NOT screenloc in pixels!
     * 
     * ?? What to do if given bad (non-conway) cell value? Ignore it? Throw Exception?
     * 
     * Throw exception if given a bad location that doesn't exist on the grid.
     * Note: ArrayIndexOutOfBoundsException is an "unchecked" exception, meaning
     * you don't have to specify that you throw it, and callers don't have to
     * worry about catching it.
     */
    public void setCellData( Point cellLoc, int newCellValue ) throws ArrayIndexOutOfBoundsException {
        if ( badGridLoc( cellLoc) ) {
            throw new ArrayIndexOutOfBoundsException("bad gridloc " + cellLoc + " in grid with width=" + cellsAcross + ", height=" + cellsDown);
        }
        //if (badCellValue( newCellValue )) {
            
        //} else {
            // if  (theData[cellLoc.x][cellLoc.y] != newCellValue) {
           theData[cellLoc.x][cellLoc.y] = theRules.getCellState(newCellValue);
           // }
        //}
    } // setCellData( )
    

    /**
    * Given a Point representing screenloc in pixels (over,down),
    * return a Point representing the [x],[y] loc of the cell in the grid.
    * What do we return if input gives us bad coords??
    */
    Point whichCell( Point clickLoc ) {
        /* Here's the equation for drawing cells:
        myg.fillOval( /* left edge.. leftMargin + (x * cellWidth),
                              /* top edge..  topMargin + (y * cellHeight), 
                                cellWidth, cellHeight ); */
        if ( badGraphicLoc( clickLoc) ) {
            return badPoint;
        }
        return null;
    } /* whichCell( ) */

    
    /**
     * Use this to check if a grid cell is being given a legit value.
     * This has to change if we allow different values, duh!
     * Will really have to change if we use a class to represent cell values!
     */
    public boolean badCellValue( int theValue ) {
        int[] states = theRules.getAcceptableCellStates();
        Arrays.sort(states);
        return Arrays.binarySearch(states,theValue)>0;
        /*for(int i=0;i<states.length;i++){
            if(theValue == states[i]) return true;
        }
        return false;*/
        //return (( theValue < minLifeCellValue ) || ( theValue > maxLifeCellValue));
    } 
       

    /**
    * Tells us if these screenloc pixel coordinates are out of bounds.
    * Note: this is different from gridloc coordinates which speak in
    * terms of [x],[y] loc of cells in the data grid!
    */
    boolean badGraphicLoc( Point clickLoc ) {
        if ( (clickLoc.x < 0 ) || (clickLoc.x >  wholePictureWidth)) { 
            return true;
        }
        if ( (clickLoc.y < 0 ) || (clickLoc.y >  wholePictureHeight)) { 
            return true;
        }
        return false;
    } /* badLoc( ) */
    
    
    /**
    * Tells us if this  [x],[y] loc of cells in the data grid is out of bounds.
    * Note: this is different from graphicloc coordinates which speak in
    * terms of screenloc pixel coordinates!
    * Note: beware the OBOB. An array size 3 has cells 0,1,2 so error if x >= 3, not just x>3!
    */
    boolean badGridLoc( Point gridLoc ) {
        if ( (gridLoc.x < 0 ) || (gridLoc.x >= cellsAcross ) ) {
            return true;
        }
        if ( (gridLoc.y < 0 ) || (gridLoc.y >= cellsDown) ) { 
            return true;
        }
        return false;
    } /* badLoc( ) */


    /**
    * automatically called whenever the screen needs refresh
    */
    public void paintComponent( Graphics myg ) {
        // System.out.println("LifeBoard.paintComponent( )");
        // quickDraw( myg ); // doesn't seem to be hiding dead cells
        
        fullDraw( myg ); // only when flagged??
    } // paint( )


    public Dimension getMinimumSize() {
        return minSize;
    }

    
    public Dimension getPreferredSize() {
        return minSize;
    }


    /**
    * redraws every cell, unlike "quickDraw" which merely draws changed cells
    */
    public void fullDraw( Graphics myg ) {
        for ( int x = 0; x < cellsAcross; ++x ) {
            for ( int y = 0; y < cellsDown; ++y ) {
                /*if ( theData[x][y] == ALIVE2 ) {
                    myg.setColor( alive2Color );
                } else if ( theData[x][y] == ALIVE1 ) {
                    myg.setColor( alive1Color );
                } else{
                    myg.setColor( deadColor );
                };*/
                myg.setColor(theRules.getCellColor(theData[x][y]));
                myg.fillOval( /* left edge */ leftMargin + (x * cellWidth),
                              /* top edge */  topMargin + (y * cellHeight), 
                                cellWidth, cellHeight );
            } // for y
        } // for x
    } // fullDraw( )


    /** 
    * I could call this if I knew how to get the current myg?
    * Or just let paint call this?
    */
    void quickDraw( Graphics myg ) {
        // This draws only the cells that have changed since last time...;
        for ( int x = 0; x < cellsAcross; ++x ) {
            for ( int y = 0; y < cellsDown; ++y ) {
                if ( theData[x][y] != newData[x][y] ) {
                    /*if ( theData[x][y] == ALIVE2 ) {
                        myg.setColor( alive2Color );
                    } else if ( theData[x][y] == ALIVE1 ) {
                        myg.setColor( alive1Color );
                    } else {
                        myg.setColor( deadColor );
                    };*/
                    myg.setColor(theRules.getCellColor(theData[x][y]));
                    myg.fillOval( /* left: */ leftMargin + (x * cellWidth),
                                /* top: */ topMargin + (y * cellHeight), 
                                /* width: */    cellWidth,
                                /* height: */   cellHeight );
                } // if new state
            } // for y
        } // for x
    } // quickDraw( )



    /**
    * Creates a new generation.
    * Currently hard-wired to use Conway rules.
    * plus living cell with 3 neighbors turns alive2
    */
    void step() {
        for ( int x = 0; x < cellsAcross; ++x ) {
            for ( int y = 0; y < cellsDown; ++y ) {
                int numOfNeighbors = neighborCount( x, y );
                /*if ( theData[x][y] == ALIVE1 ) {
                    if (numOfNeighbors == 2)  {
                        newData[x][y] = ALIVE2;
                    } else if (numOfNeighbors == 3) {
                        newData[x][y] = ALIVE1;
                    } else {
                        newData[x][y] = DEAD;
                    }
                } else if ( theData[x][y] == ALIVE2 ) {
                    if (numOfNeighbors == 2) {
                        newData[x][y] = ALIVE1;
                    } else if (numOfNeighbors == 3) {
                        newData[x][y] = ALIVE2;
                    } else {
                        newData[x][y] = DEAD;
                    }
                } else {
                    if ( (numOfNeighbors == 3) ) {
                        newData[x][y] = ALIVE1;
                    } else {
                        newData[x][y] = DEAD;
                    }
                }*/
                newData[x][y] = theRules.getCellState(theData[x][y],numOfNeighbors);
            } // for y
        } // for x 
         generationCount++;
        // now swap the pointers to the boards;;
        int /*boolean*/[][] tempPtr = theData;
        theData = newData;
        newData = tempPtr;
    } // step( )




    /* This does toroidal wrap? Perhaps be optional with boolean flag "torus" */
    int neighborCount( int x, int y ) {
        int totalNeighbors = 0;
        /* count all the surroundings AND ourself, since it's easier to set up the loops that way */;
        
        for ( int xi = - 1; xi < 2; ++xi ) {
            for ( int yi = - 1; yi < 2; ++yi ) {
                // if (torus) {
                /* use "MOD" to control wrap around */;
                /* have to add cellsAcross to force wrap around 
                since -1 % 6 = -1 !!!!! */;
                int xiloc = (x + cellsAcross + xi) % cellsAcross;
                int yiloc = (y + cellsDown + yi) % cellsDown;
                /*if (( theData[xiloc][yiloc] == ALIVE1 ) ||  ( theData[xiloc][yiloc] == ALIVE2 )) {
                    ++totalNeighbors;
                }*/
                totalNeighbors+=theRules.getNeighborValue(theData[xiloc][yiloc]);
            }
        }
        // don't count yourself!;
        /*if (( theData[x][y] == ALIVE1) || ( theData[x][y] == ALIVE2 )) {
            --totalNeighbors;
        };*/
        totalNeighbors-=theRules.getNeighborValue(theData[x][y]);
        return totalNeighbors;
    } // neighborCount( )

    boolean isAlphabetic(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }

    /** 
    * This should use some java kind of split or parse to get the ints!
    */
    void getBoardFromFile( Component theFrame ) {
            /* get board from a file! 
        File Format: life 8 8 \n":...AAA..\n:...AAA..."                       with rules file format is '.' for dead, number for state
        info: starts with word "life" and space and width and space and 
        height AND SPACE
        then the data, line by line, each line starting with colon,
        with periods for dead cells and 'A's for live ones
        AND '2' for alive2
            */;
        URL myF = ComponentUtil.getSomeOldFile( theFrame );
        String myString = ComponentUtil.readStringFromFile( myF );
        System.out.println(myString);
        /* file should start with word "life\n"...*/;
        //try {
            /* going to try to read "life <int> <int>" from start of file */;
            int whereFirstBlankIs = myString.indexOf( ' ' );
            String firstWord = myString.substring( 0, whereFirstBlankIs );
            if ( ! "life".equals( firstWord ) ) {
                System.out.println( "first chars in life file should be 'life' but is '" + myString.substring(0, 4) + "'" );
                throw new FileFormatException();
            };
            int whereSecondBlankIs = myString.indexOf( ' ', whereFirstBlankIs + 1 );
            String secondWord = myString.substring( whereFirstBlankIs + 1, whereSecondBlankIs );
            int newCellsAcross = Integer.parseInt( secondWord );
            int whereThirdBlankIs = myString.indexOf( ' ', whereSecondBlankIs + 1 );
            String thirdWord = myString.substring( whereSecondBlankIs + 1, whereThirdBlankIs );
            int newCellsDown = Integer.parseInt( thirdWord );
            cellsAcross = newCellsAcross;
            cellsDown = newCellsDown;
            wholePictureWidth = leftMargin + (cellsAcross * cellWidth) + leftMargin;
            wholePictureHeight = topMargin + (cellsDown * cellHeight) + topMargin;
            theData = new int /*boolean*/ [ cellsAcross ] [ cellsDown ];
            newData = new int /*boolean*/ [ cellsAcross ] [ cellsDown ];
            int whereDataIs = whereThirdBlankIs + 1;
            StringBuilder sb = new StringBuilder();
            for(whereDataIs = 1 + myString.indexOf("Rules:",whereDataIs) + 5; isAlphabetic(myString.charAt(whereDataIs)); whereDataIs++){
                sb.append(myString.charAt(whereDataIs));
            }
            Class maybeRulesClass;
            try{
                maybeRulesClass = Class.forName(sb.toString());
            }catch(Exception e){
                 throw new FileFormatException(sb+" is not a valid Rules");
            }
            Object maybeRules;
            try{
                maybeRules = maybeRulesClass.newInstance();
            }catch(Exception e){
                throw new FileFormatException(maybeRulesClass+" could not be instantialized");
            }
            if(!(maybeRules instanceof Rules)) throw new FileFormatException(maybeRulesClass+" is not a valid Rules");
            theRules = (Rules)maybeRules;
            for ( int y = 0; y < cellsDown; ++y ) {
                /* each line starts with : */;
                whereDataIs = 1 + myString.indexOf( ':', whereDataIs );
                for ( int x = 0; x < cellsAcross; ++x ) {
                    /*if ( (myString.charAt( whereDataIs )) == '.' ) {
                        theData[x][y] = DEAD;
                    } else if ( (myString.charAt( whereDataIs )) == 'A' ) {
                        theData[x][y] = ALIVE1;
                    } else {
                        theData[x][y] = ALIVE2;
                    }*/
                    int curData=0;
                    try{
                        curData = theRules.readCellState(myString.charAt( whereDataIs ));
                    }catch(IllegalArgumentException iae){
                        throw new FileFormatException(iae);
                    }
                    //if(badCellValue(curData)) throw new FileFormatException(curData+" is not a valid cell datum for the current rules");
                    theData[x][y]=curData;
                    ++whereDataIs;
                }
            }
        /*} catch( Exception e ) {
            System.out.println( "Error in getBoardFromFile: " + e.toString() );
            //this.LifeBoard( 16, 16 ) ?? have to fill in a dud board, perhaps random?;
        }*/
        generationCount=0;
        this.repaint();
    } // getBoardFromFile

    int charToIntFromFile(char c){
        if(c=='.') return theRules.getDefaultCellState();
        if(c>='0'&&c<='9') return c-'9';
        if(c>='a'&&c<='z') return c-'a'+10;
        if(c>='A'&&c<='Z') return c-'A'+36;
        else throw new IllegalArgumentException(c+" is not an acceptable imput for 'charToIntFromFile'");
    }
    
    /**
     * return a text image of the <em>current</em> board
     */
    public String toString() {
        StringBuilder mySB = new StringBuilder()/*Buffer( (cellsAcross + 1) * cellsDown )*/;
        for ( int y = 0; y < cellsDown; ++y ) {
            for ( int x = 0; x < cellsAcross; ++x ) {
                /*if ( theData[x][y] == ALIVE1 ) {
                    mySB.append( 'A' );
                } else if ( theData[x][y] == ALIVE2 ) {
                    mySB.append( '2' );
                } else {
                    mySB.append( '.' );
                };*/
                if(theData[x][y] == theRules.getDefaultCellState()) mySB.append('.');
                else mySB.append(theData[x][y]);
            };
            mySB.append( '\n' );
        };
        //String tempStr = new String( mySB );
        return mySB.toString();
    } // toString( )


} // Class Lifeboard