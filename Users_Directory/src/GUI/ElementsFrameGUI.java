package GUI;

import javafx.scene.control.ComboBox;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;


public class ElementsFrameGUI extends JFrame{

    /*создание метки*/
    public void createLabel(JLabel label,String nameLabel, Box mainBox){

        label=new JLabel(nameLabel);
        //mainBox.setAlignmentY(SwingConstants.LEFT);
        label.setAlignmentX(CENTER_ALIGNMENT);
        mainBox.add(label);
    }

    /*создание текстового поля*/
    public void createTextField(JTextField textField, Box mainBox){

        textField.setMaximumSize(new Dimension(200, 25));
        textField.setAlignmentX(CENTER_ALIGNMENT);
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

    /*создание календарного поля*/
    public void createDateField(JDatePicker picker, Box mainBox) {

        picker.setTextEditable(true);
        picker.setShowYearButtons(true);
        mainBox.add((Component) picker);
    }

    public void createCheckBox(JCheckBox checkBox,String nameCheckBox, Box box){

        checkBox=new JCheckBox(nameCheckBox);
        box.add(checkBox);
    }
}
