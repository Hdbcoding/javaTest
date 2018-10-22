package Minesweeper;

class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int numNeighborMines = 0;

    Cell(boolean mine) {
        isMine = mine;
    }

    boolean isMine() {
        return isMine;
    }

    void setMine(boolean mine) {
        isMine = mine;
    }

    boolean isRevealed(){
        return isRevealed;
    }

    void reveal() {
        isRevealed = true;
    }

    void addNeighbor(){
        numNeighborMines++;
    }

    int getNeighborMines(){
        return numNeighborMines;
    }

    boolean hasNeighborMines(){
        return numNeighborMines > 0;
    }

    void setFlagged(boolean flag){
        isFlagged = flag;
    }

    boolean isFlagged(){
        return isFlagged;
    }
}
