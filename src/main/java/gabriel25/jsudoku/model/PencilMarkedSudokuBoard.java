package gabriel25.jsudoku.model;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;


public class PencilMarkedSudokuBoard extends SudokuBoard {
    /**
     * Inner class of SudokuBoard that is used for easier manipulation of the board.
     */
    public class Cell extends SudokuBoard.Cell {
        protected Cell(int row, int col) {
            super(row, col);
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
            pencilMarks[row * SIZE + col].clear();
        }
    }

    private IntOpenHashSet pencilMarks[];

    public PencilMarkedSudokuBoard() {
        super();

        pencilMarks = new IntOpenHashSet[SIZE * SIZE];

        for(int i = 0; i < SIZE * SIZE; i++)
            pencilMarks[i] = new IntOpenHashSet(SIZE);
    }

    public PencilMarkedSudokuBoard(int givenValues[][]) {
        super(givenValues);
    }

    public PencilMarkedSudokuBoard(SudokuBoard board) {
        super(board);
        
        if(board.getClass() == PencilMarkedSudokuBoard.class) {
            PencilMarkedSudokuBoard cast = (PencilMarkedSudokuBoard) board;

            for(int i = 0; i < SIZE * SIZE; i++)
                pencilMarks[i] = new IntOpenHashSet(cast.pencilMarks[i]);
        }
    }

    public int getSize() { return SIZE; }

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
        return super.hashCode(); //61 * SIZE + 73 * Arrays.hashCode(values) + 79 * Arrays.hashCode(pencilMarks);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        
        SudokuBoard cast = (SudokuBoard)o;
        
        return super.equals(cast); //&& Arrays.deepEquals(pencilMarks, cast.pencilMarks);
    }
}
