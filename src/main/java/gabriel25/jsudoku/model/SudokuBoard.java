package gabriel25.jsudoku.model;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;


public class SudokuBoard {
    public class Cell {
        private int row, col;
        
        protected Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getValue() { return SudokuBoard.this.grid[row][col]; }
        public void setValue(int val) { this.val = val; }

        public int[] getPencilMarks() { return new int[]{}; }
        public void setPencilMark(int val) { }
        public void clearPencilMark(int val) { }
        public void togglePencilMark(int val) { }
        public void setPencilMark(int[] val) { }
    }

    private ConcurrentLinkedQueue<Listener> listeners;

    private Cell grid[][];

    public SudokuBoard() {
        grid = new Cell[9][9];
        
        listeners = new ConcurrentLinkedQueue<Listener>();
    }

    public synchronized Cell at(int row, int col) {

        return grid[row][col];
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
}


/*

JSudokuGridView (/possibly rename to editor? pane?)
 -has a reference to a SudokuGrid and observes its changes
 -has events for user input (changing values/pencil marks)
  and for grid selection (important later for multiplayer stuff)
 -will have a reference to a list of players/sth similar (for names/colors/...)

SudokuGrid
 -2D array of Cell objects
 -has an event for changed values

SudokuClient
 -has a reference to a SudokuGrid object that represents a local copy of the server's state
 -connects to a remote server and synchronizes initial state
 -has sth like get<...>Listener() that returns a listener that can be bound to gridview's events
 -when its listeners are bound, it sends change events and such to the server
 -when it receives changes from the server, it changes the localSudokuGrid object
   -changes made by the user have to be propagated back by the server in order to take effect (quasi-ACK mechanism)

SudokuServer
 -owns a copy of SudokuGrid that is to be considered the authoritative state of the current game
 -listens for incoming connections and handles them
 -listens for changes posted by active clients, updates SudokuGrid, and broadcasts changes back to clients
 -...

Player
 -name
 -color
 -...
*/