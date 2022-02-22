package gabriel25.jsudoku.model.gamerules;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.Set;


public interface SudokuRuleSet {
    public boolean validateBoard(SudokuBoard board);
    public Set<SudokuBoard.Cell> findInvalidCells(SudokuBoard board);

    //other methods follow
}