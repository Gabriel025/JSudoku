package gabriel25.jsudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;


public class SudokuBoard {
    /**
     * Inner class that is used for easier manipulation of the board.
    */
    public class Cell {
        private int row, col;
        
        protected Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getValue() { return SudokuBoard.this.values[row][col]; }
        public void setValue(int val) { SudokuBoard.this.values[row][col] = val; }

        public boolean isPencilMarkSet(int digit) {
            HashSet<Integer> pm = SudokuBoard.this.pencilMarks[row][col];

            return pm.contains(digit);
        }

        public int[] getPencilMarks() {
            HashSet<Integer> pm = SudokuBoard.this.pencilMarks[row][col];

            return new int[]{}; //need to sort and unbox pm.toArray() (or change method signature)
        }

        public void setPencilMark(int val) {
            HashSet<Integer> pm = SudokuBoard.this.pencilMarks[row][col];

            pm.add(val);
        }

        public void erasePencilMark(int val) {
            HashSet<Integer> pm = SudokuBoard.this.pencilMarks[row][col];

            pm.remove(val);
        }

        public void togglePencilMark(int val) {
            if(isPencilMarkSet(val))
                erasePencilMark(val);
            else
                setPencilMark(val);
        }

        public void overridePencilMarks(int[] vals) {
            HashSet<Integer> pm = SudokuBoard.this.pencilMarks[row][col];

            pm.clear();
            pm.addAll(Arrays.stream(vals).boxed().collect(Collectors.toList()));
        }

        public void clearPencilMarks() {
            HashSet<Integer> pm = SudokuBoard.this.pencilMarks[row][col];

            pm.clear();
        }
    }

    //later on: differentiate between given values (clues) and user values
    //also possibly include the solution as another array (not neccesary though)
    private int values[][];
    private HashSet<Integer> pencilMarks[][];

    public SudokuBoard() {
        //these 9's won't be hardcoded and grid size will be sourced from SudokuVariants, once implemented
        //as a result, to avoid future refactoring, I'm not using field initializers for these arrays
        values = new int[9][9];
        //I don't like the need for boxing when using containers
        //if I really wish to optimize this, I could use bitfields, but for, now this works 
        //note: produces a warning (HashSet and HashSet<T> are different types)
        //that's okay for now, will probably replace arrays with lists anyway
        pencilMarks = new HashSet[9][9];

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                pencilMarks[i][j] = new HashSet<Integer>(9);
    }

    public SudokuBoard(int givenValues[][]) {
        this();

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                values[i][j] = givenValues[i][j];
    }

    public SudokuBoard(SudokuBoard board) {
        this();

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
                values[i][j] = board.values[i][j];
                pencilMarks[i][j] = new HashSet<Integer>(board.pencilMarks[i][j]);
            }
    }

    /** Note: row and column are indexed from 1 */
    public Cell at(int row, int col) {
        return new Cell(row, col);
    }

    @Override
    public int hashCode() {
        return 73 * Arrays.deepHashCode(values) + 79 * pencilMarks.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        
        SudokuBoard cast = (SudokuBoard)o;
        
        return Arrays.deepEquals(values, cast.values)
            && pencilMarks.equals(cast.pencilMarks);
    }
}
