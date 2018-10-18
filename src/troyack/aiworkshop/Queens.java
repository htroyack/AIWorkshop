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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
public class Queens extends AIWorkshopPanel implements TreeSelectionListener,
        ActionListener, Problem {

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
        JButton solveButton = new JButton("Solve");
        solveButton.setActionCommand("solve");
        solveButton.addActionListener(this);
        add(solveButton);
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
                square[i][j] = new ChessSquare(i, j, this);
                if (i % 2 == j % 2) {
                    square[i][j].setBackground(white);
                    square[i][j].setPiece(BLACK, QUEEN);
                } else {
                    square[i][j].setBackground(black);
                    square[i][j].setPiece(WHITE, QUEEN);
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

    private int lookupTargets(int i, int j, boolean highlight) {
        int foundTargets = 0;
        
        for (int column = 0; column < boardSize; column++) {
            if (column == j) {
                continue;
            }
            foundTargets += square[i][column].setHighlight(highlight);
        }
        for (int line = 0; line < boardSize; line++) {
            if (line == i) {
                continue;
            }
            foundTargets += square[line][j].setHighlight(highlight);
        }

        int min = (i < j) ? i : j;
        for (int line = i - min, column = j - min; line < boardSize && column < boardSize; line++, column++) {
            if (line == i && column == j) {
                continue;
            }
            foundTargets += square[line][column].setHighlight(highlight);
        }

        min = ((boardSize - 1 - i) < j) ? (boardSize - 1 - i) : j;
        for (int column = j - min, line = i + min; column < boardSize && line >= 0; column++, line--) {
            if (line == i && column == j) {
                continue;
            }
            foundTargets += square[line][column].setHighlight(highlight);
        }
        
        return foundTargets;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("solve".equals(e.getActionCommand())) {
            addSolutionTree(new BreadthFirstSearch().solve(this));
        }
    }

    private void addSolutionTree(SolutionTreeNode solve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkSolution(ProblemState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ProblemState> sucessorFunction(ProblemState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProblemState getInitialState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Image getPieceImage(int color, int piece) {
        return chessPieceImages[color][piece];
    }

    private static class ChessSquare extends JPanel implements MouseListener {

        private Image pieceImage;
        private boolean highlight;
        private final Color highlightColor;
        private final int i;
        private final int j;
        private final Queens board;
        private Integer piece;

        public ChessSquare(int i, int j, Queens board) {
            piece = null;
            pieceImage = null;
            highlight = false;
            highlightColor = Color.RED;
            this.i = i;
            this.j = j;
            this.board = board;
            initSquare();
        }

        private void initSquare() {
            this.addMouseListener(this);
        }
        
        public void clearPiece() {
            this.piece = null;
            pieceImage = null;
        }

        public void setPiece(int color, int piece) {
            this.piece = piece;
            pieceImage = board.getPieceImage(color, piece);
            repaint();
        }
        
        public Integer getPiece() {
            return piece;
        }

        private int setHighlight(boolean highlight) {
            this.highlight = highlight;
            repaint();
            return (getPiece() != null) ? 1 : 0;
        }

        private void mouseHover(boolean on) {
            board.lookupTargets(i, j, on);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Color bgPrev = null;
            if (highlight) {
                bgPrev = getBackground();
                setBackground(highlightColor);
            }
            super.paintComponent(g);

            if (pieceImage != null) {
                g.drawImage(pieceImage, 0, 0, null);
            }

            if (bgPrev != null) {
                setBackground(bgPrev);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseHover(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseHover(false);
        }
    }

}
