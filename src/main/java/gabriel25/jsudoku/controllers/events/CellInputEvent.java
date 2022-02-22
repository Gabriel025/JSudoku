package gabriel25.jsudoku.controllers.events;


public class CellInputEvent extends UserEvent {
    private int row, col, val;

    public CellInputEvent(Object source, int row, int col, int newVal) {
        super(source);
        this.row = row;
        this.col = col;
    }
}