package edu.uqac.algo.draughts.players;

import edu.uqac.algo.draughts.ai.MCTS;
import edu.uqac.algo.draughts.ai.Parallels;
import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.utilities.Board;

import java.util.List;

public class CPU extends Player {
    private MCTS mcts;

    public CPU(List<Piece> pieces, Board board) {
        super(pieces, board);
        this.mcts = new MCTS(board);
    }

    @Override
    public boolean playYourTurn() {

        int p = 5;
        for (int i = 0; i < p; i++) {
            new Parallels(mcts).run();
        }

        return false;
    }

    @Override
    public boolean moveAPiece() {

        return false;
    }
}
