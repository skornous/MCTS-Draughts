package edu.uqac.algo.draughts.utilities;

import edu.uqac.algo.draughts.utilities.Board;
import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.utilities.Player;

public class Game {
    private Player white;
    private Player black;
    private Board board;

    public Game(int boardSize, boolean human) throws BoardTooSmallException {
        //todo make the game playable by a human
        this.board = new Board(boardSize);
        this.white = new Player(this.board.getWhitePieces(), this.board);
        this.black = new Player(this.board.getBlackPieces(), this.board);
        this.printRules();
        System.out.println("--- Game initialized ---");
        if (human) {
            System.out.println("You play the white pawns (xs and Xs, just a reminder. I know you're not stupid, but, hey, who knows ?)");
        }
        this.board.print();
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
        System.out.println("Play turn " + turnNumber);
        return false;
    }
}
