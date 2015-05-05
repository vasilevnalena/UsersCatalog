package GUI;

import ConstantStrings.Strings;
import ConsoleOperations.Authorization;
import UsersData.User;

import javax.swing.*;
import java.util.Set;

/**
 * Created by 1231 on 20.04.2015.
 */
public class OperationsGUI extends JFrame{

     MainWindow mainWindow = new MainWindow();
     static Strings message=new Strings();
     static Authorization authorization=new Authorization();
     static NewUserWindow newUserWindow=new NewUserWindow();

    /*проверка на пустые поля*/
    public boolean checkNullTextField(String textField, JFrame frame){
        if (textField.isEmpty()|| textField.contains(" "))
        {   JOptionPane.showMessageDialog(frame,message.getFIELD_IS_NULL());
            return false;}
        else return true;
    }

    /*проверка на существование пользователя в каталоге(1)*/
    public boolean findUserInSet(Set<User> userSet, User userFind){

        boolean userIsFind=false;
        for (User user: userSet){
            if (userFind.getLogin().equals(user.getLogin())&& userFind.getPassword()==user.getPassword())
            { userIsFind=true; break;}
        }
        return userIsFind;
    }

    /*проверка на существование пользователя в каталоге(2)*/
    public boolean findUserInSet(Set<User> userSet, String login, int password){

        boolean userIsFind=false;
        for (User user: userSet){
            if (user.getLogin().equals(login)&& user.getPassword()==password)
            { userIsFind=true; break;}
        }
        return userIsFind;
    }

    /*авторизация пользователя*/
   public void authorization(String login, int password,JFrame frame) throws Exception {

        if (authorization.existUser(login, password) == true) {
            //переход к работе с каталогом:
            mainWindow.mainWindow();
            frame.setVisible(false);
            //frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, message.getAUTHORIZATION_NOT_EXECUTED());
        }
    }

    /*регистрация пользователя*/
    public void registration(String login, int password,JFrame frame) throws Exception {

        if (authorization.existUser(login, password) == false) {
                newUserWindow.newUserWindow(login,password);
                frame.setVisible(false);
          //  frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, message.getREGISTRATION_NOT_EXECUTED());
        }
    }
}
