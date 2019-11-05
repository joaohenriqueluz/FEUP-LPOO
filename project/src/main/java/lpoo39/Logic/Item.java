package lpoo39.Logic;

public abstract class Item extends Element {

    public Item(Position position) {
        super(position);
    }

    public abstract void affectHero();

    public abstract boolean isHere(Position p);

    public abstract String getDescription();

    public abstract int getQuantity();
}
