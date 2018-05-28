/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Alex
 */
public class Mine extends JButton implements ActionListener {

    private int row;
    private int col;
    public boolean isBomb;
    private MainGame game;

    public Mine() {
        super();
    }

    public Mine(int row, int col, MainGame game) {
        super();
        this.row = row;
        this.col = col;
        this.game = game;
        setFont(new Font("Arial", Font.PLAIN, 18));

    }

    public Mine(String m) {
        super(m);
    }

    public void clicked() {
        setEnabled(false);
        if (!isBomb) {
            int numBombs = game.checkSurroundingBombs(row, col);
            if (numBombs > 0) {
                setText("" + numBombs);
            } else {
                game.openSurrondingMines(row, col);
            }
        } else {
            setText("X");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        clicked();
    }

}
