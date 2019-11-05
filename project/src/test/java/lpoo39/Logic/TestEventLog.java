package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.EventLogLanterna;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEventLog {
    @Test
    public void startDrawingTest() throws Exception {
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        EventLog eventLog = Game.getLog();
        eventLog.setMessage("Testing");
        eventLog.startDraw();

        DrawingElement de = eventLog.getDrawingElement();
        assertNotEquals(null, de);

        EventLogLanterna ell = (EventLogLanterna) de;
        assertEquals("#FFFFFF", ell.getColor());
        assertEquals("Testing", ell.getMessage());
    }
}
