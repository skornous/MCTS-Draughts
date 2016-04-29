package edu.uqac.algo.draughts.ai;

import edu.uqac.algo.draughts.utilities.Game;

public class MCTS {
    private Tree tree;
    //todo implement the MCTS algorithm
    public MCTS(Game game) {
        this.tree = new Tree(game);
    }

    public void selection() {
    }

    public void expansion() {
    }

    public void simulation() {
    }

    public void backPropagation() {
    }

    public void playout() {
        this.selection();
        this.expansion();
        this.simulation();
        this.backPropagation();
    }
}
