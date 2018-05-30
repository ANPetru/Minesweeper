/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Config {

    private static final String FILE_LEVELS = "levels";
    private static final String FILE_CONFIG = "config";

    int mineSize;
    int numMines;
    int numRowCol;

    private static Config instance;

    private Config() {
        mineSize = 40;
        numMines = 10;
        numRowCol = 10;
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public void save() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(FILE_CONFIG));
            out.write("aaaa");
        } catch (IOException ex) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void setLevel(int level) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(FILE_LEVELS));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(2);

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    System.out.println(3);

                }
            }
        }
    }
}
