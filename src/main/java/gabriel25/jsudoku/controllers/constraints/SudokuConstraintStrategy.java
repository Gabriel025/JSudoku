package gabriel25.jsudoku.controllers.constraints;


public abstract class SudokuConstraintStrategy {

}

 /*
make a separate SudokuConstraintStrategies/SudokuGameTypes/SudokuGameVarieties class with readonly presets

 sudoku constraint strategies that can be implemented:

    -9x9, 16x16, ... grid sizes

    -other game varieties (killer sudoku, knight sudoku, ...)
        -NOTE: these would require sth like a Grid(Line)Layout/GridDrawingStrategy
         (for bg colors, ,cages and cage numbers, irregular boxes)

*/