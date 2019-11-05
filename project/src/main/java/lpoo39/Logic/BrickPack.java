package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

//This collectible affects the hero by adding a 'quantity' amount of bricks to the inventory,
//as seen in the affectHero method
public class BrickPack extends Item {

    private Hero hero;
    private int quantity;

    public BrickPack(Hero hero, Position position, int quantity) {
        super(position);
        this.hero = hero;
        this.quantity = quantity;
    }

    @Override
    public void affectHero() {
        hero.reloadBricks(quantity);
    }

    @Override
    public boolean isHere(Position p) {
        return super.getPosition().equals(p);
    }


    @Override
    public String getDescription() {
        return "How amazing " + quantity + " bricks!";
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(position, "#FFFF33", "█");
    }

    @Override
    public void endDraw() {

    }
}
