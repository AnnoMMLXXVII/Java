package com.tutorial.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -304130724453334296L;

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output; // output is client's laptop or client's end (!server)
    private ObjectInputStream input;
    private String message = "";
    private String serverIP; // IPAddress of person talking to
    private Socket connection;

    public Client(String host) {
        super("Client Window");
        serverIP = host;
        userText = new JTextField();
        userText.setForeground(Color.RED);
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        sendMessage(e.getActionCommand());
                        userText.setText("");
                    } // endae actionPerformed
                } // endae AcionListener


        ); // endae userText.addActionListener
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        chatWindow.setEditable(false);

        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } // endae CONSTRUCTOR

    // run
    public void run() {
        try {
            connectingToServer(); // waiting for connect of SERVER not all computers
            setupStreams();
            whileChatting();

        } // endea TRY
        catch (EOFException e) {
            userText.setForeground(Color.BLACK);
            showMessage("\n Client terminated connection");
        } // endae CATCH
        catch (IOException e) {
            e.printStackTrace();
        } // endae CATCH
    } // endae run


    // connecting to server
    private void connectingToServer() throws IOException {

        showMessage("Attemping Connection ... \n");
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
        showMessage(" Connected to: " + connection.getInetAddress().getHostAddress());

    } // endae connectingToServer

    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());

        showMessage("\n Streams are set!");

    } // endae setUpStreams

    private void whileChatting() throws IOException {
        ableToType(true);
        do {
            try {
                message = (String) input.readObject();
                showMessage(message);
            } // ednae TRY
            catch (ClassNotFoundException cnfe) {
                showMessage("\n Unknown Object - ERROR");
            } // ednae CATCH
        } // endae DO
        while (!message.equals("SERVER - END"));
    } // ednae whileChatting

    // sending message from client to server

    private void sendMessage(String message) {
        try {
            output.writeObject("CLIENT - " + message);
            output.flush();
            showMessage("\nCLIENT - " + message); // display message on new line
        } // endae TRY
        catch (IOException e) {
            chatWindow.append("\n ERROR -- MESSAGE COULD NOT SEND!");
        } // endae CATCH
    } // enae sendMessage


    private void close() {
        showMessage("\n Closing chat...");
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();
        } // ednae TRY
        catch (IOException e) {
            e.printStackTrace();
        } // ednea CATCH
    } // endae close


    private void ableToType(final boolean TorF) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                userText.setEditable(TorF);
            } // endae run
        }); // endae runnable and swingU
    } // endae ableToType


    private void showMessage(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                chatWindow.append(text);
            } // endae run
        }); // endae runnable and swingU
    } // ednae showMessage


}
