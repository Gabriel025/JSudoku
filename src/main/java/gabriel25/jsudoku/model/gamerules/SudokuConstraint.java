package gabriel25.jsudoku.model.gamerules;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.Set;


public interface SudokuConstraint {
    public boolean validateBoard(SudokuBoard board);
    public Set<SudokuBoard.Cell> findInvalidCells(SudokuBoard board);
    public String getDescription();
}
