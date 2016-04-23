package edu.uqac.algo.ia;

import edu.uqac.algo.draughts.utilities.Game;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //todo finish the nodes
    private List<Node> children;
    private Tree tree;
    private Node parent;


    public Node(Tree tree, Game game, Node parent) {
        this.parent = parent;
        this.tree = tree;
        this.children = new ArrayList<Node>();
        // increase tree size
        this.tree.setNodeCount(this.tree.getNodeCount() + 1);
    }

    public boolean hasChilden() {
        return !this.children.isEmpty();
    }
}
