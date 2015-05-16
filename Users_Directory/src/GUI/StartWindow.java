package GUI;

import ConsoleOperations.OperationsUser;
import ConsoleOperations.OperationsXML;
import ConstantStrings.Strings;
import UsersData.FamilyStatus;
import UsersData.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Created by 1231 on 13.04.2015.
 */

import javax.swing.JFrame;
import javax.swing.JButton;

public class StartWindow extends JFrame {

    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static Strings message=new Strings();
    private static OperationsUser operationsUser=new OperationsUser();
    private static OperationsGUI operationsGUI =new OperationsGUI();
    private static OperationsXML operationsXML =new OperationsXML();
    final JFrame startPage = new JFrame("Start Page");
    private static JTextField textLogin=new JTextField(50);
    private static JTextField textPassword=new JPasswordField(50);
    private Box mainBox;
    private JButton button;
    private JLabel label;

    public JTextField getTextLogin() {
        return textLogin;
    }

    public JTextField getTextPassword() {
        return textPassword;
    }
    /*сборка стартового окна*/
    public void createFrame() throws IOException {

        startPage.setDefaultCloseOperation(startPage.EXIT_ON_CLOSE);
        startPage.setResizable(false);//запрет изменения размеров окна
        mainBox = Box.createVerticalBox();

        elementsFrameGUI.createLabel(label, "Enter login:", mainBox);
        elementsFrameGUI.createTextField(textLogin, mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label, "Enter password:", mainBox);
        elementsFrameGUI.createTextField(textPassword, mainBox);
        elementsFrameGUI.createSpace(5,mainBox);

        JButton enterButton=elementsFrameGUI.createButton(button,"Enter",mainBox);
        enterButton.setMaximumSize(new Dimension(80,35));
        elementsFrameGUI.createSpace(8, mainBox);

        elementsFrameGUI.createLabel(label, "OR", mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        JButton registrationButton=elementsFrameGUI.createButton(button,"Create an account",mainBox);
        registrationButton.setMaximumSize(new Dimension(150,35));
        elementsFrameGUI.createSpace(10,mainBox);

        startPage.setContentPane(mainBox);
        startPage.setMinimumSize(new Dimension(300, 250));
        startPage.setLocationRelativeTo(null);
        startPage.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (operationsGUI.checkNullTextField(textLogin.getText(), startPage)&&
                            operationsGUI.checkNullTextField(textPassword.getText(), startPage)) {
                        try {
                            operationsGUI.authorization(textLogin.getText(), Integer.parseInt(textPassword.getText()), startPage);
                        }catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(startPage,message.getINCORRECT_TYPE_OF_DATA_PASSWORD());
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        registrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                if (operationsGUI.checkNullTextField(textLogin.getText(), startPage) &&
                        operationsGUI.checkNullTextField(textPassword.getText(), startPage))
                    try{
                        operationsGUI.registration(textLogin.getText(), Integer.parseInt(textPassword.getText()), startPage);
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(startPage, message.getINCORRECT_TYPE_OF_DATA_PASSWORD());
                    }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }
        });
    }

    public static void main(String[] args) throws IOException, ParseException {
        StartWindow startWindow=new StartWindow();
        startWindow.createFrame();
    }
}



