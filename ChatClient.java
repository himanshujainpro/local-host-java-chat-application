package networking;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {

        int port=9994;
        System.out.println(InetAddress.getLocalHost());
        Socket s =new Socket(InetAddress.getLocalHost(),port);
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        DataSend dataSend=new DataSend(dout,br);
        DataReceive dataReceive=new DataReceive(din);

        dataSend.start();
        dataReceive.start();

    }
}
