package gabriel25.jsudoku.model;

import java.util.Arrays;


/**
 * A class representing a sudoku game board.
 */
public class SudokuBoard {
    //later on: differentiate between given values (clues) and user values
    //also possibly include the solution as another array (not neccesary though)
    private int values[];

    //the 9 won't be hardcoded and grid size will be sourced from SudokuVariants, once implemented
    protected final int SIZE = 9;

    public SudokuBoard() {
        //a row-major flat representation of the board
        values = new int[SIZE * SIZE];
    }

    public SudokuBoard(int givenValues[][]) {
        this();

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                values[i * SIZE + j] = givenValues[i][j];
    }

    public SudokuBoard(SudokuBoard board) {
        values = board.values.clone();
    }

    public int getSize() { return SIZE; }

    /** Note: row and column are indexed from 1 */
    public int at(int row, int col) {
        validatePos(row, col);
        
        row--; 
        col--;
        return values[row * SIZE + col];
    }

    public void set(int row, int col, int val) {
        validatePos(row, col);
        validateValue(val);

        row--;
        col--;
        values[row * SIZE + col] = val;
    }

    protected void validatePos(int row, int col) {
        if(row < 1 || col < 1 || row > SIZE || col > SIZE)
            throw new IndexOutOfBoundsException(
                "Grid position [" + row + ", " + col
                + "] is out of bounds. (Board size: " + SIZE + "x" + SIZE + ")"
            );
    }

    protected void validateValue(int val) {
        if(val < 1 || val > SIZE)
            throw new IllegalArgumentException(
                val + " is not a valid digit "
                + "for board of size " + SIZE + "."
            );
    }

    @Override
    public final int hashCode() {
        return 61 * SIZE + 73 * Arrays.hashCode(values);
    }

    @Override
    public final boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof SudokuBoard)) return false;
        
        SudokuBoard cast = (SudokuBoard)o;
        
        return SIZE == cast.SIZE && Arrays.equals(values, cast.values);
    }
}
