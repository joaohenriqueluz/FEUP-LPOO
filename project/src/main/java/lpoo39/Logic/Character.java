package lpoo39.Logic;

public abstract class Character extends Element {

    private int health;
    private int attack;
    private char direction;

    public Character(int health, int attack, Position position, char direction) {
        super(position);
        this.health = health;
        this.attack = attack;
        this.direction = direction;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    /*public void setHealth(int health) {
        this.health = health;
    }
*/
    public int getAttack() {
        return attack;
    }

    /*public void setAttack(int attack) {
        this.attack = attack;
    }
*/
    public void getsHurt(int damage) {
        health -= damage;
    }


}
