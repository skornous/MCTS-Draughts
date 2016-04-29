package edu.uqac.algo.draughts.pieces;

import edu.uqac.algo.draughts.utilities.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {
    //todo everything... Give them a mouth and some legs (force them to eat and make them move)
    public Pawn(int x, int y) {
        super(x, y);
    }

    public Pawn(Position position) {
        super(position);
    }

    /**
     * Check if the move if allowed, only in terms of way to move.
     * Meaning, this function only check if the move seems logic for the pawn, not if the new position is occupied.
     * @param position the position to "go to"
     * @return a boolean
     */
    @Override
    public boolean canGoto(Position position) {
        int newX = position.getX();
        int newY = position.getY();

        // move diagonally (both x and y move by 1 or -1)
        if ((newX - this.getX() == 1 || newX - this.getX() == -1) &&(newY - this.getY() == 1 || newY - this.getY() == -1)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public List<Position> getPossibleMoves() {
        return new ArrayList<Position>();
    }


}
