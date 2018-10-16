/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author htroyack
 */
class AIWorkshopMainWindow {
    
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception ex) {
            Logger.getLogger(AIWorkshopMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFrame frame = new JFrame("Artificial Intelligence Workshop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new TabbedPane(), BorderLayout.CENTER);
        frame.pack();

        frame.setVisible(true);
    }

    public AIWorkshopMainWindow() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    
}
