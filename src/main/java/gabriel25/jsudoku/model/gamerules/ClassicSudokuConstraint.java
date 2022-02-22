package gabriel25.jsudoku.model.gamerules;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.HashSet;


class ClassicSudokuConstraint implements SudokuConstraint {
    public ClassicSudokuConstraint() {}

    public boolean validateBoard(SudokuBoard board) { return false; }
    public HashSet<SudokuBoard.Cell> findInvalidCells(SudokuBoard board) { return new HashSet<SudokuBoard.Cell>(); }
    public String getDescription() { return ""; }
}
