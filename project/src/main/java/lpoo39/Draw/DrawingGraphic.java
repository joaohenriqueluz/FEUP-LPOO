package lpoo39.Draw;

import lpoo39.Logic.Element;

public interface DrawingGraphic {
    void closeScreen();

    DrawingElement getDrawingObject(Element element);

    void update();

    void updateLog();

    int getWidth();

    String getInput();
}
