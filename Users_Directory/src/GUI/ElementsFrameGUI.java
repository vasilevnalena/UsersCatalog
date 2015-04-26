package GUI;

import javax.swing.*;
import java.awt.*;


public class ElementsFrameGUI extends JFrame{

    /*создание метки*/
    public void createLabel(JLabel label,String nameLabel, Box mainBox){

        label=new JLabel(nameLabel);
        label.setAlignmentX(CENTER_ALIGNMENT);
        mainBox.add(label);
    }

    /*создание текстового поля*/
    public void createTextField(JTextField textField, Box mainBox){

        //textField=new JTextField(50);
        textField.setMaximumSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        mainBox.add(textField);
    }

    /*создание отступа*/
    public void createSpace(int size, Box mainBox){

        mainBox.add(Box.createVerticalStrut(size));
    }

    /*создание кнопки*/
    public JButton createButton(JButton enterButton,String nameButton, Box mainBox){

        enterButton=new JButton(nameButton);
        enterButton.setAlignmentX(CENTER_ALIGNMENT);
        mainBox.add(enterButton);
        return enterButton;
    }

    /*создание выпадающего списка*/
    public void createComboBox(JComboBox comboBox, Box mainBox){

        comboBox.setAlignmentX(CENTER_ALIGNMENT);
        comboBox.setMaximumSize(new Dimension(200, 30));
        mainBox.add(comboBox);

    }

    public void createDateField(JFrame frame) {


    }
}
