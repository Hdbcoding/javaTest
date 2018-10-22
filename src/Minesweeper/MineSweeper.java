package Minesweeper;

import java.util.Scanner;

public class MineSweeper {
    private Board board;
    private int rows;
    private int columns;
    private int mines;
    private GameState state;

    public static void main(String[] args){
        var game = new MineSweeper(10, 10, 10);
        game.startGame();
    }

    public MineSweeper(int r, int c, int m){
        rows = r;
        columns = c;
        mines = m;
    }

    public void startGame(){
        board = new Board(rows, columns, mines);
        state = GameState.Playing;

        gameLoop();
    }

    private void playMove(int row, int column){
        if (state != GameState.Playing) return;

        boolean gameOver = board.reveal(row, column);
        if (!board.reveal(row, column)){
            state = GameState.Lost;
            System.out.println("Ooof, you lost!");
        }
        else if (board.allSpacesRevealed()){
            state = GameState.Won;
            System.out.println("Hey, you won!");
        }
    }

    private void gameLoop(){
        var s = new Scanner(System.in);
        System.out.println("Welcome to minesweeper");
        while (state == GameState.Playing){
            board.draw();
            System.out.println("Choose your row: ");
            int row = s.nextInt();
            System.out.println("Choose your column: ");
            int col = s.nextInt();
            System.out.println("Are you sure? (1 for yes)");
            if (s.nextInt() != 1) continue;
            playMove(row, col);
        }
        board.draw();
        System.out.println("Thanks for playing!");
    }
}
