package edu.uqac.algo.draughts.utilities;

import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.players.CPU;
import edu.uqac.algo.draughts.players.Human;
import edu.uqac.algo.draughts.players.Player;

public class Game {
    private Player white;
    private Player black;
    private Board board;
    private boolean debug = false;

    public Game(int boardSize, boolean human) throws BoardTooSmallException {
        this.board = new Board(boardSize);
        if (debug)
            this.board.print();
        // will always be a CPU cuz f* 1v1 it's always 1vCPU or just 2 CPUs playing
        this.black = new CPU(this.board.getBlackPieces(), this.board);
        if (human) {
            this.white = new Human(this.board.getWhitePieces(), this.board);
            System.out.println("You play the white pawns (xs and Xs, just a reminder. I know you're not stupid, but, hey, who knows ?)");
        } else {
            this.white = new CPU(this.board.getWhitePieces(), this.board);
        }
        if (debug)
            System.out.println("--- Game initialized ---");
    }

    public Game() throws BoardTooSmallException {
        this(8, false);
    }

    public void play() {
        if (debug){
            System.out.println("Game start");
        }
        int turn = 1;
        // peace counter, the war ends in a drawn if no one dies for 25 turns, may peace continue forever in this game.
        int turnWithoutDeath = 0;
        while (this.white.canPlay() && this.black.canPlay() && turnWithoutDeath < 25) {
            if (!this.turn(turn)) {
                turnWithoutDeath++;
            }
            turn++;
        }

        if (this.white.canPlay() && this.black.canPlay()) {
            if (debug) {
                System.out.println("Game ended on a draw");
            }
        } else {
            if (!this.white.canPlay() && !this.black.canPlay()) {
                if (debug) {
                    System.out.println("No one can play yet. This developers, urg !");
                }
            } else {
                if (this.white.canPlay()) {
                    if (debug) {
                        System.out.println("White wins");
                    }
                } else {
                    if(debug) {
                        System.out.println("Black wins");
                    }
                }
            }
        }
        if (debug) {
            System.out.println("--- Game ended ---");
        }
    }

    public static void printRules() {
        System.out.println("Black pawns are represented by os and white pawns by xs");
        System.out.println("Queens are represented by their color's letter in uppercase (O and X).");
    }

    /**
     * Play a full turn : white's turn then black's one
     * @param turnNumber not very sure how useful this is yet
     * @return true if a pawn died
     */
    private boolean turn(int turnNumber) {
        boolean death = false;
//        System.out.println("Turn " + turnNumber);
        // lets see what happens during "a turn"
        // 1. white move a piece
//        System.out.println("White turn");
        death = death || this.white.playYourTurn();
        // a. what piece, from where, to where
            // b. can a piece eat an other one, if so that's the only move he can make
                // i. if he ate a piece, can he ate an other one ? if so, he have to keep on eating until he can't anymore
            // c. when he end his turn, we remove all the eaten pieces
            // d. check if the moved piece ended it's turn on a "promoting case" (basically just x = 0 or x = size -1)
        // 2. black move a piece
//        System.out.println("Black turn");
        death = death || this.black.playYourTurn();
            // same as 1.
        return death;
    }
}
