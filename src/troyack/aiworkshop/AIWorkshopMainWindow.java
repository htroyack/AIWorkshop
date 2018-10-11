/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author htroyack
 */
class AIWorkshopMainWindow {
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Artificial Intelligence Workshop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("Artificial Intelligence Workshop");
        emptyLabel.setPreferredSize(new Dimension(640, 480));

        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.pack();

        frame.setVisible(true);
    }

    public AIWorkshopMainWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
