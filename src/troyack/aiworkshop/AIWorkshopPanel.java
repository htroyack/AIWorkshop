/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import javax.swing.JPanel;

/**
 *
 * @author troyack
 */
public abstract class AIWorkshopPanel extends JPanel {
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract String getIconName();
}
