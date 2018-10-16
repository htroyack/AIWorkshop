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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author troyack
 */
public class Queens extends AIWorkshopPanel implements TreeSelectionListener {

    private final int boardSize;
    private final int gap;
    private final Color white;
    private final Color black;
    private final int squareSize;
    private final Image[][] chessPieceImages = new Image[2][6];
    public static final int BLACK = 0, WHITE = 1;
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    private JTree tree;
    private ChessSquare[][] square;

    public Queens() {
        boardSize = 8;
        gap = 2;
        white = Color.white;
        black = Color.black;
        squareSize = 64;

        initDisplay();
    }

    private void initDisplay() {
        setBackground(Color.darkGray);
        loadImages();
        createBoard();
        createTree();
    }

    private void createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
        populateTree(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);
        JScrollPane treeView = new JScrollPane(tree);
        add(treeView);
    }

    private void createBoard() {
        square = new ChessSquare[boardSize][boardSize];
        JPanel board = new JPanel(new GridLayout(boardSize, boardSize, gap, gap));
        board.setBackground(new Color(150, 75, 0));
        board.setBorder(new EmptyBorder(gap, gap, gap, gap));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                square[i][j] = new ChessSquare();
                if (i % 2 == j % 2) {
                    square[i][j].setBackground(white);
                    square[i][j].setPiece(chessPieceImages[BLACK][QUEEN]);
                } else {
                    square[i][j].setBackground(black);
                    square[i][j].setPiece(chessPieceImages[WHITE][QUEEN]);
                }
                square[i][j].setPreferredSize(new Dimension(squareSize, squareSize));
                board.add(square[i][j]);
            }
        }
        add(board);
    }

    private void populateTree(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode subItem = new DefaultMutableTreeNode("Books for Java Programmers");
        DefaultMutableTreeNode subDir = new DefaultMutableTreeNode("SubDir");
        subDir.add(new DefaultMutableTreeNode("Leaf"));
        top.add(subDir);
        top.add(subItem);
    }

    private void loadImages() {
        BufferedImage image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("chess_pieces.png"));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    chessPieceImages[i][j] = image.getSubimage(j * 64, i * 64, 64, 64);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Queens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Queens";
    }

    @Override
    public String getDescription() {
        return "Eight Queens Puzzle";
    }

    @Override
    public String getIconName() {
        return "glider.gif";
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
        } else {
        }
    }

    private static class ChessSquare extends JPanel {

        private Image pieceImage;

        public ChessSquare() {
            pieceImage = null;
        }

        public void setPiece(Image piece) {
            pieceImage = piece;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (pieceImage != null) {
                g.drawImage(pieceImage, 0, 0, null);
            }
        }

    }

}
