package GUI;

import Catalog.Catalog;
import UsersData.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by 1231 on 13.04.2015.
 */
public class MainWindow extends JFrame implements ActionListener {

    private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private RegistrationWindow registrationWindow=new RegistrationWindow();
    public static JFrame mainWindow;
    private static JMenuBar menuBar;
    private static DeleteUserWindow deleteUserWindow=new DeleteUserWindow();
    private String[] columnNames = {
            "Name",
            "Surname",
            "Birthday",
            "Family status",
            "Profession",
            "E-mail"
    };
    private static Catalog catalog=new Catalog();
    private static String[][] data;
    static JTable table;

    /*заполнение таблицы данными*/
    private static void fillTable() throws IOException, ClassNotFoundException, ParseException {

        data= new String[catalog.getUsers().size()][6];
        int count=0;
        catalog.setUsers(catalog.readCatalog());//считывание каталога
        for (User user:catalog.getUsers()){
            data[count][0]=user.getName();
            data[count][1]=user.getSurname();
            data[count][2]=format.format(user.getBirthday());
            data[count][3]=user.getFamilyStatus().toString();
            data[count][4]=user.getProfessionUser();
            data[count][5]=user.getEmail();
            count++;
        }
    }

    /*создание кнопки менюшки*/
    private void createButtonMenu(String nameMenu, String[] nameMenuItem){
        JMenu fileMenu = new  JMenu(nameMenu);
        for (String nameItem:nameMenuItem) {
            JMenuItem item = new JMenuItem(nameItem);
            item.setFont(new Font("Verdana", Font.PLAIN, 11));
            fileMenu.add(item);
            item.addActionListener(this);
            menuBar.add(fileMenu);
        }
    }

    /*создание менюшки */
    private void createMenu(){
        String[] item1={"Close"};
        String[] item2={"Create user","Edit user's data","Delete user","Refresh"};
        String[] item3={"Get help"};

        createButtonMenu("File", item1);
        createButtonMenu("Actions", item2);
        createButtonMenu("Help", item3);

        mainWindow.setJMenuBar(menuBar);
    }

    /*создание таблицы*/
    public void createTable() throws ParseException, IOException, ClassNotFoundException {

        fillTable();
        CatalogTableModel catalogTableModel=new CatalogTableModel(data, columnNames);
        table = new JTable(catalogTableModel);
        table.setRowSelectionAllowed(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 350));
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        mainWindow.getContentPane().add(scrollPane);
    }

    /*операции для кнопок менюшки*/
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if (command=="Close") System.exit(0);
        if (command=="Create user"){
            try {
                registrationWindow.registrationPage.dispose();
               // mainWindow.dispose();//для временного обновления таблицы
                registrationWindow.registrationWindow();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(command=="Delete user"){
           // try {
               // deleteUserWindow.deleteUserWindow();

          //  } catch (IOException e1) {
              //  e1.printStackTrace();
            //}
        }

    }

    /*создание главного окна */
    public  void mainWindow() throws IOException, ClassNotFoundException, ParseException {

        mainWindow=new JFrame("Catalog Page");
        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
        menuBar= new JMenuBar();
        createTable();
        createMenu();

        mainWindow.setMinimumSize(new Dimension(600,400));
        mainWindow.setMaximumSize(new Dimension(800, 400));
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

}

