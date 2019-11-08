package networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class DataSend extends Thread{
    String msgout;
    DataOutputStream dataOutputStream;
    BufferedReader bufferedReader;

    DataSend(DataOutputStream dataOutputStream, BufferedReader bufferedReader){
        this.dataOutputStream=dataOutputStream;
        this.bufferedReader=bufferedReader;
    }

    @Override
    public void run() {
        while (true){
            super.run();
            try {
                msgout=bufferedReader.readLine();
                dataOutputStream.writeUTF(msgout);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class DataReceive extends Thread{
    String msgin;
    DataInputStream dataInputStream;

    DataReceive(DataInputStream dataInputStream){
        this.dataInputStream=dataInputStream;
    }

    @Override
    public void run() {
        while (true){
            super.run();
            try {
                msgin=dataInputStream.readUTF();
                System.out.println("---------------------------------------"+msgin);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ChatServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is Started");
        ServerSocket ss= new ServerSocket(9994);
        System.out.println("Server is waiting for request");
        Socket s=ss.accept();
        System.out.println("Client is connected");
        System.out.println(ss.getInetAddress());
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        DataSend dataSend=new DataSend(dout,br);
        DataReceive dataReceive=new DataReceive(din);

        dataSend.start();
        dataReceive.start();

    }
}
