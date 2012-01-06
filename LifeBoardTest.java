

//import static org.junit.Assert.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;

/**
 * The test class LifeBoardTest.
 *
 * @author  (Mike Roam)
 * @version (a version number or a date)
 */
public class LifeBoardTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class LifeBoardTest
     */
    public LifeBoardTest()
    {
    }
    

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    //@Before
    public void setUp()
    {
    }
    

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    //@After
    public void tearDown()
    {
    }
    


    public void test1()
    {
        LifeBoard lifeBoard10x10 = new LifeBoard(10, 10, new OurRules());
    }
    


    public void testInstantiate()
    {
        LifeBoard lifeBoar1 = new LifeBoard(2, 3, new OurRules());
        assertEquals(false, lifeBoar1.equals(null));
        assertEquals(2, lifeBoar1.getCellsAcross());
        assertEquals(3, lifeBoar1.getCellsDown());
    }

    

    //@Test
    public void testBoardsize()
    {
        LifeBoard lifeBoar1 = new LifeBoard(2, 3, new OurRules());
        assertEquals(false, lifeBoar1.badGridLoc(new java.awt.Point(0,1)));
        assertEquals(false, lifeBoar1.badGridLoc(new java.awt.Point(0,0)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(-1,1)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(1,-1)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(1,3)));
    }


    public void testInstance0()
    {
        LifeBoard lifeBoar1 = new LifeBoard(0, 0, new OurRules());
        assertEquals(false, lifeBoar1==null);
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(0,1)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(0,0)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(-1,1)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(1,-1)));
        assertEquals(true, lifeBoar1.badGridLoc(new java.awt.Point(1,3)));
    }

    public void test1a()
    {
        LifeBoard lifeBoar1 = new LifeBoard(2, 3, new OurRules());
        assertEquals(2, lifeBoar1.getCellsAcross());
        assertEquals(3, lifeBoar1.getCellsDown());
    }

    
    public void testNegativeBoardSize()
    {
        // oops, brutal: perhaps do try catch
        String errStr=null;
        try{
            new LifeBoard(1,-7, new OurRules());
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
            errStr=e.toString();
        }
        assertEquals(true, errStr!=null);
        errStr=null;
        try{
            new LifeBoard(-3,4, new OurRules());
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
            errStr=e.toString();
        }
        assertEquals(true,   errStr!=null);
    }
    
    
    public void testAutumn() // may not work with rules
    {
        LifeBoard lifeBoar1 = new LifeBoard(4, 4, new OurRules());
        assertEquals(0, lifeBoar1.getCellData(new java.awt.Point(1,1)));
    }
    
    
    public void testCellDataSetGetAndNeighborCount() // may not work with rules
    {
        LifeBoard lifeBoar1 = new LifeBoard(2, 3, new OurRules());
        lifeBoar1.setCellData(new java.awt.Point(1,2), 1);
        assertEquals(1, lifeBoar1.getCellData(new java.awt.Point(1,2)));
        assertEquals(0, lifeBoar1.neighborCount(1, 2));
        assertEquals(0, lifeBoar1.getCellData(new java.awt.Point(1,1)));
        lifeBoar1.getCellsAcross();
        lifeBoar1.getCellsDown();
    }

    
}







 // class LifeBoardTest

