package edu.uqac.algo.draughts.utilities;

import edu.uqac.algo.draughts.pieces.Piece;

import java.util.List;

public class Player {
    //todo add everything the player need to play
    private List<Piece> pieces;
    private Board board;

    public Player(List<Piece> pieces, Board board) {
        this.pieces = pieces;
        this.board = board;
    }

    /**
     * Can the player move a piece ?
     * @return true if at least one piece can move
     */
    public boolean canPlay() {
        for (Piece p : this.pieces) {
            if (p.canMove()) {
                return true;
            }
        }
        return false;
    }
}
