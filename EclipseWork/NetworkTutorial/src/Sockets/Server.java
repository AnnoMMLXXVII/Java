package Sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws Exception {
        // server sides require two sockets
        // 1) accept all requeest
        // 2) accept the client
        // if more than one client, then more than one client
        System.out.println("Server has stared...");
        ServerSocket ss = new ServerSocket(9999); // port number must match client's port number
        ServerSocket sss = new ServerSocket(9998);
        System.out.println("Server is waiting for client request...");
        Socket s = ss.accept(); // server needs to accept request;
        Socket sock = sss.accept();
        System.out.println("Client Connected...");

        // now must fetch the data once the client is connected
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader br1 = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String str = br.readLine();
        String strSock = br1.readLine();

        System.out.println("Client Data : " + str);
        System.out.println("Client Data : " + strSock);
    }
}
