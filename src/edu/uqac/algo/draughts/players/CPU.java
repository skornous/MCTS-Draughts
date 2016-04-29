package edu.uqac.algo.draughts.players;

import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.utilities.Board;

import java.util.List;

public class CPU extends Player {
    public CPU(List<Piece> pieces, Board board) {
        super(pieces, board);
    }

    @Override
    public boolean playYourTurn() {
        return false;
    }

    @Override
    public void moveAPiece() {

    }
}
