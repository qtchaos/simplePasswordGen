package com.chaos;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GUI implements ActionListener {
        JFrame frame = new JFrame("Pass Gen");
    GUI() {

        JTextField input = new JTextField();
        input.setBounds(25, 40, 30, 25);
        frame.add(input);
        
        JLabel text = new JLabel("Insert max password length.");
        text.setBounds(25, 10, 400, 25);
        text.setFont(new Font("", Font.PLAIN, 15));
        frame.add(text);

        // Listens to the JTextField and waits for user to press enter.
        input.addActionListener(e -> {
            try {
                String generatedPassword;
                String inputText = input.getText();
                int string2int = Integer.parseInt(inputText);

                // Uses the getAlphaNumericString function to generate a randomized string from the provided int.
                generatedPassword = getAlphaNumericString(string2int);
                System.out.println(generatedPassword);
                clipboardCopy(generatedPassword);
                // Set  background color to GREEN to show success.
                frame.getContentPane().setBackground(Color.GREEN);
            } catch (NumberFormatException err) {
                // Set  background color to RED to show failure.
                frame.getContentPane().setBackground(Color.RED);
                err.printStackTrace();
            }
        });

        // Frame options
        frame.setSize(250, 125);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // Adds a custom icon to the window.
        URL iconURL = getClass().getResource("/icon.png");
        assert iconURL != null;
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());
    }

    // This function generates the random string.
    static String getAlphaNumericString(int n) {

        // Random characters for use later on.
        String AlphaNumericString =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // Create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // Generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // Add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // See ln 27.
    }

    // Function for copying String values to the clipboard.
    public void clipboardCopy(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public static void main(String[] args) {
        new GUI();
    }
}