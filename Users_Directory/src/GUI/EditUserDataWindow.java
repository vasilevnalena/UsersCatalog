package GUI;

import ConstantStrings.Strings;
import GUI.ElementsFrameGUI;
import GUI.OperationsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by 1231 on 27.04.2015.
 */
public class EditUserDataWindow {

    private static ElementsFrameGUI elementsFrameGUI = new ElementsFrameGUI();
    private static Strings message = new Strings();
    private static OperationsGUI operationsGUI = new OperationsGUI();
    final JFrame frame = new JFrame("Choose user's data to change");
    private Box mainBox;
    private JButton button;
    private JLabel label;
    private JCheckBox checkBox;

    public void chooseAttributes(){


    }

    /*сборка окна доступа к данным изменяемого пользователя*/
    public void createFrame() throws IOException {

        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);//запрет изменения размеров окна

        mainBox = Box.createVerticalBox();

        elementsFrameGUI.createCheckBox(checkBox,"Name",mainBox);
        elementsFrameGUI.createCheckBox(checkBox,"Surname",mainBox);
        elementsFrameGUI.createCheckBox(checkBox,"Birthday",mainBox);
        elementsFrameGUI.createCheckBox(checkBox,"FamilyStatus",mainBox);
        elementsFrameGUI.createCheckBox(checkBox,"Profession",mainBox);
        elementsFrameGUI.createCheckBox(checkBox,"E-mail",mainBox);

        JButton enterButton = elementsFrameGUI.createButton(button, "to change choosen attributes", mainBox);
        elementsFrameGUI.createSpace(8, mainBox);

        frame.getContentPane().add(mainBox);
        frame.setMinimumSize(new Dimension(200,300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

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
        startWindow.createFrame();
    }
}

