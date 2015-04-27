package ConsoleOperations;

import ConstantStrings.Strings;
import UsersData.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Catalog.*;

/**
 * Created by 1231 on 13.04.2015.
 */
public class Authorization {

     static MainConsole main = new MainConsole();
     static Catalog catalog=new Catalog();
     static Strings message=new Strings();
     static OperationsUser operationsUser=new OperationsUser();
     private String login;
     private int password;
     private int choose;

    /*ввод логина*/
    public void enterLogin(BufferedReader buf) throws IOException {

        while (true){
            System.out.println(message.getENTER_LOGIN());
            login = buf.readLine();
            if (!(login.equals("")||login.contains(" "))) break;
            else System.out.println("Incorrect type data of login.");
        }
    }

    /*ввод пароля*/
    public void enterPassword(BufferedReader buf) throws IOException {

        try {
            System.out.println(message.getENTER_PASSWORD());
            password = Integer.parseInt(buf.readLine());
        }
        catch(NumberFormatException ex){
            System.out.println("Incorrect type data of password.");
            enterPassword(buf);
        }
    }

    /*проверка на существование пользователя*/
    public boolean existUser(String login, int password) throws IOException, ClassNotFoundException {

        boolean exist = false;
        catalog.setUsers(catalog.readCatalog());//считываем каталог
        for (User user :  catalog.getUsers()) {
            if (login.equals(user.getLogin()) && password==user.getPassword())
            {exist = true;
             break;}
        }
        return exist;
    }

    /*авторизация пользователя*/
    public void authorization() throws Exception {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        enterLogin(buf);
        enterPassword(buf);

        if (existUser(login,password) == true) {
            //переход к работе с каталогом:
                main.start();
        }else {
            System.out.println(message.getAUTHORIZATION_NOT_EXECUTED());
            authorization();
        }
        buf.close();
    }

    /*регистрация пользователя*/
    public void registration() throws Exception {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        enterLogin(buf);
        enterPassword(buf);

        if (existUser(login,password) == false) {
            catalog.setUsers(catalog.readCatalog());//считываем каталог
            //переход к созданию пользователя и добавлению его в каталог:
            operationsUser.createUser(login, password, buf);
            catalog.writeCatalog( catalog.getUsers());//обновляем каталог
            main.start();
        } else {
            System.out.println(message.getREGISTRATION_NOT_EXECUTED());
            buf.close();
        }
        buf.close();
    }

    /*выбор действия в стартовой консольке*/
    public void chooseAction(BufferedReader buf) throws IOException {

        System.out.println(message.getWELCOME());
        try {
            choose = Integer.parseInt(buf.readLine());
        }
        catch(NumberFormatException ex){
            System.out.println("Incorrect type data of introduced number.");
            chooseAction(buf);
        }
    }

    /*запуск консольки*/
    private void startConsole() throws Exception {

        catalog.setUsers(catalog.readCatalog());

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        chooseAction(buf);

        switch(choose){
            case 1:
                authorization();
                break;
            case 2:
                registration();
                break;
            case 3:
                break;
            default:
                System.out.println(message.getNOTHING_NOT_CHOSE());
                startConsole();
                break;
        }
        buf.close();
    }

    public static void main(String[] args) throws Exception {

        Authorization authorization = new Authorization();
        authorization.startConsole();
    }
}
