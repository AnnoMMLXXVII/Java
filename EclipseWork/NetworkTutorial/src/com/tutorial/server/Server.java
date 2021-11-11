package com.tutorial.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    // streams are ways computers talk input and output
    // output is ab user while input is ad user
    private ServerSocket server;
    private Socket connection; //


    public Server() {
        super("Server Window");
        userText = new JTextField();
        userText.setForeground(Color.RED);
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sendMessage(e.getActionCommand()); // will take message and send it to the other person
                        userText.setText(""); // will reset text box to blank after every message sent
                    } // endae ActionPerformed
                } // endae ActionListener
        ); // endae userText.addActionListener()
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        chatWindow.setEditable(false);
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    } // endae Constructor


    public void run() {
        try {
            // need to setup server and give port number
            server = new ServerSocket(6789, 100);
            // @param -- 6789 ==> port number --> testing port
            // @param -- 100 ==> backlog number --> queue for those watiting for this port number (queue length)
            // client side is going to need to know this port number 6789
            while (true) {
                // part that needs to run over and over again
                try {
                    // run three methods
                    // wait for connection
                    Connection();
                    // setup Streams --> input and output
                    Streams();
                    // program runs the window while chatting
                    Chatting();
                } // endae TRY
                catch (EOFException e) {
                    showMessage("\n Server ended the Connection!");
                } // endae CATCH
                finally {
                    close();
                } // endae FINALLY
            } // endae WHILE

        } // endea TRY
        catch (IOException e) {
            e.printStackTrace();
        } // endae CATCH
    } // endae run

    // connection
    private void Connection() throws IOException {

        showMessage(" Waiting for someone to connect... \n");
        connection = server.accept(); // accepts connection when someone wants to connect to use
        showMessage(" Now connected to " + connection.getInetAddress().getHostAddress());
        chatWindow.setForeground(Color.BLUE);
    } // endae Connection

    // Streams
    private void Streams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream()); // send things out
        output.flush();
        input = new ObjectInputStream(connection.getInputStream()); // receive things in!
        showMessage(" \n Streams are now setup! \n");
    } // endae Streams

    // code during chatting
    private void Chatting() throws IOException {

        String message = " You are now Connected!";
        sendMessage(message);
        ableToType(true);

        do {
            // have a conversation
            try {
                message = (String) input.readObject(); // their message is viewed as object and read it as a string(hence a cast);
                showMessage("\n" + message); // display it in the textArea
            } // endae TRY
            catch (ClassNotFoundException cnfe) {
                showMessage("\n Error occured...");
            } // endae CATCH
        } // endae DO
        while (!message.equals("CLIENT - END"));

    } // endae Chatting


    private void close() {
        showMessage("\n Closing Connections...\n");
        ableToType(false);
        try {
            // need to close streams
            output.close();     // closes outgoing info
            input.close(); // closes incoming info
            connection.close(); // closes socket (main conneciton inter you and someone else
        } // endae TRY
        catch (IOException e) {
            e.printStackTrace();
        } // endae CATCH
    } // endae close

    // send message to client --> computer that is connected to the server
    private void sendMessage(String message) {
        try {
            output.writeObject("\nSERVER - " + message); // takes object and puts into output stream (sends to the other person or client);
            output.flush(); // pushes all extra bytes to user ( no left overs);
            showMessage("\nSERVER - " + message); // will show on user's screen
        } // enddae TRY
        catch (IOException ioe) {
            chatWindow.append("\n ERROR OCCURED...MESSAGE CANNOT BE SEND!");
        } // endae CATCH
    } // endae sendMessage

    // updates chatWindow
    private void showMessage(final String text) {
        // thread that updates the chatwindow part of the GUI not the whole GUI
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        chatWindow.append(text);
                    } // endae run
                } // endae runnable
        ); // endae invokeLater
    } // endae showMessage


    // changes permission on whether user can type or not
    private void ableToType(final boolean TorF) {
        // updating GUI on whether user can type or not
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        userText.setEditable(TorF);
                    } // endae run
                } // endae runnable
        ); // endae invokeLater
    } // endae ableToType

} // endae CLASS
