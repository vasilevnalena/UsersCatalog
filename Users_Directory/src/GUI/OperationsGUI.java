package GUI;

import ConstantStrings.Strings;
import Operations.Authorization;
import javax.swing.*;
import Catalog.*;
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
                newUserWindow.newUserWindow();
                //frame.setVisible(false);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, message.getREGISTRATION_NOT_EXECUTED());
        }
    }
}
