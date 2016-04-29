package edu.uqac.algo.draughts.utilities;

import com.sun.istack.internal.Nullable;
import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.pieces.Pawn;
import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.pieces.Queen;

import java.util.ArrayList;
import java.util.HashMap;
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
        if (size < 5 || size % 2 == 1) {
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
        //can move from start to end
        Piece pieceToMove = this.getPieceAtPosition(start);
        if (pieceToMove == null) return false;
        Piece endPosition = this.getPieceAtPosition(end);
        if (endPosition != null) {
            return false;
        }
        return true;
    }

    public boolean canEat(Piece pieceToMove, Piece endPosition) {
        // get direction Ne/Nw/Se/Sw
        if (Math.abs(pieceToMove.getX() - endPosition.getX()) == 1 && Math.abs(pieceToMove.getY() - endPosition.getY()) == 1) {
            int direction = 0;
            if (pieceToMove.getX() < endPosition.getX()) {
                // North
                if (pieceToMove.getY() < endPosition.getY()) {
                    direction = 0; // East
                } else if (pieceToMove.getY() > endPosition.getY()) {
                    direction = 1; // West
                } else { // Unknown direction
                    return false;
                }
            } else if (pieceToMove.getX() > endPosition.getX()) {
                // South
                if (pieceToMove.getY() < endPosition.getY()) {
                    direction = 2; // East
                } else if (pieceToMove.getY() > endPosition.getY()) {
                    direction = 3; // West
                } else { // Unknown direction
                    return false;
                }
            } else { // Unknown direction
                return false;
            }
            // get position behind the endPosition in this direction
            Position behind;
            switch (direction) {
                case 0: //NE
                    behind = new Position(endPosition.getX() + 1, endPosition.getY() + 1);
                    break;
                case 1: //NW
                    behind = new Position(endPosition.getX() + 1, endPosition.getY() - 1);
                    break;
                case 2: //SE
                    behind = new Position(endPosition.getX() - 1, endPosition.getY() + 1);
                    break;
                default: //SW
                    behind = new Position(endPosition.getX() - 1, endPosition.getY() - 1);
            }
            return this.getPieceAtPosition(behind) == null;
        }
        return false;
    }

    public boolean eatPiece(Piece eater, Piece eaten) {
        if (this.canEat(eater, eaten)) {
            this.whitePieces.remove(eaten);
            this.blackPieces.remove(eaten);
            return this.pieces.remove(eaten);
        }
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
        return this.getPieceAtPosition(new Position(x, y));
    }

    @Nullable
    public Piece getPieceAtPosition(Position pos) {
        for (Piece p : this.pieces) {
            if (p.getPosition().equals(pos)) return p;
        }
        return null;
    }

    @Nullable
    public Piece getPieceAtPositionFromList(Position pos, List<Piece> pieces) {
        for (Piece p : pieces) {
            if (p.getPosition().equals(pos)) return p;
        }
        return null;
    }

    @Nullable
    public Map<Piece, List<Position>> getAllPossibleMoves() {
        Map<Piece, List<Position>> moves = new HashMap<Piece, List<Position>>();
        for (Piece p : this.pieces) {
            List<Position> filteredMoves = this.filterMoves(p.getPossibleMoves());
            if (!filteredMoves.isEmpty()) {
                moves.put(p, filteredMoves);
            }
        }
        return moves;
    }

    @Nullable
    public Map<Piece, List<Position>> getPossibleMoves(List<Piece> pieces) {
        Map<Piece, List<Position>> moves = new HashMap<Piece, List<Position>>();
        for (Piece p : pieces) {
            List<Position> filteredMoves = this.filterMoves(p.getPossibleMoves());
            if (!filteredMoves.isEmpty()) {
                moves.put(p, filteredMoves);
            }
        }
        return moves;
    }

    public char[][] getState() {
        char[][] state = new char[size][size];

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                Piece p = this.getPieceAtPosition(i, j);
                if (p == null) {
                    state[i][j] = ' ';
                } else {
                    if (this.whitePieces.contains(p)) { // white piece
                        if (p instanceof Pawn) { // pawn
                            state[i][j] = 'x';
                        } else { // queen
                            state[i][j] = 'X';
                        }
                    } else { // black piece
                        if (p instanceof Pawn) { // pawn
                            state[i][j] = 'o';
                        } else { // queen
                            state[i][j] = 'O';
                        }
                    }
                }
            }
        }
        return state;
    }

    public void setFromState(char[][] state) {
        // reset pieces
        this.pieces = new ArrayList<Piece>();
        this.whitePieces = new ArrayList<Piece>();
        this.blackPieces = new ArrayList<Piece>();

        // reset size
        this.size = state.length;

        // place pieces from state
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (state[i][j] == 'x') {
                    this.whitePieces.add(new Pawn(i, j));
                } else if (state[i][j] == 'X') {
                    this.whitePieces.add(new Queen(i, j));
                } else if (state[i][j] == 'o') {
                    this.blackPieces.add(new Pawn(i, j));
                } else if (state[i][j] == 'O') {
                    this.blackPieces.add(new Queen(i, j));
                }
            }
        }

        this.pieces.addAll(this.blackPieces);
        this.pieces.addAll(this.whitePieces);
    }

    private List<Position> filterMoves(List<Position> possibleMoves) {
        List<Position> realisticMoves = new ArrayList<Position>();

        for (Position move : possibleMoves) {
            if (this.getPieceAtPosition(move) == null && this.isInBoard(move)) {
                realisticMoves.add(move);
            }
        }

        return realisticMoves;
    }

    private boolean isInBoard(Position move) {
        return move.getX() >= 0 && move.getX() < this.size
                && move.getY() >= 0 && move.getY() < this.size;
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
