package GUI;

import ConstantStrings.Strings;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Created by 1231 on 13.04.2015.
 */

import javax.swing.JFrame;
import javax.swing.JButton;

public class StartWindow extends JFrame {

    private static RegistrationWindow registrationWindow=new RegistrationWindow();
    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static Strings message=new Strings();
    private static OperationsGUI operationsGUI =new OperationsGUI();
    final JFrame startPage = new JFrame("Start Page");
    private JTextField textLogin=new JTextField(50);
    private JTextField textPassword=new JPasswordField(50);
    private Box mainBox;
    private JButton button;
    private JLabel label;

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
        elementsFrameGUI.createSpace(8, mainBox);

        elementsFrameGUI.createLabel(label, "OR", mainBox);
        elementsFrameGUI.createSpace(5, mainBox);

        JButton registrationButton=elementsFrameGUI.createButton(button,"Create an account",mainBox);
        elementsFrameGUI.createSpace(10,mainBox);

        startPage.setContentPane(mainBox);
        startPage.pack();
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
                    registrationWindow.registrationWindow();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //startPage.setVisible(false);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        StartWindow startWindow=new StartWindow();
        startWindow.createFrame();
    }
}



