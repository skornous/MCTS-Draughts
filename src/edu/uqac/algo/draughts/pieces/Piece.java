package edu.uqac.algo.draughts.pieces;

import edu.uqac.algo.draughts.utilities.Position;

public abstract class Piece {
    //todo make them move
    //todo make them able to kill
    private Position position;

    public Piece(int x, int y) {
        this(new Position(x, y));
    }

    public Piece(Position position) {
        this.position = position;
    }

    public abstract void move();
    public abstract boolean canGoto(Position position);
    public abstract boolean canMove();

    public int getX() {
        return this.getPosition().getX();
    }
    public void setX(int x) {this.getPosition().setX(x);}

    public int getY() {
        return this.getPosition().getY();
    }
    public void setY(int y) {this.getPosition().setY(y);}

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
