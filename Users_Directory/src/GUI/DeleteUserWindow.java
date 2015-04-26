package GUI;

import Catalog.Catalog;
import ConstantStrings.Strings;
import Operations.OperationsUser;
import Operations.OperationsXML;
import UsersData.FamilyStatus;
import UsersData.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by 1231 on 22.04.2015.
 */
public class DeleteUserWindow {

    private static Catalog catalog=new Catalog();
    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static OperationsGUI operationsGUI=new OperationsGUI();
    final JFrame removeUser =new JFrame("Removing user");
    private  JTextField textName;//= new JTextField(50);
    private  JTextField textEmail;//=new JTextField(50);
    private  Box mainBox;// = Box.createVerticalBox();
    private JLabel label;
    private JButton button;
    private static OperationsUser operationsUser=new OperationsUser();
    private static OperationsXML operationsXML=new OperationsXML();
    private static Strings message=new Strings();
    private static MainWindow mainWindow=new MainWindow();

    public void removeUser() throws Exception {

        catalog.setUsers(catalog.readCatalog());//считывание каталога
        boolean userIsFound=false;
        for (User user : catalog.getUsers()) {
            if (user.getName().equals(textName.getText()) && user.getEmail().equals(textEmail.getText())) {
                catalog.getUsers().remove(user);
                operationsXML.deleteDocument(operationsXML.findUserInXML(textName.getText(),textEmail.getText()));
                userIsFound=true;
                break;
            }
        }
        if(!userIsFound) JOptionPane.showMessageDialog(removeUser, message.getUSER_NOT_FOUND());
        catalog.writeCatalog(catalog.getUsers());//обновляем каталог
    }

    /*сборка окна удаления пользователя*/
    public void deleteUserWindow() throws IOException {

        mainBox = Box.createVerticalBox();
        removeUser.setDefaultCloseOperation(removeUser.DISPOSE_ON_CLOSE);
        removeUser.setResizable(false);//запрет изменения размеров окна

        elementsFrameGUI.createLabel(label,"Name:", mainBox);
        textName= new JTextField(50);
        elementsFrameGUI.createTextField(textName, mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label,"E-mail:", mainBox);
        textEmail=new JTextField(50);
        elementsFrameGUI.createTextField(textEmail, mainBox);
        elementsFrameGUI.createSpace(8, mainBox);

        JButton enterButton=elementsFrameGUI.createButton(button,"Remove selected user",mainBox);
        elementsFrameGUI.createSpace(8,mainBox);

        removeUser.setContentPane(mainBox);
        removeUser.setMaximumSize(new Dimension(400, 200));
        removeUser.pack();
        removeUser.setLocationRelativeTo(null);
        removeUser.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                  if (operationsGUI.checkNullTextField(textName.getText(), removeUser)&&
                          operationsGUI.checkNullTextField(textEmail.getText(), removeUser))
                        try {
                            removeUser();
                                mainWindow.mainWindow.dispose();
                                mainWindow.mainWindow();
                            removeUser.dispose();
                        }catch(NumberFormatException ex) {
                            JOptionPane.showMessageDialog(removeUser, message.getINCORRECT_TYPE_OF_DATA_PASSWORD());
                        }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
