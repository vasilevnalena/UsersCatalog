package Client_Server;

import ConsoleOperations.Authorization;
import ConsoleOperations.OperationsUser;
import ConsoleOperations.OperationsXML;
import GUI.OperationsGUI;
import Catalog.*;
import UsersData.FamilyStatus;
import UsersData.User;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Client {
    static Socket s = null;
    static DataInputStream dataIn;
    static DataOutputStream dataOut;
    private static SAXBuilder builder = new SAXBuilder();
    static boolean noConnect = true;
    private static OperationsUser operationsUser=new OperationsUser();
    private static OperationsXML operationsXML=new OperationsXML();
    private static Catalog catalog=new Catalog();
    private static Authorization authorization=new Authorization();

    public static void startClient() {

        DataInputStream dataIn;
        DataOutputStream dataOut;
        System.out.println("Database server started...");
        try {
        searchServer();
            readMessages();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean registration(String login,int password) throws ParseException, IOException, ClassNotFoundException {

        if (authorization.existUser(login, password) == false) {

            //для пользователя с пустыми полями(кроме логина-пароля) дата рождения="1.1.1" по умолчанию
            FamilyStatus familyStatus=FamilyStatus.NOT_MARRIED;
            User user = operationsUser.createObjectUser("","","1.1.1",familyStatus,"","");
            user.setLogin(login);
            user.setPassword(password);

            operationsXML.createDocumentXML(user, login);
            catalog.setUsers(catalog.readCatalog());//считывание каталога
            catalog.getUsers().add(user);
            catalog.writeCatalog(catalog.getUsers());//обновляем каталог
            return true;
        }
        else return false;
    }

    public static String getMethodFromGeneralServer(String inputMessage) throws IOException, JDOMException, ParseException, ClassNotFoundException { //Расшифровываем и вызываем нужные методы
        String tempInput = inputMessage;
        File file=new File("src/Client_Server/temp.xml");
        PrintWriter printWriter;
        file.createNewFile();
        printWriter = new PrintWriter(file.getAbsoluteFile());
        printWriter.print(inputMessage);
        System.out.println(inputMessage);

        printWriter.close();
        Document document = builder.build(file);
        Element root = document.getRootElement();
        if ("registration".equals(root.getName())) {
        String login=root.getChild("dataReg").getAttributeValue("login");
            int password1=Integer.parseInt(root.getChild("dataReg").getAttributeValue("pass1"));
            int password2=Integer.parseInt(root.getChild("dataReg").getAttributeValue("pass2"));
            if(password1==password2) {
                if (registration(login, password1))
                    return "ok";
            }
            else return "Error";
        }
        else

        if ("logIn".equals(root.getName())) {

            String login=root.getChild("dataLogIn").getAttributeValue("login");
            int password1=Integer.parseInt(root.getChild("dataLogIn").getAttributeValue("pass1"));
            if (authorization.existUser(login, password1) == true){
                System.out.println("User is exist!");
                return "ok";
            }
            else return "Error";
        }
        return "Error";
    }

    public static void searchServer()  {

       do {
            try {
                System.out.println("Find server...");
                s = new Socket("localhost", 4445);
                noConnect = false;
                System.out.println("Connected!");
                readMessages();
                } catch (ConnectException e) {
                noConnect = true;
                System.out.println("Server not found!");
                try {
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } while (noConnect);

    }

    public static void readMessages() throws JDOMException, ParseException, ClassNotFoundException,NullPointerException {
        String input="";
        String string;
        boolean exit = false;
        try{
        dataIn = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        dataOut = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));



            while (!exit) {

                if ((input = dataIn.readUTF()) != null) {
                    System.out.println(input);
                    String result = getMethodFromGeneralServer(input);
                    dataOut.writeUTF(result);
                    dataOut.flush();
                }
            }
            dataIn.close();
            dataOut.close();
            s.close();
        }catch (IOException ex){
            System.out.println("Поиск начался");searchServer();}

    }

    public static void main(String args[]) {
        startClient();
    }

}