package Client_Server;

import java.io.*;
import java.net.Socket;

public class Client
{
    public static void startClient(){

        String  string;
        boolean exit = false;
        System.out.println("Socket Client Application");
        System.out.println("---- Start ----");
        try
        {
            BufferedReader stdin =new BufferedReader(new InputStreamReader(System.in));
            Socket s = new Socket("localhost",4444);
            DataInputStream dataIn = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            // ввод команд серверу
            while(!exit)
            {
                string = dataIn.readUTF();//сообщение от сервера
                System.out.println(string);
                string = stdin.readLine();//ввод логина

                if(!string.equals("exit"))
                {
                    dataOut.writeUTF(string);//отправка серверу логина
                    dataOut.flush();
                }
                else
                {
                    dataOut.writeUTF("exit");
                    dataOut.flush();
                    exit = true;
                }

                string = dataIn.readUTF();//сообщение от сервера
                System.out.println(string);
                string = stdin.readLine();//ввод пароля

                if(!string.equals("exit"))
                {
                    dataOut.writeUTF(string);//отправка серверу пароля
                    dataOut.flush();
                }
                else
                {
                    dataOut.writeUTF("exit");
                    dataOut.flush();
                    exit = true;
                }

                string = dataIn.readUTF();//сообщение от сервера
                System.out.println(string);
                if(string.equals("exit"))
                {
                    dataOut.writeUTF("exit");//получение ответа - существует ли запрашиваемый пользователь
                    dataOut.flush();
                    exit = true;
                }
            }
            dataIn.close();
            dataOut.close();
            s.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Disconnected link.");
        }
    }

    public static void main(String args[])
    {
        startClient();
    }
}