/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author htroyack
 */
class AIWorkshopMainWindow {
    
    private static void createAndShowGUI() {
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
