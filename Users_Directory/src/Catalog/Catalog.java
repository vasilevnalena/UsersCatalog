package Catalog;

import ConstantStrings.Strings;
import UsersData.User;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1231 on 10.04.2015.
 */
public class Catalog {

    private static Set<User>users = new HashSet<User>();//временный список пользователей, на время работы программы
    static Strings constant=new Strings();

    /*получение списка пользователей*/
    public static Set<User> getUsers() {
        return users;
    }

    /*запись списка пользователей*/
    public static void setUsers(Set<User> users) {
        Catalog.users = users;
    }

    /*считывание каталога*/
    public static Set<User> readCatalog() throws IOException, ClassNotFoundException {

        //добавить проверку на пустой файл(Catalog.xml)
        ObjectInputStream in = new ObjectInputStream(new
                FileInputStream(constant.getPATH_TO_THE_CATALOG()));
        Set<User> values = (Set<User>) in.readObject();
        in.close();
            return values;
    }

    /*обновление каталога(перезапись)*/
    public static void writeCatalog(Set<User> catalog) throws IOException {//перезапись каталога:

        ObjectOutputStream out = new ObjectOutputStream(new
                FileOutputStream(constant.getPATH_TO_THE_CATALOG()));
        out.writeObject(catalog);
        out.close();
    }

    /*public static void main(String[] args) throws Exception {

     //   MainConsole authorization = new MainConsole();
        // authorization.start();
      //  writeCatalog();
        setUsers(readCatalog());
        System.out.println(users);
    }*/
}
