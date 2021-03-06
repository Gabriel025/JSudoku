package gabriel25.jsudoku.controllers.events;

import gabriel25.jsudoku.model.SudokuBoard;


public class NewBoardEvent extends UserEvent {
    SudokuBoard board;
    
    public NewBoardEvent(Object source, SudokuBoard newBoard) {
        super(source);

        board = new SudokuBoard(newBoard);
    }

    public NewBoardEvent(NewBoardEvent e) {
        this(e.source, e.board);
    }
    
}
