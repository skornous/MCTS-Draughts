package edu.uqac.algo.draughts.players;

import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.utilities.Board;
import edu.uqac.algo.draughts.utilities.Position;

import java.util.List;

public abstract class Player {
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

    public Piece getPieceAtPosition(Position pos) {
        for (Piece p : this.pieces) {
            if (p.getPosition().equals(pos)) {
                return p;
            }
        }
        return null;
    }

    public Piece getPieceAtPosition(int x, int y) {
        return this.getPieceAtPosition(new Position(x, y));
    }

    // the core of the player, play your turn :O
    public abstract boolean playYourTurn();
    // better move a piece
    public abstract boolean moveAPiece();
}
