/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class MainGame extends javax.swing.JFrame {

    private Mine[][] mines;
    private int numBombs;
    public boolean gameOver;
    private Config config;
    private LevelSelection ls;

    /**
     * Creates new form MainGame
     */
    public MainGame() {
        initComponents();
        config = Config.getInstance();
        ls = new LevelSelection(this);
        add(ls);
        ls.setVisible(true);
        pack();

    }

    public void startGame() {
        remove(ls);
        initMines();
        gameOver = false;
    }

    private void initMines() {
        mines = new Mine[config.numRowCol][config.numRowCol];
        for (int row = 0; row < mines.length; row++) {
            for (int col = 0; col < mines.length; col++) {

                mines[row][col] = new Mine(row, col, this);
                mines[row][col].setPreferredSize(new Dimension(config.mineSize, config.mineSize));
                mines[row][col].addMouseListener(mines[row][col]);

                this.getContentPane().add(mines[row][col]);
            }

        }

        setMinimumSize(new Dimension(getBoardDimension(), getBoardDimension()));

        setVisible(true);
        putBombs(config.numMines);
    }

    private int getBoardDimension() {
        if (config.numMines == 10) {
            System.out.println("aaa");
            return config.mineSize * config.numRowCol + 100;
        } else if (config.numMines == 40) {
            System.out.println("bbb");
            return config.mineSize * config.numRowCol + 140;

        }
        System.out.println("ccc");
        return config.mineSize * config.numRowCol + 100;

    }

    public void openSurrondingMines(int row, int col) {

        if (row - 1 >= 0) {
            if (mines[row - 1][col].isEnabled()) {
                mines[row - 1][col].clicked();
            }
            if (col - 1 >= 0 && mines[row - 1][col - 1].isEnabled()) {
                mines[row - 1][col - 1].clicked();
            }
            if (col + 1 < mines.length && mines[row - 1][col + 1].isEnabled()) {
                mines[row - 1][col + 1].clicked();
            }
        }

        if (col - 1 >= 0 && mines[row][col - 1].isEnabled()) {
            mines[row][col - 1].clicked();
        }
        if (col + 1 < mines.length && mines[row][col + 1].isEnabled()) {
            mines[row][col + 1].clicked();
        }
        if (row + 1 < mines.length) {
            if (col - 1 >= 0 && mines[row + 1][col - 1].isEnabled()) {
                mines[row + 1][col - 1].clicked();
            }
            if (mines[row + 1][col].isEnabled()) {
                mines[row + 1][col].clicked();
            }
            if (col + 1 < mines.length && mines[row + 1][col + 1].isEnabled()) {
                mines[row + 1][col + 1].clicked();
            }
        }
    }

    private void putBombs(int n) {
        numBombs = n;
        int generatedBombs = 0;
        while (generatedBombs < n) {
            int randomRow = (int) (Math.random() * config.numRowCol);
            int randomCol = (int) (Math.random() * config.numRowCol);
            if ((generatedBombs < numBombs) && !(mines[randomRow][randomCol].isBomb)) {
                generatedBombs++;
                mines[randomRow][randomCol].isBomb = true;
            }
        }
    }

    public int checkSurroundingBombs(int row, int col) {
        int surrBombs = 0;
        if (row - 1 >= 0) {
            if (mines[row - 1][col].isBomb) {
                surrBombs++;
            }
            if (col - 1 >= 0 && mines[row - 1][col - 1].isBomb) {
                surrBombs++;
            }
            if (col + 1 < mines.length && mines[row - 1][col + 1].isBomb) {
                surrBombs++;
            }
        }

        if (col - 1 >= 0 && mines[row][col - 1].isBomb) {
            surrBombs++;
        }
        if (col + 1 < mines.length && mines[row][col + 1].isBomb) {
            surrBombs++;
        }
        if (row + 1 < mines.length) {
            if (col - 1 >= 0 && mines[row + 1][col - 1].isBomb) {
                surrBombs++;
            }
            if (mines[row + 1][col].isBomb) {
                surrBombs++;
            }
            if (col + 1 < mines.length && mines[row + 1][col + 1].isBomb) {
                surrBombs++;
            }
        }
        return surrBombs;

    }

    public void gameOver() {
        if (!gameOver) {
            gameOver = true;
            showBombs(false);
        }
    }

    private void showBombs(boolean won) {
        for (int row = 0; row < mines.length; row++) {
            for (int col = 0; col < mines.length; col++) {
                if (mines[row][col].isBomb) {
                    mines[row][col].clicked();
                    if(won){
                        System.out.println("aaa");
                        mines[row][col].setForeground(Color.yellow);
                    }
                }
            }
        }
    }

    public void checkWin() {
        boolean win = true;
        for (int row = 0; row < mines.length; row++) {
            for (int col = 0; col < mines.length; col++) {
                if (mines[row][col].isBomb) {
                    if (mines[row][col].getBackground()!= Color.yellow) {
                        win = false;
                    }
                }
            }
        }
        if(win){
            showBombs(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
