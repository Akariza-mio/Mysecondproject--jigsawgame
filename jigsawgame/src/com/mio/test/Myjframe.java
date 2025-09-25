package com.mio.test;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Myjframe extends JFrame implements KeyListener {
    JButton jtb1 = new JButton("点我！");

    public Myjframe() {
        this.setTitle("Test");
        this.setSize(600, 600);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        //jtb1.setBounds(0, 0, 100, 50);
        jtb1.addKeyListener(this);
        this.getContentPane().add(jtb1);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开");
    }
}
