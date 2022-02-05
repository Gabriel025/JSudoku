package gabriel25.jsudoku;

import gabriel25.jsudoku.views.ConsoleSudokuView;

import javax.swing.*;
import java.awt.FlowLayout;


public class JSudokuApp {
    private JFrame frame;
    private JPanel contentPane;

    public JSudokuApp() {
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setIconImage(new ImageIcon("icon.png").getImage());

        contentPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(new JButton("test"));//content init...
        frame.setContentPane(contentPane);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        //JSudokuApp game = new JSudokuApp();
        //game.show();

        ConsoleSudokuView view;
    }
}