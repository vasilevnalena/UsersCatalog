package GUI;

import Catalog.Catalog;
import ConstantStrings.Strings;
import ConsoleOperations.OperationsUser;
import ConsoleOperations.OperationsXML;
import UsersData.FamilyStatus;
import UsersData.User;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import java.util.Date;
import java.util.regex.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by 1231 on 20.04.2015.
 */

public class NewUserWindow {

    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private String name,surname,profession,email;
    private FamilyStatus familyStatus;
    private static Catalog catalog=new Catalog();
    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static OperationsGUI operationsGUI=new OperationsGUI();
    private static OperationsUser operationsUser=new OperationsUser();
    private static OperationsXML operationsXML=new OperationsXML();
    private static RegistrationWindow registrationWindow=new RegistrationWindow();
    private static StartWindow startWindow=new StartWindow();
    private static Strings message=new Strings();
    private static MainWindow mainWindow=new MainWindow();
    final JFrame newUserPage=new JFrame("Create user page");
    private  JDatePicker textBirthday;
    private  JTextField textProfession;
    private  JTextField textEmail;
    private  Box mainBox;
    private  static String[] familyStatusVariable = {
            "Married",
            "Not married"
    };
    private static JComboBox textFamilyStatus;
    private  JLabel label;
    private  JButton button;
    private  String[] names;
    private  String[] surnames;
    private JComboBox nameComboBox;//предлагаемый список уже существующих имен пользователей
    private JComboBox surnameComboBox;//предлагаемый список уже существующих фамилий пользователей

    public void setFamilyStatus(){

        if(textFamilyStatus.getSelectedItem().toString().equals("Married"))
            familyStatus = FamilyStatus.MARRIED;
        else
        if(textFamilyStatus.getSelectedItem().toString().equals("Not married"))
            familyStatus = FamilyStatus.NOT_MARRIED;
    }

    /*создание пользователя*/
    public void createUser(String login,int password) throws ParseException, IOException, ClassNotFoundException {

        name=nameComboBox.getSelectedItem().toString();
        surname=surnameComboBox.getSelectedItem().toString();
        String birthday=textBirthday.getModel().getDay() + "." + (textBirthday.getModel().getMonth()+1) + "." + textBirthday.getModel().getYear();
        setFamilyStatus();
        profession=textProfession.getText();
        email=textEmail.getText();

            User user = operationsUser.createObjectUser(name, surname, birthday, familyStatus, profession, email);
            //user.setLogin(startWindow.getTextLogin().getText());
        user.setLogin(login);
            //user.setPassword(Integer.parseInt(startWindow.getTextPassword().getText()));
        user.setPassword(password);

            operationsXML.createDocumentXML(user, login);
            catalog.setUsers(catalog.readCatalog());//считывание каталога
            catalog.getUsers().add(user);
            catalog.writeCatalog(catalog.getUsers());//обновляем каталог

    }

    /*считывание имен пользователей с каталога в массив*/
      private void readNamesUsers(){
        names=new String[catalog.getUsers().size()];
        int count=0;
        for (User user:catalog.getUsers()) {
            names[count] = user.getName();
            count++;
        }
    }

    /*считывание фамилий пользователей с каталога в массив*/
    private void readSurnamesUsers(){
        surnames=new String[catalog.getUsers().size()];
        int count=0;
        for (User user:catalog.getUsers()) {
            surnames[count] = user.getSurname();
            count++;
        }
    }

    /*проверка вводимого E-mail*/
    private boolean checkEmailFormat(){

        String regex = "(\\w+)@(\\w+\\.)(\\w+)" ;
        Pattern p2 = Pattern. compile (regex);
        Matcher m2 = p2.matcher(textEmail.getText());
        if (!m2.matches()) {
            JOptionPane.showMessageDialog(newUserPage, "Incorrect format e-mail! Please, try again.");
            return false;
        }
        else return true;
    }

    /*сборка окна создания пользователя*/
    public void newUserWindow(final String login, final int password) throws IOException, ParseException {

        newUserPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newUserPage.setResizable(false);//запрет изменения размеров окна
        mainBox = Box.createVerticalBox();
        mainBox.setMaximumSize(new Dimension(200,500));

        elementsFrameGUI.createSpace(3, mainBox);
        elementsFrameGUI.createLabel(label,"Name", mainBox);
        readNamesUsers();
        nameComboBox=new JComboBox(names);
        elementsFrameGUI.createComboBox(nameComboBox,mainBox);
        nameComboBox.setEditable(true);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label,"Surname:", mainBox);
        readSurnamesUsers();
        surnameComboBox=new JComboBox(surnames);
        elementsFrameGUI.createComboBox(surnameComboBox,mainBox);
        surnameComboBox.setEditable(true);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label,"Birthday:", mainBox);
        textBirthday = new JDateComponentFactory().createJDatePicker();
        elementsFrameGUI.createDateField(textBirthday, mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label,"FamilyStatus:", mainBox);
        textFamilyStatus=new JComboBox(familyStatusVariable);
        elementsFrameGUI.createComboBox(textFamilyStatus, mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label,"Profession:", mainBox);
        textProfession=new JTextField(50);
        elementsFrameGUI.createTextField(textProfession, mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        elementsFrameGUI.createLabel(label,"E-mail:", mainBox);
        textEmail=new JTextField(50);
        elementsFrameGUI.createTextField(textEmail, mainBox);
        elementsFrameGUI.createSpace(8, mainBox);

        JButton createUserButton=elementsFrameGUI.createButton(button,"Create new user",mainBox);
        elementsFrameGUI.createSpace(8,mainBox);

        newUserPage.setContentPane(mainBox);
        newUserPage.setMinimumSize(new Dimension(250, 400));
        newUserPage.setLocationRelativeTo(null);
        newUserPage.setVisible(true);

        createUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (operationsGUI.checkNullTextField(nameComboBox.getSelectedItem().toString(), newUserPage) &&
                            operationsGUI.checkNullTextField(surnameComboBox.getSelectedItem().toString(), newUserPage)&&
                            operationsGUI.checkNullTextField(textProfession.getText(), newUserPage) &&
                            checkEmailFormat())
                        try {
                            createUser(login,password);
                            //обновление пока тупое:закрытие старой формы и открытие обновленной
                            mainWindow.mainWindow();
                            newUserPage.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(newUserPage, message.getINCORRECT_TYPE_OF_DATA_PASSWORD());
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
