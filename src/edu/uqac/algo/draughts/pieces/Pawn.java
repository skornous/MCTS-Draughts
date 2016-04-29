package edu.uqac.algo.draughts.pieces;

import edu.uqac.algo.draughts.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(int x, int y) {
        super(x, y);
    }

    public Pawn(Position position) {
        super(position);
    }

    /**
     * Check if the move if allowed, only in terms of way to move.
     * Meaning, this function only check if the move seems logic for the pawn, not if the new position is occupied.
     *
     * @param position the position to "go to"
     * @return a boolean
     */
    @Override
    public boolean canGoto(Position position) {
        int newX = position.getX();
        int newY = position.getY();

        // move diagonally (both x and y move by 1 or -1)
        if ((newX - this.getX() == 1 || newX - this.getX() == -1) && (newY - this.getY() == 1 || newY - this.getY() == -1)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canMove() {
        return !this.getPossibleMoves().isEmpty();
    }

    @Override
    public List<Position> getPossibleMoves() {
        List<Position> moves = new ArrayList<Position>();
        // up right
        if (this.canGoto(this.getX() + 1, this.getY() + 1)) {
            moves.add(new Position(this.getX() + 1, this.getY() + 1));
        }
        // up left
        if (this.canGoto(this.getX() + 1, this.getY() - 1)) {
            moves.add(new Position(this.getX() + 1, this.getY() - 1));
        }
        // down right
        if (this.canGoto(this.getX() - 1, this.getY() + 1)) {
            moves.add(new Position(this.getX() - 1, this.getY() + 1));
        }
        // down left
        if (this.canGoto(this.getX() - 1, this.getY() - 1)) {
            moves.add(new Position(this.getX() - 1, this.getY() - 1));
        }
        return moves;
    }


}
