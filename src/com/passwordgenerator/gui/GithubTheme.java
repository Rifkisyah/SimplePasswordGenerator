package com.passwordgenerator.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GithubTheme {

    // Warna umum untuk dark mode GitHub
    static final Color DARK_BACKGROUND = new Color(22, 27, 34);
    private static final Color DARK_PANEL = new Color(36, 41, 46);
    private static final Color DARK_TEXT = Color.WHITE;
    private static final Color DARK_BORDER = new Color(88, 96, 105);
    private static final Font DEFAULT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    // Style untuk JButton
    public static void styleDarkButton(JButton button) {
        button.setBackground(DARK_PANEL);
        button.setForeground(DARK_TEXT);
        button.setFont(DEFAULT_FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(DARK_BORDER));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(50, 56, 62));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(DARK_PANEL);
            }
        });
    }

    // Style untuk JPanel
    public static void styleDarkPanel(JPanel panel) {
        panel.setBackground(DARK_BACKGROUND);
    }

    // Style untuk JLabel
    public static void styleDarkLabel(JLabel label) {
        label.setForeground(DARK_TEXT);
        label.setFont(DEFAULT_FONT);
    }

    // Style untuk JTextField
    public static void styleDarkTextField(JTextField textField) {
        textField.setBackground(DARK_PANEL);
        textField.setForeground(DARK_TEXT);
        textField.setCaretColor(DARK_TEXT);
        textField.setBorder(BorderFactory.createLineBorder(DARK_BORDER));
        textField.setFont(DEFAULT_FONT);
        textField.setOpaque(true);
    }

    // Style untuk JFrame
    public static void styleDarkFrame(JFrame frame) {
        frame.getContentPane().setBackground(DARK_BACKGROUND);
        frame.getContentPane().setForeground(DARK_TEXT);
    }
}

