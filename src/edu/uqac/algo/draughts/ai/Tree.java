package edu.uqac.algo.draughts.ai;

import edu.uqac.algo.draughts.utilities.Board;
import edu.uqac.algo.draughts.utilities.Game;
import edu.uqac.algo.draughts.utilities.Position;

public class Tree {
    //todo finish the tree
    private Node rootNode;
    private Node currentNode;
    private Board board;
    private Game game;
    private int nodeCount;

    private final int randomMovesPerNode = 30;

    public Tree(Board board) {
        this.board = board;
        this.nodeCount = 0;
        this.rootNode = new Node(this,board.getState(),null);
        this.currentNode = rootNode;
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

    public Board getBoard() { return board; }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

}
