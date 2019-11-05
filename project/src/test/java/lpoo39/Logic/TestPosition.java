package lpoo39.Logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPosition {

    Position position;

    @Before
    public void setUpTests(){
        position = new Position (23,10);
    }

    @Test
    public void createPosition(){

        assertEquals(23, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    public void settersPosition(){
        position.setX(20);
        assertEquals(20, position.getX());

        position.setY(15);
        assertEquals(15, position.getY());
    }

    @Test
    public void equalsPosition(){
        Position p  = new Position(23,10);
        assertTrue(position.equals(p) && p.getClass() == Position.class );
    }

    @Test
    public void equalsSamePosition(){
        assertTrue(position.equals(position));
    }

    @Test
    public void equalsNotValid(){
        Position p = null;
        assertTrue(!position.equals(p) );
    }
}
