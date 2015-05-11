package GUI;

import ConstantStrings.Strings;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Created by 1231 on 27.04.2015.
 */
public class EditUserDataWindow extends JFrame{

    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static Strings message = new Strings();
    private static OperationsGUI operationsGUI = new OperationsGUI();
    private static NewUserWindow newUserWindow=new NewUserWindow();
    private static MainWindow mainWindow=new MainWindow();
    final JFrame frame = new JFrame("Choose user's data to change");
    private Box mainBox;
    private JTextField textField;
    private JDatePicker textBirthday;
    private  static String[] familyStatusVariable = {
            "Married",
            "Not married"
    };
    private Box leftBox=Box.createVerticalBox();
    private Box rightBox=Box.createVerticalBox();
    private JButton button;
    private JCheckBox checkBox;
    private JCheckBox birthdayCheckBox;
    private JCheckBox familyStatusCheckBox;
    private JComboBox familyStatusComboBox;
    final JFrame changeUser =new JFrame("Changing user");
    private JTextField textName;
    private JTextField textLogin;
    private JLabel label;

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
   /* public void enterEditUserWindow() throws IOException {

        mainBox = Box.createVerticalBox();
        changeUser.setDefaultCloseOperation(changeUser.DISPOSE_ON_CLOSE);
        changeUser.setResizable(false);//запрет изменения размеров окна

        elementsFrameGUI.createLabel(label,"Name:", mainBox);
        textName= new JTextField(50);
        elementsFrameGUI.createTextField(textName, mainBox);
        elementsFrameGUI.createSpace(8, mainBox);

        elementsFrameGUI.createLabel(label,"Login:", mainBox);
        textLogin=new JTextField(50);
        elementsFrameGUI.createTextField(textLogin, mainBox);
        elementsFrameGUI.createSpace(12, mainBox);

        JButton enterButton=elementsFrameGUI.createButton(button,"Remove selected user",mainBox);
        enterButton.setMaximumSize(new Dimension(170, 35));
        elementsFrameGUI.createSpace(8,mainBox);

        changeUser.setContentPane(mainBox);
        changeUser.setMinimumSize(new Dimension(400, 200));
        changeUser.setLocationRelativeTo(null);
        changeUser.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (operationsGUI.checkNullTextField(textName.getText(), changeUser)&&
                            operationsGUI.checkNullTextField(textLogin.getText(), changeUser))
                        try {
                            //...
                            mainWindow.refreshTable();
                            changeUser.dispose();
                        }catch(NumberFormatException ex) {
                            JOptionPane.showMessageDialog(changeUser, message.getINCORRECT_TYPE_OF_DATA_PASSWORD());
                        }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }*/

    /*сборка окна с изменяемыми данными пользователя*/
    public void editUsersData() throws IOException {

        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);//запрет изменения размеров окна

        mainBox = Box.createVerticalBox();

        textField = new JTextField(50);
        createRowAttribute(checkBox,"Name", textField);

        textField=new JTextField(50);
        createRowAttribute(checkBox,"Surname", textField);

        birthdayCheckBox=new JCheckBox("Birthday");
        leftBox.add(birthdayCheckBox);
        elementsFrameGUI.createSpace(6, leftBox);
        textBirthday=new JDateComponentFactory().createJDatePicker();
        //косяк с переключением поля
        elementsFrameGUI.createDateField(textBirthday, rightBox);
        textBirthday.setTextEditable(false);
        textBirthday.getModel().setSelected(false);
        elementsFrameGUI.createSpace(6,rightBox);

        familyStatusCheckBox=new JCheckBox("FamilyStatus");
        leftBox.add(familyStatusCheckBox);
        elementsFrameGUI.createSpace(6, leftBox);
        familyStatusComboBox=new JComboBox(familyStatusVariable);
        elementsFrameGUI.createComboBox(familyStatusComboBox, rightBox);
        familyStatusComboBox.setEnabled(false);
        elementsFrameGUI.createSpace(6, rightBox);

        textField = new JTextField(50);
        createRowAttribute(checkBox,"Profession", textField);

        textField = new JTextField(50);
        createRowAttribute(checkBox,"E-mail", textField);

        textField = new JTextField(50);
        createRowAttribute(checkBox,"Login", textField);

        textField = new JPasswordField(50);
        createRowAttribute(checkBox,"Password", textField);

        Box rowBox = Box.createHorizontalBox();
        rowBox.add(leftBox);
        rowBox.add(rightBox);
        mainBox.add(rowBox);
        JButton enterButton = elementsFrameGUI.createButton(button, "Change chosen attributes", mainBox);
        elementsFrameGUI.createSpace(8, mainBox);
        frame.getContentPane().add(mainBox);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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

                    //...
                    mainWindow.refreshTable();
                   //  } catch (IOException e1) {
                     //  e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) throws IOException {
        EditUserDataWindow startWindow=new EditUserDataWindow();
        startWindow.editUsersData();
    }
}

