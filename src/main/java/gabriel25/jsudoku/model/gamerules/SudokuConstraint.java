package gabriel25.jsudoku.model.gamerules;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.Set;
import it.unimi.dsi.fastutil.ints.IntIntPair;


public interface SudokuConstraint {
    public boolean validateBoard(SudokuBoard board);
    public Set<IntIntPair> findInvalidCells(SudokuBoard board);
    public String getDescription();
}
