package edu.uqac.algo.draughts.utilities;

import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.players.CPU;
import edu.uqac.algo.draughts.players.Human;
import edu.uqac.algo.draughts.players.Player;

public class Game {
    private Player white;
    private Player black;
    private Board board;

    public Game(int boardSize, boolean human) throws BoardTooSmallException {
        //todo make the game playable by a human
        // ^ making it playable at all would be a nice start
        this.board = new Board(boardSize);
        this.board.print();
        // will always be a CPU cuz f* 1v1 it's always 1vCPU or just 2 CPUs playing
        this.black = new CPU(this.board.getBlackPieces(), this.board);
        if (human) {
            this.white = new Human(this.board.getWhitePieces(), this.board);
            System.out.println("You play the white pawns (xs and Xs, just a reminder. I know you're not stupid, but, hey, who knows ?)");
        } else {
            this.white = new CPU(this.board.getWhitePieces(), this.board);
        }
        System.out.println("--- Game initialized ---");
    }

    public void play() {
        System.out.println("Game start");
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
            System.out.println("Game ended on a draw");
        } else {
            if (!this.white.canPlay() && !this.black.canPlay()) {
                System.out.println("No one can play yet. This developers, urg !");
            } else {
                if (this.white.canPlay()) {
                    System.out.println("White wins");
                } else {
                    System.out.println("Black wins");
                }
            }
        }

        System.out.println("--- Game ended ---");
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
        //todo a lot of things, like, play a turn
        System.out.println("Turn " + turnNumber);
        // lets see what happens during "a turn"
        // 1. white move a piece
            // a. what piece, from where, to where
            // b. can a piece eat an other one, if so that's the only move he can make
                // i. if he ate a piece, can he ate an other one ? if so, he have to keep on eating until he can't anymore
            // c. when he end his turn, we remove all the eaten pieces
            // d. check if the moved piece ended it's turn on a "promoting case" (basically just x = 0 or x = size -1)
        // 2. black move a piece
            // same as 1.
        return false;
    }
}
