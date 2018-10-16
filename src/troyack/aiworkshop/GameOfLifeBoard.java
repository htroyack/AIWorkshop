/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import javax.swing.Timer;

/**
 *
 * @author troyack
 */
public class GameOfLifeBoard extends AIWorkshopPanel {

    private final int lines;
    private final int columns;
    private final int top;
    private final int left;
    private final int cellWidth;
    private final int cellHeight;
    private final Color backgroundColor;
    private final Color foregroundColor;
    private boolean[][] cells;
    private final int cellMargin;
    private final Timer timer;
    private final int updateDelay;

    public GameOfLifeBoard() {
        lines = 50;
        columns = lines * 2;
        top = 0;
        left = 0;
        cellHeight = 10;
        cellWidth = cellHeight;
        cellMargin = 2;
        backgroundColor = Color.black;
        foregroundColor = Color.green;
        updateDelay = 100;

        initBoard();

        timer = new Timer(updateDelay, (ActionEvent evt) -> {
            advanceGeneration();
        });
        timer.start();
    }

    private void initBoard() {
        cells = new boolean[lines][columns];

        // glider
        cells[0][1] = true;
        cells[1][2] = true;
        cells[2][0] = true;
        cells[2][1] = true;
        cells[2][2] = true;
        
        // blinker
        cells[1][10] = true;
        cells[1][11] = true;
        cells[1][12] = true;
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

                if (cells[row][col]) {
                    nextGen[row][col] = !(neighbours < 2 || neighbours > 3);
                } else if (neighbours == 3) {
                    nextGen[row][col] = true;
                }
            }
        }
        Log.log("\n");

        cells = nextGen;

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

                if (cells[line][column]) {
                    Log.log("found live cell: line " + line + " column " + column);
                    ++neighbours;
                }
            }
        }

        return neighbours;
    }

    private int getBoardWidth() {
        return cellWidth * columns;
    }

    private int getBoardHeight() {
        return cellHeight * lines;
    }

    @Override
    public void paint(Graphics g) {
        // TODO: Consider scale transform
        /* TODO: Consider recreating the board to match window size uppon
        resizing, preserving the subset of cells that fit and perhaps filling
        new cells with random */
        // String msg = "w: " + getWidth() + " h: " + getHeight();
        // g2.drawString(msg, 20, 20);
        // super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBoardBackground(g2);
        drawBoardGrid(g2);
        drawBoardCells(g2);
    }

    private void drawBoardCells(Graphics2D g2) {
        g2.setColor(foregroundColor);
        Rectangle cell = new Rectangle();
        for (int row = 0; row < lines; row++) {
            for (int col = 0; col < columns; col++) {
                // cells[row][col] = true;
                if (cells[row][col]) {
                    cell.x = left + (col * cellWidth) + cellMargin;
                    cell.y = top + (row * cellHeight) + cellMargin;
                    cell.width = cellWidth - (cellMargin * 2);
                    cell.height = cellHeight - (cellMargin * 2);
                    g2.fill(new Rectangle(cell));
                }
            }
        }
    }

    private void drawBoardGrid(Graphics2D g2) {
        g2.setColor(foregroundColor);
        // Vertical lines
        for (int i = 0; i < columns + 1; i++) {
            g2.draw(new Line2D.Double(i * cellWidth, top, i * cellWidth, top + getBoardHeight()));
        }
        // Horizontal lines
        for (int i = 0; i < lines + 1; i++) {
            g2.draw(new Line2D.Double(left, i * cellHeight, left + getBoardWidth(), i * cellHeight));
        }
    }

    private void drawBoardBackground(Graphics2D g2) {
        g2.setColor(backgroundColor);
        // g.fillRect(left, top, getBoardWidth(), getBoardHeight());
        g2.fillRect(left, top, getWidth(), getHeight());
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
}
