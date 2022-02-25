package gabriel25.jsudoku.model;


import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;

/*
note: pencil marks and any future decorations (cell coloring and such) are sort of a separate concern
preferably split them into separate classes later
this is just a simple implementation for the time being
*/
public class PencilMarkedSudokuBoard extends SudokuBoard {
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

    /** Note: row and column are indexed from 1 */
    public boolean isPencilMarkSet(int row, int col, int val) {
        validatePos(row, col);
        validateValue(val);
        row--;
        col--;

        return pencilMarks[row * SIZE + col].contains(val);
    }

    /** Note: row and column are indexed from 1 */
    public IntSet getPencilMarks(int row, int col) {
        validatePos(row, col);
        row--;
        col--;

        return IntSets.unmodifiable(pencilMarks[row * SIZE + col]);
    }

    /** Note: row and column are indexed from 1 */
    public int[] getPencilMarkArray(int row, int col) {
        validatePos(row, col);
        row--;
        col--;

        return pencilMarks[row * SIZE + col].intStream().sorted().toArray();
    }

    /** Note: row and column are indexed from 1 */
    public void setPencilMark(int row, int col, int val) {
        validatePos(row, col);
        validateValue(val);
        row--;
        col--;

        pencilMarks[row * SIZE + col].add(val);
    }

    /** Note: row and column are indexed from 1 */
    public void erasePencilMark(int row, int col, int val) {
        validatePos(row, col);
        validateValue(val);
        row--;
        col--;

        pencilMarks[row * SIZE + col].remove(val);
    }

    /** Note: row and column are indexed from 1 */
    public void togglePencilMark(int row, int col, int val) {
        if(isPencilMarkSet(row, col, val))
            erasePencilMark(row, col, val);
        else
            setPencilMark(row, col, val);
    }

    /** Note: row and column are indexed from 1 */
    public void overridePencilMarks(int row, int col, int[] vals) {
        clearPencilMarks(row, col);
        
        for(int val : vals)
            setPencilMark(row, col, val);
    }

    /** Note: row and column are indexed from 1 */
    public void clearPencilMarks(int row, int col) {
        validatePos(row, col);
        row--;
        col--;

        pencilMarks[row * SIZE + col].clear();
    }
}
