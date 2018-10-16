/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author troyack
 */
public class GameOfLife extends AIWorkshopPanel {

    private final int lines;
    private final int columns;
    private final Color bgColor;
    private final Color fgColor;
    private final int cellWidth;
    private final int cellHeight;
    private final int cellGap;
    private final int updateDelay;
    private Cell[][] cells;
    private final Timer timer;

    public GameOfLife() {
        lines = 50;
        columns = lines * 2;
        bgColor = Color.black;
        fgColor = Color.green;
        cellHeight = 10;
        cellWidth = cellHeight;
        cellGap = 1;
        updateDelay = 100;

        initBoard();
        
        timer = new Timer(updateDelay, (ActionEvent evt) -> {
            advanceGeneration();
        });
        timer.start();
    }

    private void initBoard() {
        setBackground(bgColor);
        JPanel board = new JPanel(new GridLayout(lines, columns, cellGap, cellGap));
        board.setBackground(fgColor);
        board.setBorder(new EmptyBorder(cellGap, cellGap, cellGap, cellGap));
        // board.setBackground(Color.red);
        Dimension cellSize = new Dimension(cellWidth, cellHeight);
        cells = new Cell[lines][columns];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setPreferredSize(cellSize);
                cells[i][j].setForeground(fgColor);
                // cells[i][j].setBackground(Color.blue);
                cells[i][j].setBackground(bgColor);
                cells[i][j].setGap(cellGap);
                board.add(cells[i][j]);
            }
        }
        
        populate();
        
        add(board);
    }

    private void populate() {
        // glider
        cells[0][1].setAlive(true);
        cells[1][2].setAlive(true);
        cells[2][0].setAlive(true);
        cells[2][1].setAlive(true);
        cells[2][2].setAlive(true);
        
        // blinker
        cells[1][10].setAlive(true);
        cells[1][11].setAlive(true);
        cells[1][12].setAlive(true);
    }

    private void advanceGeneration() {
        boolean[][] nextGen = new boolean[lines][columns];

        /*
        Any live cell with fewer than two live neighbors dies, as if by underpopulation.
        Any live cell with two or three live neighbors lives on to the next generation.
        Any live cell with more than three live neighbors dies, as if by overpopulation.
        Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
         */
        for (int row = 0; row < lines; row++) {
            for (int col = 0; col < columns; col++) {
                int neighbours = countNeighbours(row, col);

                Log.log("neighbours {" + row + "," + col + "}: " + neighbours);

                if (cells[row][col].isAlive()) {
                    nextGen[row][col] = !(neighbours < 2 || neighbours > 3);
                } else if (neighbours == 3) {
                    nextGen[row][col] = true;
                }
            }
        }
        Log.log("\n");

        for (int row = 0; row < lines; row++) {
            for (int col = 0; col < columns; col++) {
                cells[row][col].setAlive(nextGen[row][col]);
            }
        }

        repaint();
    }

    private int countNeighbours(int row, int col) {
        int neighbours = 0;

        for (int line = row - 1; line <= row + 1; line++) {
            // respect board bounds
            if (line < 0 || line >= lines) {
                continue;
            }
            for (int column = col - 1; column <= col + 1; column++) {
                // respect board bounds
                if (column < 0 || column >= columns) {
                    continue;
                }

                // skip self
                if (line == row && column == col) {
                    continue;
                }

                if (cells[line][column].isAlive()) {
                    Log.log("found live cell: line " + line + " column " + column);
                    ++neighbours;
                }
            }
        }

        return neighbours;
    }

    @Override
    public String getTitle() {
        return "Life";
    }

    @Override
    public String getDescription() {
        return "John Horton Conway's Game of Life";
    }

    @Override
    public String getIconName() {
        return "glider.gif";
    }

    private class Cell extends JPanel {

        private int gap;
        private boolean alive;

        public Cell() {
            gap = 0;
            alive = false;
            connectMouse();
        }

        public void setGap(int cellGap) {
            gap = cellGap;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (isAlive()) {
                g.fillRect(gap, gap, getWidth() - (gap * 2),
                        getHeight() - (gap * 2));
            }
        }

        public boolean isAlive() {
            return alive;
        }

        public void setAlive(boolean isAlive) {
            alive = isAlive;
            repaint();
        }

        private void connectMouse() {
            MouseListener listener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setAlive(!isAlive());
                }
            };
            this.addMouseListener(listener);
        }

    }

}
