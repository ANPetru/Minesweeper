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
    private int level;

    int mineSize;
    int numMines;
    int numRowCol;

    private static Config instance;

    private Config() {
        mineSize = 40;
        numMines = 10;
        numRowCol = 10;
        level = 0;
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
        this.level = level;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(FILE_LEVELS));
            String line = "";
            int counter = 0;
            while (counter <= level && (line = in.readLine()) != null) {
                counter++;
            }
            String[] levelConfig = line.split(",");
            if (levelConfig.length == 3) {
                mineSize = Integer.parseInt(levelConfig[0]);
                numMines = Integer.parseInt(levelConfig[1]);
                numRowCol = Integer.parseInt(levelConfig[2]);
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

    public int getFontSize() {
        switch (level) {
            case 0:
                return 16;
            case 1:
                return 10;
            case 2:
                return 6;
        }
        return 16;
    }
}
