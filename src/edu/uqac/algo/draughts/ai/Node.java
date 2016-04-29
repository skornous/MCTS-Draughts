package edu.uqac.algo.draughts.ai;

import edu.uqac.algo.draughts.utilities.Board;
import edu.uqac.algo.draughts.utilities.Game;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //todo finish the nodes
    private List<Node> children;
    private Node parent;
    private Tree tree;
    private int visits;
    private int success_score;
    private boolean gameOver;
    private char[][] state;


    public Node(Tree tree, char[][] state, Node parent) {
        this.tree = tree;
        this.parent = parent;
        this.state = state;
        this.children = new ArrayList<Node>();
        // increase tree size
        tree.setNodeCount(tree.getNodeCount() + 1);
        this.gameOver = false;
    }

    public boolean hasChildren() {
        return !this.children.isEmpty();
    }

    public float score(){
        return this.success_score/this.visits;
    }

    public void increase_visit(boolean win){
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

    public void setState(char[][] state){
        this.state = state;
    }

    public char[][] getState(){
        return this.state;
    }

    public Node getRandomLeaf(){
        if(!this.hasChildren()){
            return this;
        }else{
            return this.children.get((int)(Math.random()*this.children.size())).getRandomLeaf();
        }
    }

    public Node addChild(){
        Node n = new Node(this.tree,this.state,this);
        return n;
    }
}
