package GUI;

import ConsoleOperations.OperationsUser;
import ConsoleOperations.OperationsXML;
import ConstantStrings.Strings;
import UsersData.FamilyStatus;
import UsersData.User;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import Catalog.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by 1231 on 27.04.2015.
 */
public class EditUserDataWindow extends JFrame{

    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static Strings message = new Strings();
    private static OperationsGUI operationsGUI = new OperationsGUI();
    private static OperationsUser operationsUser=new OperationsUser();
    private static OperationsXML operationsXML=new OperationsXML();
    private static Catalog catalog=new Catalog();
    private static MainWindow mainWindow=new MainWindow();
    JFrame changeUserWindow;// = new JFrame("Choose user's data to change");
    //private Box mainBox;
    private  static String[] familyStatusVariable = {
            "Married",
            "Not married"
    };
    private Box leftBox;
    private Box rightBox;
    private Box rowBox;
    private JButton button;
    private JCheckBox checkBox;
    private JCheckBox birthdayCheckBox;
    private JCheckBox familyStatusCheckBox;
    final JFrame accessToChangeUserWindow =new JFrame("Changing user");
    private JTextField textName;
    private JTextField textSurname;
    private JDatePicker textBirthday;
    private JTextField textProfession;
    private JTextField textEmail;
    private JTextField textLogin;
    private JTextField textPassword;
    private JComboBox familyStatusComboBox;
    private JLabel label;
    private JTextField textNamePass;
    private JTextField textLoginPass;
    private Box mainBox;
    private String nameUserSaved;
    private String loginUserSaved;
    private FamilyStatus familyStatus;

    /*строка чекбокс+текстовое поле(активация/деактивация выбранного атрибута)*/
    private void createRowAttribute(JCheckBox checkBox,String nameCheckBox, final JTextField textField){

        checkBox=new JCheckBox(nameCheckBox);
        textField.setEnabled(false);
        textField.setMaximumSize(new Dimension(200, 25));

        leftBox.add(checkBox);
        elementsFrameGUI.createSpace(6,leftBox);

        rightBox.add(textField);
        elementsFrameGUI.createSpace(6,rightBox);

        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textField.setEnabled(true);
                }
                else{
                    textField.setEnabled(false);
                }
            }
        });
    }

    /*окно получения доступа к редактированию данных пользователя*/
    public void enterEditUserWindow() throws IOException {

        mainBox = Box.createVerticalBox();
        leftBox=Box.createVerticalBox();
        rightBox=Box.createVerticalBox();
        accessToChangeUserWindow.setDefaultCloseOperation(accessToChangeUserWindow.DISPOSE_ON_CLOSE);
        accessToChangeUserWindow.setResizable(false);//запрет изменения размеров окна

        elementsFrameGUI.createLabel(label, "Name:", mainBox);
        textNamePass= new JTextField(50);
        elementsFrameGUI.createTextField(textNamePass, mainBox);
        elementsFrameGUI.createSpace(8, mainBox);

        elementsFrameGUI.createLabel(label, "Login:", mainBox);
        textLoginPass=new JTextField(50);
        elementsFrameGUI.createTextField(textLoginPass, mainBox);
        elementsFrameGUI.createSpace(12, mainBox);

        JButton enterButton=elementsFrameGUI.createButton(button,"Next",mainBox);
        enterButton.setMaximumSize(new Dimension(170, 35));
        elementsFrameGUI.createSpace(8, mainBox);

        accessToChangeUserWindow.setContentPane(mainBox);
        accessToChangeUserWindow.setMinimumSize(new Dimension(400, 200));
        accessToChangeUserWindow.setLocationRelativeTo(null);
        accessToChangeUserWindow.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (operationsGUI.checkNullTextField(textNamePass.getText(), accessToChangeUserWindow)&&
                            operationsGUI.checkNullTextField(textLoginPass.getText(), accessToChangeUserWindow))
                            if(isExistChangeUser()) {
                                nameUserSaved=textNamePass.getText();
                                loginUserSaved=textLoginPass.getText();
                                editUsersData();
                                accessToChangeUserWindow.dispose();
                            }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /*проверка на существование пользователя по имени/логину */
    public boolean isExistChangeUser() throws Exception {

        catalog.setUsers(catalog.readCatalog());//считывание каталога
        boolean userIsFound=false;

        for (User user : catalog.getUsers()) {
            if (user.getName().equals(textNamePass.getText()) && user.getLogin().equals(textLoginPass.getText())) {
                userIsFound=true;
                break;
            }
        }
        if(!userIsFound) {
            JOptionPane.showMessageDialog(accessToChangeUserWindow, message.getUSER_NOT_FOUND());
            return false;
        }
            else return true;
    }

    /*окно выбора изменяемых данных пользователя*/
    public void editUsersData() throws IOException {

        changeUserWindow = new JFrame("Choose user's data to change");
        changeUserWindow.setDefaultCloseOperation(changeUserWindow.DISPOSE_ON_CLOSE);
        changeUserWindow.setResizable(false);//запрет изменения размеров окна

        leftBox=Box.createVerticalBox();
        rightBox=Box.createVerticalBox();
        mainBox = Box.createVerticalBox();
        textName = new JTextField(50);
        createRowAttribute(checkBox,"Name", textName);

        textSurname=new JTextField(50);
        createRowAttribute(checkBox,"Surname", textSurname);

        birthdayCheckBox=new JCheckBox("Birthday");
        leftBox.add(birthdayCheckBox);
        elementsFrameGUI.createSpace(6, leftBox);
        textBirthday=new JDateComponentFactory().createJDatePicker();
        //косяк с переключением поля
        elementsFrameGUI.createDateField(textBirthday, rightBox);
        textBirthday.setTextEditable(false);
        textBirthday.getModel().setSelected(false);
        elementsFrameGUI.createSpace(6, rightBox);

        familyStatusCheckBox=new JCheckBox("FamilyStatus");
        leftBox.add(familyStatusCheckBox);
        elementsFrameGUI.createSpace(6, leftBox);
        familyStatusComboBox=new JComboBox(familyStatusVariable);
        elementsFrameGUI.createComboBox(familyStatusComboBox, rightBox);
        familyStatusComboBox.setEnabled(false);
        elementsFrameGUI.createSpace(6, rightBox);

        textProfession = new JTextField(50);
        createRowAttribute(checkBox,"Profession", textProfession);

        textEmail = new JTextField(50);
        createRowAttribute(checkBox,"E-mail", textEmail);

        textLogin = new JTextField(50);
        createRowAttribute(checkBox,"Login", textLogin);

        textPassword = new JPasswordField(50);
        createRowAttribute(checkBox,"Password", textPassword);

        Box rowBox = Box.createHorizontalBox();
        rowBox.add(leftBox);
        rowBox.add(rightBox);
        mainBox.add(rowBox);

        JButton enterButton = elementsFrameGUI.createButton(button, "Change chosen attributes", mainBox);
        elementsFrameGUI.createSpace(8, mainBox);
        changeUserWindow.getContentPane().add(mainBox);

        changeUserWindow.setMinimumSize(new Dimension(400, 400));
        changeUserWindow.setLocationRelativeTo(null);
        changeUserWindow.setVisible(true);
        //сброс чекбоксов
//        checkBox.setSelected(false);
       // birthdayCheckBox.setSelected(false);
       // familyStatusCheckBox.setSelected(false);

        birthdayCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textBirthday.setTextEditable(true);
                    textBirthday.getModel().setSelected(true);
                }
                else{
                    textBirthday.setTextEditable(false);
                    textBirthday.getModel().setSelected(false);
                }


            }
        });

        familyStatusCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    familyStatusComboBox.setEnabled(true);

                } else {
                    familyStatusComboBox.setEnabled(false);
                }
            }
        });

       enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setChangingDataUser();
                    changeUserWindow.dispose();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    /*применение выбранных атрибутов*/
    public void setChangingDataUser() throws Exception {

        Catalog.setUsers(catalog.readCatalog());//считывание каталога

        String nameXML=operationsXML.findUserInXML(nameUserSaved,loginUserSaved).getName();
        int i = nameXML.lastIndexOf('.');
        if (i != -1) {
            nameXML = nameXML.substring(0, i);
        }

       if (textBirthday.isTextEditable())operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setBirthday(format.parse(textBirthday.getModel().getDay() + "." + (textBirthday.getModel().getMonth() + 1) + "." + textBirthday.getModel().getYear()));
          if (familyStatusComboBox.isEnabled()) {
                 if(familyStatusComboBox.getSelectedItem().toString().equals("Married"))
                     familyStatus=FamilyStatus.MARRIED;
              else
                 if(familyStatusComboBox.getSelectedItem().toString().equals("Not married"))
                     familyStatus=FamilyStatus.NOT_MARRIED;
            operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setFamilyStatus(familyStatus);
        }
        if (textEmail.isEnabled())operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setEmail(textEmail.getText());
        if (textProfession.isEnabled())operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setProfession(textProfession.getText());
        if (textPassword.isEnabled())operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setPassword(Integer.parseInt(textPassword.getText()));
        if (textLogin.isEnabled()) {
            operationsUser.findUserInSet(nameUserSaved, loginUserSaved).setLogin(textLogin.getText());
            loginUserSaved=textLogin.getText();
        }
        if (textName.isEnabled()){
            operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setName(textName.getText());
            nameUserSaved=textName.getText();
        }
        if (textSurname.isEnabled())operationsUser.findUserInSet(nameUserSaved,loginUserSaved).setSurname(textSurname.getText());

        catalog.writeCatalog(catalog.getUsers());
        operationsXML.createDocumentXML(operationsUser.findUserInSet(nameUserSaved,loginUserSaved), nameXML);
        mainWindow.refreshTable();
    }
}
