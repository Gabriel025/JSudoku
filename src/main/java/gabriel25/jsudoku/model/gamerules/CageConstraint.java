package gabriel25.jsudoku.model.gamerules;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.HashSet;


class CageConstraint implements SudokuConstraint {
    public CageConstraint() {}

    public boolean validateBoard(SudokuBoard board) { return false; }
    public HashSet<SudokuBoard.Cell> findInvalidCells(SudokuBoard board) { return new HashSet<SudokuBoard.Cell>(); }
    public String getDescription() { return ""; }
}
