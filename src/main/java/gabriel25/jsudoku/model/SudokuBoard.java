package gabriel25.jsudoku.model;


import java.util.Arrays;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets; 

/**
 * A class representing a sudoku game board.
 */
public class SudokuBoard {
    /**
     * Inner class of SudokuBoard that is used for easier manipulation of the board.
     */
    public class Cell {
        private int row, col;
        
        protected Cell(int row, int col) {
            this.row = row - 1;
            this.col = col - 1;
        }

        public int getRow() { return row + 1; }
        public int getColumn() { return col + 1; }

        public int getValue() { return values[row * SIZE + col]; }
        public void setValue(int val) {
            if(val < 1 || val > SIZE)
                throw new IllegalArgumentException(
                    val + " is not a valid digit "
                    + "for board of size " + SIZE + ".");

            values[row * SIZE + col] = val;
        }

        public boolean isPencilMarkSet(int digit) {
            return pencilMarks[row * SIZE + col].contains(digit);
        }

        public IntSet getPencilMarks() {
            return IntSets.unmodifiable(pencilMarks[row * SIZE + col]);
        }

        public int[] getPencilMarkArray() {
            return pencilMarks[row * SIZE + col].intStream().sorted().toArray();
        }

        public void setPencilMark(int val) {
            if(val < 1 || val > SIZE)
                throw new IllegalArgumentException(
                    "Pencil mark value " + val + " is not a valid digit "
                    + "for board of size " + SIZE + ".");

            pencilMarks[row * SIZE + col].add(val);
        }

        public void erasePencilMark(int val) {
            pencilMarks[row * SIZE + col].remove(val);
        }

        public void togglePencilMark(int val) {
            if(isPencilMarkSet(val))
                erasePencilMark(val);
            else
                setPencilMark(val);
        }

        public void overridePencilMarks(int[] vals) {
            clearPencilMarks();
            
            for(int val : vals)
                setPencilMark(val);
        }

        public void clearPencilMarks() {
            SudokuBoard.this.pencilMarks[row * SIZE + col].clear();
        }
    }

    //later on: differentiate between given values (clues) and user values
    //also possibly include the solution as another array (not neccesary though)
    private int values[];
    //later on: make pencil marks less hardcoded and more loosely coupled from the board
    //->separate class for decorators
    private IntOpenHashSet pencilMarks[];
    
    //the 9 won't be hardcoded and grid size will be sourced from SudokuVariants, once implemented
    private final int SIZE = 9;

    public SudokuBoard() {
        //Both values and pencilMarks are row-major flat representations on the board
        values = new int[SIZE * SIZE];
        pencilMarks = new IntOpenHashSet[SIZE * SIZE];

        for(int i = 0; i < SIZE * SIZE; i++)
            pencilMarks[i] = new IntOpenHashSet(SIZE);
    }

    public SudokuBoard(int givenValues[][]) {
        this();

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                values[i * SIZE + j] = givenValues[i][j];
    }

    public SudokuBoard(SudokuBoard board) {
        this();

        for(int i = 0; i < SIZE * SIZE; i++) {
            values[i] = board.values[i];
            pencilMarks[i] = new IntOpenHashSet(board.pencilMarks[i]);
        }
    }

    /** Note: row and column are indexed from 1 */
    public Cell at(int row, int col) {
        if(row < 1 || col < 1 || row > SIZE || col > SIZE)
            throw new IndexOutOfBoundsException(
                "Grid position [" + row + ", " + col
                + "] is out of bounds. (Board size: " + SIZE + "x" + SIZE + ")"
            );
        
        return new Cell(row, col);
    }

    @Override
    public int hashCode() {
        return 61 * SIZE + 73 * Arrays.hashCode(values)
            + 79 * Arrays.hashCode(pencilMarks);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        
        SudokuBoard cast = (SudokuBoard)o;
        
        return SIZE == cast.SIZE && Arrays.equals(values, cast.values)
            && Arrays.deepEquals(pencilMarks, cast.pencilMarks);
    }
}
