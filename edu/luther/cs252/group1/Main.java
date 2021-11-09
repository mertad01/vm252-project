package edu.luther.cs252.group1;
//
// Title: VM252 debugger interface
// Authors: Michael Musa, Kritib Bhattarai, Adam Mertzenich
// Date: 11/09/21
//

//Import Swing and awt packages

import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ProgramFrame frame = new ProgramFrame();
            frame.setTitle("VM252 Debugger");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.setVisible(true);
        });
    }
}

