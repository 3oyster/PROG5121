/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Registration {
    String registerdUser;
    String registeredPassword;
    String registeredPhone;

    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> recipientPhone = new ArrayList<>();
    ArrayList<String> hashID = new ArrayList<>();
    ArrayList<String> uniqueMessageID = new ArrayList<>();
    Random random = new Random();

    // Username validation
    public boolean userNameCheck(String username) {
        if (username.contains("_") && username.length() >= 5) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure it contains an underscore and is no more than five characters");
            return false;
        }
    }

    // Password validation
    public boolean passwordCheck(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. It must have at least 8 characters, a capital letter, a number, and a special character.");
            return false;
        }
    }

    // Cell number validation
    public boolean cellNumberCheck(String cellphone) {
        if (cellphone.matches("^\\+[0-9]{1,3}[0-9]{1,10}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or missing international code");
            return false;
        }
    }
 
    // Sign up method
    public void signUp() {
        String firstName = JOptionPane.showInputDialog("Enter your name");
        String lastName = JOptionPane.showInputDialog("Enter your surname");
        String userName = JOptionPane.showInputDialog("Enter your username");
        String userPassword = JOptionPane.showInputDialog("Enter your password");
        String userCellNumber = JOptionPane.showInputDialog("Enter your cellnumber");

        if (userNameCheck(userName) && passwordCheck(userPassword) && cellNumberCheck(userCellNumber)) {
            registerdUser = userName;
            registeredPassword = userPassword;
            registeredPhone = userCellNumber;
            JOptionPane.showMessageDialog(null, "SignUp successfully! Welcome " + firstName + " " + lastName);
        } else {
            JOptionPane.showMessageDialog(null, "SignUp Failed!");
        }
    }

    // Sign in method
    public void signIn() {
        if (registerdUser == null || registeredPassword == null) {
            JOptionPane.showMessageDialog(null, "You need to SignUp before you can register");
            return;
        }

        String userName = JOptionPane.showInputDialog("Enter your username to login!");
        String userPassword = JOptionPane.showInputDialog("Enter your password to login!");

        if (userName.equals(registerdUser) && userPassword.equals(registeredPassword)) {
            String[] options = {"Send Message", "Coming Soon", "Exit"};
            int choices = JOptionPane.showOptionDialog(null, "Welcome to QuickChat", "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choices) {
                case 0 -> sendMessage();
                case 1 -> JOptionPane.showMessageDialog(null, "Coming Soon");
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
                    System.exit(0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Your details are incomplete");
        }
    }

    // Send message method
    public void sendMessage() {
        String recipientNumber;
        do {
            recipientNumber = JOptionPane.showInputDialog(null, "Enter recipient's number");
            if (recipientNumber == null) return;
        } while (!cellNumberCheck(recipientNumber));

        recipientPhone.add(recipientNumber);

        String messageNumber = JOptionPane.showInputDialog("How many messages do you want to send?");
        if (messageNumber == null) return;

        try {
            int messageCount = Integer.parseInt(messageNumber);
            for (int i = 0; i < messageCount; i++) {
                String message = JOptionPane.showInputDialog(null, String.format("Enter your message (%d of %d):", i + 1, messageCount));
                if (message == null) return;

                if (message.length() > 250) {
                    JOptionPane.showMessageDialog(null, "Message too long. Maximum is 250 characters.");
                    continue;
                } else if (message.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Please enter a message of less than 50 characters.");
                    continue;
                }

                String[] options = {"Send", "Store", "Disregard"};
                int actions = JOptionPane.showOptionDialog(null, "What do you want to do with this message?", "Message Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                String id = checkMessageId();

                switch (actions) {
                    case 0 -> {
                        sentMessage.add(message);
                        uniqueMessageID.add(id);

                        int number = sentMessage.size();
                        String hash = createMessageHash(id, number, message);
                        hashID.add(hash);

                        JOptionPane.showMessageDialog(null, "Message sent!\nMessage ID: " + id + "\nHash ID: " + hash);
                    }
                    case 1 -> {
                        storeMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message stored");
                    }
                    case 2 -> {
                        disregardMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message disregarded");
                    }
                    default -> {
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }

        printMessages();
        JOptionPane.showMessageDialog(null, "Total messages sent: " + returnTotalMessages());
    }

    // Generate unique message ID
    public String checkMessageId() {
        String Id;
        do {
            int firstNum = 1 + random.nextInt(9);
            int remainingNum = random.nextInt(1_000_000_000);
            Id = firstNum + String.format("%09d", remainingNum);
        } while (uniqueMessageID.contains(Id));
        return Id;
    }

    // Create message hash
    public String createMessageHash(String messageId, int messageNum, String messageContent) {
        String firstTwo = messageContent.length() >= 2 ? messageContent.substring(0, 2).toUpperCase() : "XX";
        String lastTwo = messageContent.length() >= 2 ? messageContent.substring(messageContent.length() - 2).toUpperCase() : "YY";
        return messageId.substring(0, 2) + ":" + String.format("%02d", messageNum) + firstTwo + lastTwo;
    }

    // Return total messages sent
    public int returnTotalMessages() {
        return sentMessage.size();
    }

    // Print sent messages
    public String printMessages() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No message was sent");
            return "No message was sent";
        }

        StringBuilder display = new StringBuilder("\n*** SENT MESSAGES ***\n");
        for (int i = 0; i < sentMessage.size(); i++) {
            display.append("Message ").append(i + 1).append(":\n")
                   .append("Message HASH ID: ").append(hashID.get(i)).append("\n")
                   .append("Message ID: ").append(uniqueMessageID.get(i)).append("\n")
                   .append("Message Content: ").append(sentMessage.get(i)).append("\n")
                   .append("Recipient: ").append(recipientPhone.get(i)).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, display.toString());
        return null;
    }
}