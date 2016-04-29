package edu.uqac.algo.draughts.utilities;

import com.sun.istack.internal.Nullable;
import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.pieces.Pawn;
import edu.uqac.algo.draughts.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        //todo redo the horizontal axis numbers, it's ugly af
        for (int i = 0; i < this.size / 10; i++) {
            System.out.printf(" ");
        }
        System.out.printf("   "); // 3 spaces cuz of opening and closing parentheses plus the number
        for (int i = 0; i < this.size; i++) {
            System.out.print(" " + (i+1));
        }
        System.out.println();
        for (int x = this.size-1 ; x >= 0 ; x--) {
            System.out.print("(" + (x + 1) + ")");
            if (this.size >= 10 && x+1 < 10) {
                System.out.printf(" ");
            }
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
    public Piece getPieceAtPosition(int x, int y) {
        Position pos = new Position(x, y);
        for (Piece p : this.pieces) {
            if (p.getPosition().equals(pos)) return p;
        }
        return null;
    }

    @Nullable
    public Map<Piece, List<Position>> getAllPossibleMoves() {
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
