package gabriel25.jsudoku.model.gamerules;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.HashSet;
import it.unimi.dsi.fastutil.ints.IntIntPair;


class CageConstraint implements SudokuConstraint {
    public CageConstraint() {}

    public boolean validateBoard(SudokuBoard board) { return false; }
    public HashSet<IntIntPair> findInvalidCells(SudokuBoard board) { return new HashSet<IntIntPair>(); }
    public String getDescription() { return ""; }
}
