package edu.uqac.algo.draughts.ai;

import edu.uqac.algo.draughts.pieces.Piece;
import edu.uqac.algo.draughts.utilities.Board;
import edu.uqac.algo.draughts.utilities.Game;
import edu.uqac.algo.draughts.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MCTS {
    private Tree tree;
    //todo implement the MCTS algorithm
    public MCTS(Board board) {
        this.tree = new Tree(board);
    }



    public void playout() {
        char[][] mem_state = this.tree.getBoard().getState();

        //selection
        Node n = tree.getCurrentNode();
        Node l = n.getRandomLeaf();

        //expansion
        Node m = l.addChild();

        //simulation
        tree.getBoard().setFromState(m.getState()); // set state of leaf
        boolean result = this.simulation(tree.getBoard());

        //backPropagation
        m.increase_visit(result);
        m.setState(tree.getBoard().getState());

        tree.getBoard().setFromState(mem_state);
    }

    public boolean simulation(Board board){
        Map<Piece, List<Position>> moves =  board.getPossibleMoves(board.getWhitePieces());

        ArrayList<Piece> keys = new ArrayList<Piece>(moves.keySet());
        Piece randomPiece = keys.get( (int)(Math.random()*keys.size()));
        List<Position> piece_moves = moves.get(randomPiece);
        Position randomMove = piece_moves.get((int)(Math.random()*piece_moves.size()));

        if(board.canMove(randomPiece.getPosition(),randomMove)){
            randomPiece.moveTo(randomMove);
        }
        return false;
    }


    public Tree getTree() {
        return tree;
    }
}
