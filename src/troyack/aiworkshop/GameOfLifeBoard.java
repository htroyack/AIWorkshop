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
import java.awt.geom.Line2D;

/**
 *
 * @author troyack
 */
public class GameOfLifeBoard extends AIWorkshopPanel {
    
    private int lines;
    private int columns;
    private final int top;
    private final int left;
    private final int cellWidth;
    private final int cellHeight;
    private final Color backgroundColor;
    private final Color foregroundColor;
    private boolean[][] cells;
    private final int cellMargin;
    
    public GameOfLifeBoard() {
        lines = 30;
        columns = lines * 2;
        top = 0;
        left = 0;
        cellHeight = 100;
        cellWidth = cellHeight;
        cellMargin = 2;
        backgroundColor = Color.black;
        foregroundColor = Color.green;
        
        initBoard();
    }

    private void initBoard() {
        cells = new boolean[lines][columns];
        cells[1][0] = true;
        cells[1][1] = true;
        cells[1][2] = true;
    }
    
    private int getBoardWidth()
    {
        return cellWidth * columns;
    }
    
    private int getBoardHeight()
    {
        return cellHeight * lines;
    }
    
    @Override
    public void paint(Graphics g) {
        // TODO: Consider scale transform
        /* TODO: Consider recreating the board to match window size uppon
        resizing, preserving the subset of cells that fit and perhaps filling
        new cells with random */
        /* String msg = "w: " + getWidth() + " h: " + getHeight();
        g2.drawString(msg, 20, 20); */
        // super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBoardBackground(g2);
        drawBoardGrid(g2);
        drawBoardCells(g2);
    }

    private void drawBoardCells(Graphics2D g2) {
        g2.setColor(foregroundColor);
        Rectangle cell = new Rectangle();
        for (int row = 0; row < lines; row++){
            for (int col = 0; col < columns; col++) {
                // cells[row][col] = true;
                if (cells[row][col]) {
                    cell.x = left + (col * cellWidth) + cellMargin;
                    cell.y = top + (row * cellHeight) + cellMargin;
                    cell.width = cellWidth - (cellMargin*2);
                    cell.height = cellHeight - (cellMargin*2);
                    g2.fill(new Rectangle(cell));
                }
            }
        }
    }

    private void drawBoardGrid(Graphics2D g2) {
        g2.setColor(foregroundColor);
        // Vertical lines
        for (int i = 0; i < columns+1; i++) {
            g2.draw(new Line2D.Double(i * cellWidth, top, i * cellWidth, top + getBoardHeight()));
        }
        // Horizontal lines
        for (int i = 0; i < lines+1; i++) {
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
