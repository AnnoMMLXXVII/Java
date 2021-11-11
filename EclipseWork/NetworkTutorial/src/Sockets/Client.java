package Sockets;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String args[]) throws Exception {
        // @param - IP Address
        // @param - Port Number
        String ip = "localhost"; // client IP
        // if server ==> mention the server port;
        int port = 9999; // 0-1023 to 65535
        Socket s = new Socket(ip, port);
        Socket sock = new Socket("localhost", 9998);

        String str = "Haku Ready";
        String strSock = "Haku Wei Ready";

        // trying to send data to server side;

        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        OutputStreamWriter socker = new OutputStreamWriter(sock.getOutputStream());
        // @Param --> need to mention from where do you want to send the data
        // s.outputStreamSOcket --> is from the socket's output port;

        PrintWriter out = new PrintWriter(os);
        PrintWriter sockOut = new PrintWriter(socker);
        os.write(str);
        socker.write(strSock);
        os.flush(); // must flush inorder to negate null or rid of excess buffer bits
        socker.flush();
    }
}
