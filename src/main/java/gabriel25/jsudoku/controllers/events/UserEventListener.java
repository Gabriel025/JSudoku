package gabriel25.jsudoku.controllers.events;

import java.util.EventListener;
import java.util.EventObject;

public interface UserEventListener extends EventListener {
    public void eventOccured(UserEvent e);
}
