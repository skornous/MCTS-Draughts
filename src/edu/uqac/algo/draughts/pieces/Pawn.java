package edu.uqac.algo.draughts.pieces;

import edu.uqac.algo.draughts.utilities.Position;

public class Pawn extends Piece {
    //todo everything... Give them a mouth and some legs (force them to eat and make them move)
    public Pawn(int x, int y) {
        super(x, y);
    }

    public Pawn(Position position) {
        super(position);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean canMove() {
        return false;
    }
}
