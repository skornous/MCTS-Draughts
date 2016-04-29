package edu.uqac.algo.draughts.players;

import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.utilities.Board;
import edu.uqac.algo.draughts.utilities.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Human extends Player {
    private BufferedReader br;
    public Human(List<Piece> pieces, Board board) {
        super(pieces, board);
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public boolean playYourTurn() {
        int choice;
        boolean madeAMove = false;
        boolean killed = false;
        try {
            do {
                System.out.println("So, what's it gonna be ?"
                        + "\n1. print board"
                        + "\n2. Move a piece"
                        + "\n0. End");
                choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1:
                        this.board.print();
                        break;
                    case 2:
                        if (!madeAMove) {
                            killed = this.moveAPiece();
                            madeAMove = true;
                        } else {
                            System.out.printf("You already moved a piece");
                        }
                        break;
                    default:
                        System.out.printf("You have to move a piece before ending your turn");
                        // if you can, you HAVE to move during every single turn
                        /*if(!madeAMove) {
                            this.moveAPiece();
                        }*/
                }
            } while(choice != 0 && !madeAMove);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return killed;
    }

    @Override
    public boolean moveAPiece(){
        int x, y;
        Piece p = null;
        Position newPosition;
        boolean moved = false;
        boolean killed = false;
        //check if an other piece can eat something, if so, whatever its move is, it can't be granted
        try {
            do {
                System.out.println("Enter your pieces coordinates :");
                System.out.print("it's row : ");
                x = Integer.parseInt(br.readLine());
                System.out.print("it's column : ");
                y = Integer.parseInt(br.readLine());

                p = this.getPieceAtPosition(x-1, y-1);

                if (p == null) {
                    System.out.println("There is no pieces here... Are you drunk ? Because if you are this is going to be a loooong game");
                }
            } while (p == null);

            do {
                System.out.println("Enter it's new coordinates :");
                System.out.print("it's new row : ");
                x = Integer.parseInt(br.readLine());
                System.out.print("it's new column : ");
                y = Integer.parseInt(br.readLine());

                newPosition = new Position(x-1, y-1);

                if (Math.abs(p.getX() - newPosition.getX()) == 1 && Math.abs(p.getY() - newPosition.getY()) == 1) {
                    // move to a distance of 1 = move
                    if (this.board.canMove(p.getPosition(), newPosition)) {
                        p.moveTo(newPosition);
                        moved = true;
                    } else {
                        System.out.println("Your piece can't go there ! You're going to have to re-enter it's new location, happy now ?"
                                + " I don't care if you lose time, I'm a program, I've got all the time in the world to make your life crappy");
                        return this.moveAPiece();
                    }
                } else if (Math.abs(p.getX() - newPosition.getX()) == 2 && Math.abs(p.getY() - newPosition.getY()) == 2) {
                    // move to a distance of 2 = eat a piece
                    Piece pieceToEat;
                    Position checkPos;
                    // get direction
                    if (p.getX() < newPosition.getX() && p.getY() < newPosition.getY()) {
                        // NE
                        pieceToEat = this.board.getPieceAtPosition(newPosition.getX() - 1, newPosition.getY() - 1);
                        checkPos = new Position(newPosition.getX() - 1, newPosition.getY() - 1);
                    } else if (p.getX() < newPosition.getX() && p.getY() > newPosition.getY()) {
                        // NW
                        pieceToEat = this.board.getPieceAtPosition(newPosition.getX() - 1, newPosition.getY() + 1);
                        checkPos = new Position(newPosition.getX() - 1, newPosition.getY() + 1);
                    } else if (p.getX() > newPosition.getX() && p.getY() < newPosition.getY()) {
                        // SE
                        pieceToEat = this.board.getPieceAtPosition(newPosition.getX() + 1, newPosition.getY() - 1);
                        checkPos = new Position(newPosition.getX() + 1, newPosition.getY() - 1);
                    } else {
                        // SW
                        pieceToEat = this.board.getPieceAtPosition(newPosition.getX() + 1, newPosition.getY() + 1);
                        checkPos = new Position(newPosition.getX() + 1, newPosition.getY() + 1);
                    }


                    if (pieceToEat != null) {
                        // eat
                        if (this.board.eatPiece(p, pieceToEat)) {
                            killed = true;
                            p.moveTo(newPosition);
                            moved = true;
                        } else {
                            System.out.println("You can't move here : no one to eat or you can't eat this piece");
                        }
                    } else {
                        System.out.println("No piece to eat");
                        return this.moveAPiece();
                    }
                } else {
                    System.out.println("You can't do that");
                    return this.moveAPiece();
                }
            } while (!moved);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return killed;
    }
}
