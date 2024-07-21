package org.example;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        this.setTitle("Pain");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(865, 640);
        this.setVisible(true);
        ThePanel panel = new ThePanel();
        this.add(panel);
        panel.setSize(this.getWidth(), this.getHeight());
        panel.requestFocus();
    }
}
