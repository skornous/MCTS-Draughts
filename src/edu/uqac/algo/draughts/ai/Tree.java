package edu.uqac.algo.draughts.ai;

import edu.uqac.algo.draughts.utilities.Game;

public class Tree {
    //todo finish the tree
    private Node rootNode;
    private Node currentNode;
    private Game game;
    private int nodeCount;

    public Tree() {
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }
}
