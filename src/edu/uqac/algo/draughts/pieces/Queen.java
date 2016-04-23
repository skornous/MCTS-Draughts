package edu.uqac.algo.draughts.pieces;

import edu.uqac.algo.draughts.utilities.Position;

public class Queen extends Piece {
    //todo give her crown to the queen (make her move and kill)
    public Queen(Position position) {
        super(position);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean canGoto(Position position) {
        return false;
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
