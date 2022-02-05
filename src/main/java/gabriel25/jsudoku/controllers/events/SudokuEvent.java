package gabriel25.jsudoku.controllers.events;

import gabriel25.jsudoku.model.SudokuBoard;

import java.time.LocalDateTime;
import java.util.EventObject;



public abstract class SudokuEvent extends EventObject {
    private LocalDateTime timestamp;
    
    protected SudokuEvent(Object source) {
        this(source, LocalDateTime.now());
    }

    protected SudokuEvent(Object source, LocalDateTime timestamp) {
        super(source);
        this.timestamp = timestamp;
    }

    protected SudokuEvent(SudokuEvent e) {
        this(e.source, e.timestamp);
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public SudokuEvent setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
