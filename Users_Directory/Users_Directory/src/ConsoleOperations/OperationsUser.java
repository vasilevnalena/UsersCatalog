package ConsoleOperations;

import ConstantStrings.Strings;
import UsersData.FamilyStatus;
import UsersData.User;
import Catalog.Catalog;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.sun.xml.internal.ws.util.ServiceFinder.find;

/**
 * Created by 1231 on 01.04.2015.
 */
public class OperationsUser {
    private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static OperationsXML operationsXML = new OperationsXML();
    private static Strings message=new Strings();
    private String name,surname,birthday,profession,email,login;
    private FamilyStatus familyStatus;
    private static Catalog catalog=new Catalog();
    private int choose;

    public OperationsUser(){
    }

    /*ввод пользовательского атрибута*/
    public String enterDataUser(String textMessage, BufferedReader buf)throws IOException{

        System.out.println(textMessage);
        if (!(buf.readLine().isEmpty()||buf.readLine().contains("")))
        return buf.readLine();
        else {
            System.out.println(message.getINCORRECT_TYPE_OF_DATA());
            enterDataUser(textMessage,buf);
            return null;
        }
    }

    /*выбор семейного положения пользователя*/
    public FamilyStatus enterFamilyStatus(BufferedReader buf) throws IOException {

        System.out.println(message.getENTER_FAMILY_STATUS());
        int chooseFamilyStatus = Integer.parseInt(buf.readLine());
        switch (chooseFamilyStatus) {
            case 1:
                familyStatus = FamilyStatus.MARRIED;
                break;
            case 2:
                familyStatus = FamilyStatus.NOT_MARRIED;
                break;
            default:
                System.out.println(message.getNOTHING_NOT_CHOSE());
                enterFamilyStatus(buf);
                break;
        }
        return familyStatus;
    }

    /*поиск пользователя в списке*/
    public User findUserInSet(String name, String login)throws Exception{

        boolean userIsFound=false;
        User foundUser=null;
        for(User user: catalog.getUsers()) {
            if(user.getName().equals(name)&&user.getLogin().equals(login))
            {
                userIsFound=true;
                foundUser=user;
                break;
            }
        }
        if(!userIsFound) System.out.println("UsersData was not found!\n");
        return foundUser;
    }

    /*создание объекта пользователя*/
    public User createObjectUser(String name, String surname, String birthday,
                                  FamilyStatus familyStatus, String profession,String email)throws ParseException {
        return new User(name,surname,format.parse(birthday),familyStatus,profession,email);
    }

    /*создание пользователя и его запись в xml и каталог(без логина/пароля)*/
    public void createUser(BufferedReader buf) throws ParseException, IOException, ClassNotFoundException {

        name=enterDataUser(message.getENTER_NAME(), buf);
        surname=enterDataUser(message.getENTER_SURNAME(), buf);
        birthday=enterDataUser(message.getENTER_BIRTHDAY(), buf);
        familyStatus=enterFamilyStatus(buf);
        profession=enterDataUser(message.getENTER_PROFESSION(), buf);
        //nameXML=enterDataUser(message.getENTER_NAME_XML(), buf);
        email=enterDataUser(message.getENTER_EMAIL(), buf);

        User user= createObjectUser(name, surname, birthday, familyStatus, profession,email);

        operationsXML.createDocumentXML(user,email);
        catalog.getUsers().add(user);

        catalog.writeCatalog(catalog.getUsers());//обновляем каталог
    }

    /*создание пользователя и его запись в xml и каталог(с логином/паролем)*/
    public void createUser(String login, int password, BufferedReader buf) throws ParseException, IOException {

        name=enterDataUser(message.getENTER_NAME(), buf);
        surname=enterDataUser(message.getENTER_SURNAME(), buf);
        birthday=enterDataUser(message.getENTER_BIRTHDAY(), buf);
        familyStatus=enterFamilyStatus(buf);
        profession=enterDataUser(message.getENTER_PROFESSION(), buf);
        //nameXML=enterDataUser(message.getENTER_NAME_XML(), buf);
        email=enterDataUser(message.getENTER_EMAIL(), buf);

        User user=createObjectUser(name, surname, birthday, familyStatus, profession,email);

        user.setLogin(login);
        user.setPassword(password);
        operationsXML.createDocumentXML(user,email);
        catalog.getUsers().add(user);

        catalog.writeCatalog(catalog.getUsers());//обновляем каталог
    }

    /*удаление пользователя*/
    public void deleteUser(BufferedReader buf) throws Exception {

        name=enterDataUser(message.getENTER_NAME(), buf);
        login=enterDataUser(message.getENTER_LOGIN(), buf);

        boolean userIsFound=false;
        for (User user : catalog.getUsers()) {
             if ((user.getName().equals(name)) && (user.getLogin().equals(login))) {
                 catalog.getUsers().remove(user);
                 operationsXML.deleteDocument(operationsXML.findUserInXML(name,login));
                 userIsFound=true;
                    break;
             }
        }
        if(!userIsFound) System.out.println("UsersData was not found!\n");
        catalog.writeCatalog(catalog.getUsers());//обновляем каталог
    }

    /*выбор действия с проверкой корректности вводимых данных*/
    public void chooseAction(BufferedReader buf) throws IOException {

        System.out.println(message.getCHOOSE_CHANGE_ATTRIBUTE());
        try {
            choose = Integer.parseInt(buf.readLine());
        }
        catch(NumberFormatException ex){
            System.out.println(message.getINCORRECT_TYPE_OF_DATA());
            chooseAction(buf);
        }
    }

    /*выбор изменяемых атрибутов пользователя*/
    private void changingData(User user,BufferedReader buf) throws IOException, ParseException {

        chooseAction(buf);
        switch (choose) {
            case 1:
                user.setName(enterDataUser(message.getENTER_CHANGE_USER_NAME(),buf));
                changingData(user,buf);
                break;
            case 2:
                user.setSurname(enterDataUser(message.getENTER_CHANGE_USER_SURNAME(), buf));
                changingData(user,buf);
                break;
            case 3:
                user.setBirthday(format.parse(enterDataUser(message.getENTER_CHANGE_USER_BIRTHDAY(), buf)));
                changingData(user,buf);
                break;
            case 4:
                user.setFamilyStatus(enterFamilyStatus(buf));
                changingData(user,buf);
                break;
            case 5:
                user.setProfession(enterDataUser(message.getENTER_CHANGE_USER_PROFESSION(),buf));
                changingData(user,buf);
                break;
            case 6:
                user.setLogin(enterDataUser(message.getENTER_CHANGE_USER_LOGIN(),buf));
                changingData(user,buf);
                break;
            case 7:
                user.setPassword(Integer.parseInt(enterDataUser(message.getENTER_CHANGE_USER_PASSWORD(),buf)));
                changingData(user,buf);
                break;
            case 8:
                user.setEmail(enterDataUser(message.getENTER_CHANGE_USER_EMAIL(), buf));
                changingData(user,buf);
                break;
            case 9:
                break;
            default:
                System.out.println(message.getNOTHING_NOT_CHOSE()+"\n");
                changingData(user, buf);
                break;
        }
    }

    /*правка атрибутов пользователя*/
    public void editDataUser(BufferedReader buf) throws Exception {

        name=enterDataUser(message.getENTER_NAME(), buf);
        login=enterDataUser(message.getENTER_LOGIN(), buf);

        User user=findUserInSet(name, login);
            if (!(user==null)) {
            String nameXML = operationsXML.findUserInXML(name, login).getName();
            //отбрасываем "хвост"-.xml
            int i = nameXML.lastIndexOf('.');
            if (i != -1) {
                nameXML = nameXML.substring(0, i);
            }
            changingData(user, buf);
            operationsXML.createDocumentXML(user, nameXML);
        }
        catalog.writeCatalog(catalog.getUsers());//обновляем каталог
    }
}
