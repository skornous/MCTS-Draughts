package edu.uqac.algo;

import edu.uqac.algo.draughts.exceptions.BoardTooSmallException;
import edu.uqac.algo.draughts.utilities.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Game.printRules();
            System.out.print("Enter board size: ");
            int boardSize = Integer.parseInt(br.readLine());
            System.out.println("Is a human playing ? y/n");
            boolean humanPlayer = br.readLine().toLowerCase().contains("y");
            new Game(boardSize, humanPlayer).play(); // launch game
        } catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (BoardTooSmallException e) {
            e.printStackTrace();
        }
    }
}
