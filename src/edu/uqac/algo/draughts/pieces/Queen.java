package edu.uqac.algo.draughts.pieces;

import edu.uqac.algo.draughts.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    //todo give her crown to the queen (make her move and kill)
    public Queen(Position position) {
        super(position);
    }

    public Queen(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canGoto(Position position) {
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
