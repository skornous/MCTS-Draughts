package edu.uqac.algo.draughts.ai;

import edu.uqac.algo.draughts.utilities.Game;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //todo finish the nodes
    private List<Node> children;
    private Tree tree;
    private Node parent;
    private int visits;
    private int success_score;
    private boolean gameOver;


    public Node(Tree tree, Game game, Node parent) {
        this.parent = parent;
        this.tree = tree;
        this.children = new ArrayList<Node>();
        // increase tree size
        this.tree.setNodeCount(this.tree.getNodeCount() + 1);
        this.gameOver = false;
    }

    public boolean hasChildren() {
        return !this.children.isEmpty();
    }

    public float score(){
        return this.success_score/this.visits;
    }

    public void visit(boolean win){
        this.visits++;
        if(win){
            this.success_score++;
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

}
