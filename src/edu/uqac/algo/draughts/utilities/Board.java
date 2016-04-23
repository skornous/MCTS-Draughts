package edu.uqac.algo.draughts.utilities;

import com.sun.istack.internal.Nullable;
import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.pieces.Pawn;
import edu.uqac.algo.draughts.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<Piece> blackPieces;
    private List<Piece> whitePieces;
    private List<Piece> pieces;

    public Board() throws BoardTooSmallException {
        this(8);
    }

    public Board(int size) throws BoardTooSmallException {
        if (size < 5) {
            throw new BoardTooSmallException();
        }
        this.size = size;
        this.blackPieces = new ArrayList<Piece>();
        this.whitePieces = new ArrayList<Piece>();
        this.pieces = new ArrayList<Piece>();

        this.init();
    }

    private void init() {
        // The number of pawns at start depends of the size of the board
        int nbRows = (int) Math.round(this.size / 2.5);

        // init white pieces
        for (int row = 0 ; row < nbRows ; row++) {
            for (int col = 0 ; col < this.size ; col += 2) {
                this.whitePieces.add(new Pawn(row, col + (row%2)));
            }
        }

        // init black pieces
        for (int row = 0 ; row < nbRows ; row++) {
            for (int col = 0 ; col < this.size ; col += 2) {
                this.blackPieces.add(new Pawn(this.size-row-1, col + ((row+1)%2)));
            }
        }

        this.pieces.addAll(this.blackPieces);
        this.pieces.addAll(this.whitePieces);
    }

    public boolean canMove(Position start, Position end) {
        //todo can move from start to end
        return false;
    }

    /**
     * Print the board's state
     */
    public void print() {
        for (int x = 0 ; x < this.size ; x++) {
            for (int y = 0 ; y < this.size ; y++) {
                Piece p = this.getPieceAtPosition(x, y);
                if (p != null) {
                    if (this.blackPieces.contains(p)) {
                        System.out.print("|o");
                    } else {
                        System.out.print("|x");
                    }
                } else {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }
    }

    /**
     * Get the piece at position (x, y)
     * @param x
     * @param y
     * @return a Piece if there is one, else null
     */
    @Nullable
    private Piece getPieceAtPosition(int x, int y) {
        Position pos = new Position(x, y);
        for (Piece p : this.pieces) {
            if (p.getPosition().equals(pos)) return p;
        }
        return null;
    }

    public int getSize() {
        return size;
    }
    public List<Piece> getPieces() {
        return this.pieces;
    }
    public List<Piece> getBlackPieces() {
        return blackPieces;
    }
    public List<Piece> getWhitePieces() {
        return whitePieces;
    }
}
