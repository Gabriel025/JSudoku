package gabriel25.jsudoku.controllers.events;

import java.util.EventListener;


public interface UserEventListener extends EventListener {
    public void eventOccured(UserEvent e);
}
