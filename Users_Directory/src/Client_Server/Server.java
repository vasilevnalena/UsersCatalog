package Client_Server;

import Catalog.Catalog;
import GUI.OperationsGUI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server
{
    private static String string,login;
    private static int password;
    private static OperationsGUI operationsGUI=new OperationsGUI();
    private static Catalog catalog=new Catalog();

    public static void startServer(){

        boolean exit = false;
        System.out.println("Socket Server Application");
        System.out.println("---- Start ----");
        try
        {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket socket = serverSocket.accept();
            DataInputStream dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while(!exit){
                dataOut.writeUTF("Enter login: ");//сообщение для клиента
                dataOut.flush();
                login = dataIn.readUTF();//получение логина от клиента
                System.out.println("Received login: " + login);
                if(login.equals("exit")) break;

                // boolean isNumberFormat=false;
                dataOut.writeUTF("Enter password: ");//сообщение для клиента
                dataOut.flush();
                // while(!isNumberFormat) {
                //   try {
                string = dataIn.readUTF();//получение пароля от клиента
                if (string.equals("exit")) break;
                password = Integer.parseInt(string);
                System.out.println("Received password: " + password);
                       /*  isNumberFormat = true;
                   //  } catch (NumberFormatException ex) {
                    //     dataOut.writeUTF("Incorrect type of password! Please, enter password again.");
                      //   dataOut.flush();

                    // }
                 }*/
                if (operationsGUI.findUserInSet(catalog.readCatalog(),login,password)) {
                    dataOut.writeUTF("This user exists!");//сообщение для клиента
                }else{
                    dataOut.writeUTF("This user not exists!");//сообщение для клиента
                }
                System.out.println("----The end of operation----");
                dataOut.flush();

            }
            dataIn.close();
            dataOut.close();
            socket.close();
            serverSocket.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Disconnected link.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("---- Finish ----");
    }

    public static void main(String args[]) {

        startServer();
    }
}
