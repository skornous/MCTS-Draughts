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
    public void playYourTurn() {
        int choice;
        boolean madeAMove = false;
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
                        this.moveAPiece();
                        break;
                    default:
                        // if you can, you HAVE to move during every single turn
                        if(!madeAMove) {
                            this.moveAPiece();
                        }
                }
            } while(choice != 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveAPiece(){
        int x, y;
        Piece p = null;
        Position newPosition;
        //todo check if an other piece can eat something, if so, whatever its move is, it can't be granted
        try {
            do {
                System.out.println("Enter your pieces coordinates :");
                System.out.print("it's row : ");
                x = Integer.parseInt(br.readLine());
                System.out.print("it's column : ");
                y = Integer.parseInt(br.readLine());

                p = this.board.getPieceAtPosition(x-1, y-1);

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

                if (p.canGoto(newPosition)) {
                    //todo add a lot of other verifications, cuz it ain't that simple
                } else {
                    System.out.println("Your piece can't go there ! You're going to have to re-enter it's new location, happy now ?"
                            + " I don't care if you lose time, I'm a program, I've got all the time in the world to make your life crappy");
                }
            } while (!p.canGoto(new Position(x-1, y-1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
