package gabriel25.jsudoku.controllers.events;

import java.time.LocalDateTime;
import java.util.EventObject;


public abstract class UserEvent extends EventObject {
    private LocalDateTime timestamp;
    
    protected UserEvent(Object source) {
        this(source, LocalDateTime.now());
    }

    protected UserEvent(Object source, LocalDateTime timestamp) {
        super(source);
        this.timestamp = timestamp;
    }

    protected UserEvent(UserEvent e) {
        this(e.source, e.timestamp);
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
