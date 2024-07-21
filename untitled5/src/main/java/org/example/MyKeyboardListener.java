package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyboardListener implements KeyListener {
    ThePanel panel;
    public MyKeyboardListener(ThePanel panel) {
        this.panel = panel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
            switch (pressed) {
                case KeyEvent.VK_LEFT -> {
                    this.panel.setRectangleX(-(int) this.panel.getRectangle().getWidth());
                }
                case KeyEvent.VK_RIGHT -> {
                    this.panel.setRectangleX((int) this.panel.getRectangle().getWidth());
                }
                case KeyEvent.VK_UP -> {
                    this.panel.setRectangleY(-(int) this.panel.getRectangle().getHeight());
                }
                case KeyEvent.VK_DOWN -> {
                    this.panel.setRectangleY((int) this.panel.getRectangle().getHeight());
                }

            }
        this.panel.addPassedPoint(this.panel.getRectangle().getLocation());
        this.panel.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
