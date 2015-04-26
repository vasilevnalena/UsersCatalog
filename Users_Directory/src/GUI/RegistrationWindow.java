package GUI;

import ConstantStrings.Strings;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Created by 1231 on 13.04.2015.
 */

public class RegistrationWindow extends JFrame {

    private  ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private  Strings message=new Strings();
    private static OperationsGUI authorizationAndRegistration=new OperationsGUI();
    final JFrame registrationPage=new JFrame("Registration Page");
    static JTextField textLogin;//=new JTextField(50);
    static JTextField textPassword;//=new JPasswordField(50);
    private Box mainBox;
    private JLabel label;
    private JButton button;
  //  public String login;//=textLogin.getText();
   // public int password;//=Integer.parseInt(textPassword.getText());


    public static JTextField getTextLogin() {
        return textLogin;
    }

    public static JTextField getTextPassword() {
        return textPassword;
    }

    /*сборка окна регистрации*/
    public void registrationWindow() throws IOException {

        registrationPage.setDefaultCloseOperation(registrationPage.DISPOSE_ON_CLOSE);
        registrationPage.setResizable(false);//запрет изменения размеров окна
        mainBox = Box.createVerticalBox();

        elementsFrameGUI.createLabel(label,"Enter login:",mainBox);
        textLogin=new JTextField(50);
        elementsFrameGUI.createTextField(textLogin,mainBox);
        elementsFrameGUI.createSpace(5,mainBox);

        elementsFrameGUI.createLabel(label,"Enter password:",mainBox);
        textPassword=new JPasswordField(50);
        elementsFrameGUI.createTextField(textPassword,mainBox);
        elementsFrameGUI.createSpace(8,mainBox);

        JButton enterButton=elementsFrameGUI.createButton(button,"Registration",mainBox);
        elementsFrameGUI.createSpace(8,mainBox);

        registrationPage.setContentPane(mainBox);
        registrationPage.pack();
        registrationPage.setLocationRelativeTo(null);
        registrationPage.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    if (authorizationAndRegistration.checkNullTextField(textLogin.getText(),registrationPage)&&
                            authorizationAndRegistration.checkNullTextField(textPassword.getText(),registrationPage)) {
                        try {
                            authorizationAndRegistration.registration(textLogin.getText(), Integer.parseInt(textPassword.getText()), registrationPage);
                            registrationPage.dispose();
                        }catch(NumberFormatException ex) {
                          JOptionPane.showMessageDialog(registrationPage,message.getINCORRECT_TYPE_OF_DATA_PASSWORD());
                        }
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
