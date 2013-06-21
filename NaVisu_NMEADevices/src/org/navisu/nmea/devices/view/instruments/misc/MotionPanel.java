/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.misc;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author Serge Morvan
 */
public class MotionPanel {

    private Point initialClick;
    private final JFrame parent;
    private Dimension dimension;
    private int heightScreen;
    private int widthScreen;
    private int heightFrame;
    private int widthFrame;
    private int maxHeight;
    private int maxWidth;

    public MotionPanel(JFrame parent) {
        this.parent = parent;
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        heightScreen = (int) dimension.getHeight();
        widthScreen = (int) dimension.getWidth();
        heightFrame = parent.getPreferredSize().height;
        System.out.println("heightFrame : " + heightFrame);
        widthFrame = parent.getPreferredSize().width;
        System.out.println("widthFrame : " + widthFrame);
        maxWidth = widthScreen - widthFrame;
        maxHeight = heightScreen - heightFrame;
    }

    public void init(int x, int y) {
        initialClick = new Point(x, y);
    }

    public void setLocation(int x, int y) {

        // get location of Window
        int thisX = parent.getLocation().x;
        int thisY = parent.getLocation().y;
        // Determine how much the mouse moved since the initial click
        int xMoved = (thisX + x) - (thisX + initialClick.x);
        int yMoved = (thisY + y) - (thisY + initialClick.y);
        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        if (X > 0) {
            if ((X + widthFrame) > widthScreen) {
                X = widthScreen - widthFrame;
            }
        } else {
            X = 0;
        }
        if (Y > 0) {
            if ((Y + heightFrame) > heightScreen) {
                Y = heightScreen - heightFrame;
            }
        } else {
            Y = 0;
        }
        parent.setLocation(X, Y);
    }
}
