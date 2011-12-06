

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LifeBoardTest.
 *
 * @author  (Mike Roam)
 * @version (a version number or a date)
 */
public class LifeBoardTest
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
    @Before
    public void setUp()
    {
    }
    

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    

    @Test
    public void t1()
    {
        LifeBoard lifeBoard10x10 = new LifeBoard(10, 10);
    }
    

    @Test
    public void instantiate()
    {
        LifeBoard lifeBoar1 = new LifeBoard(2, 3);
        assertEquals(2, lifeBoar1.getCellsAcross());
        assertEquals(3, lifeBoar1.getCellsDown());
    }

    

    @Test
    public void boardsize()
    {
        LifeBoard lifeBoar1 = new LifeBoard(2, 3);
        assertEquals(false, lifeBoar1.badGridLoc(new java.awt.Point(1,1)));
    }
}


 // class LifeBoardTest

