package gabriel25.jsudoku.controllers.events;

import gabriel25.jsudoku.model.SudokuBoard;

import java.util.Objects;


public class CellInputEvent extends UserEvent {
    private int row, col, val;

    public CellInputEvent(Object source, int row, int col, int newVal) {
        super(source);
        this.row = row;
        this.col = col;
    }

    /*
    public SudokuBoard.Cell getCell() { return cell; }

    @Override
    public int hashCode() {
        return Objects.hash(source, cell);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;

        CellChangeEvent cast = (CellChangeEvent) o;
        return cell.equals(cast.cell);
    }
    */
}