package com.chaos;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GUI implements ActionListener {

    GUI() {


        JFrame frame = new JFrame("testgen");
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
                generatedPassword = getAlphaNumericString(string2int);
                System.out.println(generatedPassword);
                clipboardCopy(generatedPassword);
            } catch (NumberFormatException error) {
                System.out.println("Invalid character!");
            }
        });

        // Frame options
        frame.setSize(250, 125);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Adds a custom icon to the window.
        URL iconURL = getClass().getResource("/icon.png");
        assert iconURL != null;
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());

    }

    public static void main(String[] args) {
        new GUI();
    }

    // This function generates the random string.
    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Function for copying String values to the clipboard.
    public void clipboardCopy(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}