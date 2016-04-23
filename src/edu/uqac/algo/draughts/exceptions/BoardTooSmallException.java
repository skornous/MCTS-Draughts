package edu.uqac.algo.draughts.exceptions;

public class BoardTooSmallException extends Exception {
    public BoardTooSmallException() {
        super("Board can't be smaller than 5x5");
    }
}
