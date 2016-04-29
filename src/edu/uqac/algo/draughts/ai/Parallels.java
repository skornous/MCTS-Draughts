package edu.uqac.algo.draughts.ai;

public class Parallels extends Thread {
    private MCTS mcts;

    public Parallels(MCTS mcts){
        this.mcts = mcts;
    }

    @Override
    public void run() {
        mcts.playout();
    }
}
