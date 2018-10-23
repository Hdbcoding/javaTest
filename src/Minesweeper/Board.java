package Minesweeper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.function.IntBinaryOperator;

public class Board {
    private Cell[][] board;
    private int rows;
    private int columns;
    private int mines;
    private int spacesLeft;

    private final int[][] neighborDeltas =
    {
        {-1,-1}, {-1,0}, {-1,1},
        {0,-1},          {0,1},
        {1,-1},  {1,0},  {1,1}
    };

    public Board(int r, int c, int m){
        board = new Cell[r][c];
        rows = r;
        columns = c;
        mines = m;
        spacesLeft = r * c - m;
        initialize();
    }

    public boolean reveal(int row, int column){
        var cell = board[row][column];
        cell.reveal();
        if (cell.isMine()) {
            revealAllMines();
            return false;
        }
        if (!cell.hasNeighborMines())
            revealRegion(row, column);
        else spacesLeft--;

        return true;
    }

    public boolean allSpacesRevealed(){ return spacesLeft <= 0; }

    public void draw(){
        System.out.println("  0123456789");
        System.out.println();

        for (int i = 0; i < rows; i++){
            System.out.print(i + " ");
            for (int j = 0; j < columns; j++){
                var cell = board[i][j];
                var result = "?";
                if (cell.isRevealed()){
                    if (cell.isMine()) result = "X";
                    else if (cell.hasNeighborMines()) result = cell.getNeighborMines() + "";
                    else result = " ";
                }
                System.out.print(result);
            }
            System.out.println();
        }
    }

    private void initialize(){
        setupCells();
        shuffleMines();
        setNumbers();
    }

    private void setupCells() {
        int minesToAdd = mines;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                board[i][j] = new Cell(minesToAdd > 0);
                minesToAdd--;
            }
        }
    }

    private void shuffleMines() {
        var random = new Random();
        var cells = rows * columns;
        for (int i = 0; i < cells; i++){
            int j = i + random.nextInt(cells - i);
            if (j != i){
                Cell cell1 = getNthCell(i);
                Cell cell2 = getNthCell(j);

                boolean cell2Mine = cell2.isMine();
                cell2.setMine(cell1.isMine());
                cell1.setMine(cell2Mine);
            }
        }
    }

    private void setNumbers() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (board[i][j].isMine()){
                    for (var delta : neighborDeltas){
                        var r = i + delta[0];
                        var c = j + delta[1];
                        if (isInBounds(r,c)){
                            board[r][c].addNeighbor();
                        }
                    }
                }
            }
        }
    }

    private void revealRegion(int row, int col){
        Queue<int[]> coords = new LinkedList<>();
        coords.add(new int[]{row, col});
        while (!coords.isEmpty()){
            int[] coord = coords.remove();
            int r = coord[0], c = coord[1];
            Cell cell = board[r][c];

            if (!cell.isRevealed()){
                cell.reveal();
                spacesLeft--;
            }

            if (!cell.hasNeighborMines()){
                for (var delta : neighborDeltas){
                    var rd = r + delta[0];
                    var cd = c + delta[1];
                    if (isInBounds(rd,cd)){
                        var neighbor = board[rd][cd];
                        if (!neighbor.isRevealed())
                            coords.add(new int[]{rd,cd});
                    }
                }
            }
        }
    }

    private void revealAllMines() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                var cell = board[i][j];
                if (cell.isMine()) {
                    cell.reveal();
                }
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    private Cell getNthCell(int n){
        int row = n / rows;
        int col = (n - row * columns) % columns;
        return board[row][col];
    }
}
