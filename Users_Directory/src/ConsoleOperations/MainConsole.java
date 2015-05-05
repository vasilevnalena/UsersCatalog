package ConsoleOperations;
/**
 * Created by 1231 on 26.03.2015.
 */
import java.io.*;
import java.lang.*;
import ConstantStrings.Strings;
import Catalog.Catalog;

public class MainConsole implements Serializable{

 OperationsUser operationsUser=new OperationsUser();
 static Strings constant=new Strings();
 static Catalog catalog=new Catalog();
    private int choose;

 public MainConsole(){}

    /*выбор действия в главной консольке*/
    public void chooseAction(BufferedReader buf) throws IOException {

        System.out.println(constant.getCONSOLE_CHOICE());
        try {
            choose = Integer.parseInt(buf.readLine());
        }
        catch(NumberFormatException ex){
            System.out.println(constant.getINCORRECT_TYPE_OF_DATA());
            chooseAction(buf);
        }
    }

 /*вызов главной консольки после успешной регистрации или авторизации*/
 public void start() throws Exception {

    //catalog.writeCatalog(catalog.getUsers());//обновляем каталог перед закрытием

    catalog.setUsers(catalog.readCatalog());//считывание каталога

   BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
      chooseAction(buf);

   switch (choose) {
    case 1:
       operationsUser.createUser(buf);
       start();
     break;

    case 2:
       operationsUser.editDataUser(buf);
       start();
     break;

    case 3:
       operationsUser.deleteUser(buf);
       start();
     break;

    case 4://выход из меню
     catalog.writeCatalog(catalog.getUsers());//обновляем каталог перед закрытием
     break;

    default:
     System.out.println(constant.getNOTHING_NOT_CHOSE()+"\n");
     start();
     break;
   }
   buf.close();
 }
    public static void main(String[] args) throws Exception {

        MainConsole authorization = new MainConsole();
        authorization.start();
    }
}