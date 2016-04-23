package edu.uqac.algo.draughts.players;

import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.utilities.Board;

import java.util.List;

public abstract class Player {
    //todo add everything the player need to play
    protected List<Piece> pieces;
    protected Board board;

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

    // the core of the player, play your turn :O
    public abstract void playYourTurn();
    // better move a piece
    public abstract void moveAPiece();
}
