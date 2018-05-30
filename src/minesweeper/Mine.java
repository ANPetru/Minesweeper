/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Alex
 */
public class Mine extends JButton implements MouseListener {

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
                setForeground(Color.black);

                setText("" + numBombs);
            } else {
                game.openSurrondingMines(row, col);
            }
        } else {
            setForeground(Color.red);
            setText("X");
            game.gameOver();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == 1) {
            if (!game.gameOver) {
                clicked();
            }
        } else if (me.getButton() == 3) {
            if (getBackground() != Color.yellow) {
                setBackground(Color.yellow);
            } else {
                setBackground(Color.white);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
