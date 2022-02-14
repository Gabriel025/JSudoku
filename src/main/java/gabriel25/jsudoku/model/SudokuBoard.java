package gabriel25.jsudoku.model;

import java.util.Arrays;
import java.util.stream.IntStream;


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
            int pm[] = SudokuBoard.this.pencilMarks[row][col];

            return Arrays.stream(pm).anyMatch((int i) -> i == digit);
        }

        public int[] getPencilMarks() {
            int pm[] = SudokuBoard.this.pencilMarks[row][col];

            return Arrays.stream(pm).filter((int i) -> i != 0).toArray();
        }

        public void setPencilMark(int val) {
            int pm[] = SudokuBoard.this.pencilMarks[row][col];

            if(pm[0] != 0) return; //all pencil marks are already set
            
            pm[0] = val;
            Arrays.sort(pm);
        }

        public void erasePencilMark(int val) {
            int pm[] = SudokuBoard.this.pencilMarks[row][col];

            pm = Arrays.stream(pm)
                       .map((int i) -> i == val ? 0 : i)
                       .sorted().toArray();
        }

        public void togglePencilMark(int val) {
            if(isPencilMarkSet(val))
                erasePencilMark(val);
            else
                setPencilMark(val);
        }

        public void overridePencilMarks(int[] vals) {
            int pm[] = SudokuBoard.this.pencilMarks[row][col];

            pm = Arrays.stream(vals).distinct().map((int i) -> i <= 9 ? (i <= 1 ? i : 0) : 0).toArray();
            pm = Arrays.copyOf(pm, 9);
            Arrays.sort(pm);
        }

        public void clearPencilMarks() {
            SudokuBoard.this.pencilMarks[row][col] =
                IntStream.generate(() -> 0).limit(9).toArray();
        }
    }

    //later on: differentiate between given values (clues) and user values
    //also possibly include the solution as another array (not neccesary though)
    private int values[][];
    private int pencilMarks[][][];

    public SudokuBoard() {
        //these 9's won't be hardcoded and grid size will be sourced from SudokuVariants, once implemented
        //as a result, to avoid future refactoring, I'm not using field initializers for these arrays
        values = new int[9][9];
        //if I really wish to optimize this, I could use bitmaps; for now this works
        pencilMarks = new int[9][9][9];
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
                for(int k = 0; k < 9; k++)
                    pencilMarks[i][j][k] = board.pencilMarks[i][j][k];
            }
    }

    public Cell at(int row, int col) {
        return new Cell(row, col);
    }

    @Override
    public int hashCode() {
        return 73 * Arrays.deepHashCode(values) + 79 * Arrays.deepHashCode(pencilMarks);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        
        SudokuBoard cast = (SudokuBoard)o;
        
        return Arrays.deepEquals(values, cast.values)
            && Arrays.deepEquals(pencilMarks, cast.pencilMarks);
    }
}
