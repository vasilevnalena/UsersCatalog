package ConstantStrings;

/**
 * Created by 1231 on 07.04.2015.
 */
public class Strings {
    private final String WELCOME="Welcome to the Directory of users! Choose...\n...1 to authorization,\n...2 to registration,\n...3 to exit.";
    private final String AUTHORIZATION_NOT_EXECUTED="Enter is not executed. Check correctness of login and password.";
    private final String REGISTRATION_NOT_EXECUTED="Registration is not executed. Such user already exists.";
    private final String INCORRECT_TYPE_OF_DATA_PASSWORD ="Incorrect type data of introduced password.";
    private final String INCORRECT_TYPE_OF_DATA="Incorrect type data of introduced field.";
    private final String FIELD_IS_NULL ="One or several field is null!";
    private final String ENTER_PASSWORD = "Enter password:";
    private final String ENTER_LOGIN = "Enter login:";
    private final String CONSOLE_CHOICE = "Welcome to the Directory of users! \n Choose...\n ...1 to create user, \n ...2 to edit user's data,\n ...3 to delete user\n ...4 to exit.";
    private final String ENTER_NAME="Enter name:";
    private final String ENTER_SURNAME="Enter surname:";
    private final String ENTER_BIRTHDAY="Enter birthday";
    private final String ENTER_FAMILY_STATUS="Enter family status( 1 - MARRIED or 2 - NOT MARRIED):";
    private final String ENTER_PROFESSION="Enter profession:";
    private final String ENTER_EMAIL="Enter e-mail:";
    private final String NOTHING_NOT_CHOSE="You have not chosen nothing.";
    //private final String ENTER_NAME_XML="Enter name of document XML:";
    private final String PATH_TO_THE_CATALOG="C:\\Users\\1231\\IdeaProjects\\Users_Directory\\src\\Catalog\\Catalog.xml";
    private final String PATH_TO_THE_XML_FILES="C:\\Users\\1231\\IdeaProjects\\Users_Directory\\src\\FilesXML\\";
    private final String CHOOSE_CHANGE_ATTRIBUTE = "If you want...\n" +
            "...to change user's name, to change choose 1,\n" +
            "...to change user's surname, choose 2,\n" +
            "...to change user's birthday, choose 3,\n" +
            "...to change user's family status, choose 4,\n" +
            "...to change user's profession, choose 5,\n" +
            "...to change user's login, choose 6,\n" +
            "...to change user's password, choose 7,\n" +
            "...to change user's e-mail, choose 8,\n"+
            "...to exit choose 9.";
    private final String ENTER_CHANGE_USER_NAME="Enter new name:";
    private final String ENTER_CHANGE_USER_SURNAME="Enter new surname:";
    private final String ENTER_CHANGE_USER_BIRTHDAY="Enter new birthday:";
    private final String ENTER_CHANGE_USER_FAMILY_STATUS="Enter new family status:";
    private final String ENTER_CHANGE_USER_PROFESSION="Enter new profession:";
    private final String ENTER_CHANGE_USER_LOGIN="Enter new login:";
    private final String ENTER_CHANGE_USER_PASSWORD="Enter new password:";
    private final String ENTER_CHANGE_USER_EMAIL="Enter new e-mail:";
    private final String USER_NOT_FOUND="User not found!";

    public String getENTER_PASSWORD() {
        return ENTER_PASSWORD;
    }

    public String getENTER_LOGIN() {
        return ENTER_LOGIN;
    }

    public String getCONSOLE_CHOICE() {
        return CONSOLE_CHOICE;
    }

    public String getENTER_NAME() {
        return ENTER_NAME;
    }

    public String getENTER_SURNAME() {
        return ENTER_SURNAME;
    }

    public String getENTER_BIRTHDAY() {
        return ENTER_BIRTHDAY;
    }

    public String getENTER_FAMILY_STATUS() {
        return ENTER_FAMILY_STATUS;
    }

    public String getENTER_PROFESSION() {
        return ENTER_PROFESSION;
    }

    public String getNOTHING_NOT_CHOSE() {
        return NOTHING_NOT_CHOSE;
    }

  //  public String getENTER_NAME_XML() {
    //    return ENTER_NAME_XML;
   // }

    public String getPATH_TO_THE_CATALOG() {
        return PATH_TO_THE_CATALOG;
    }

    public String getCHOOSE_CHANGE_ATTRIBUTE() {
        return CHOOSE_CHANGE_ATTRIBUTE;
    }

    public String getENTER_CHANGE_USER_NAME() {
        return ENTER_CHANGE_USER_NAME;
    }

    public String getENTER_CHANGE_USER_SURNAME() {
        return ENTER_CHANGE_USER_SURNAME;
    }

    public String getENTER_CHANGE_USER_BIRTHDAY() {
        return ENTER_CHANGE_USER_BIRTHDAY;
    }

    public String getENTER_CHANGE_USER_FAMILY_STATUS() {
        return ENTER_CHANGE_USER_FAMILY_STATUS;
    }

    public String getENTER_CHANGE_USER_PROFESSION() {
        return ENTER_CHANGE_USER_PROFESSION;
    }

    public String getENTER_CHANGE_USER_LOGIN() {
        return ENTER_CHANGE_USER_LOGIN;
    }

    public String getENTER_CHANGE_USER_PASSWORD() {
        return ENTER_CHANGE_USER_PASSWORD;
    }

    public String getWELCOME() {
        return WELCOME;
    }

    public String getAUTHORIZATION_NOT_EXECUTED() {
        return AUTHORIZATION_NOT_EXECUTED;
    }

    public String getREGISTRATION_NOT_EXECUTED() {
        return REGISTRATION_NOT_EXECUTED;
    }

    public String getFIELD_IS_NULL() {
        return FIELD_IS_NULL;
    }

    public String getINCORRECT_TYPE_OF_DATA_PASSWORD() {
        return INCORRECT_TYPE_OF_DATA_PASSWORD;
    }

    public String getENTER_EMAIL() {
        return ENTER_EMAIL;
    }

    public String getENTER_CHANGE_USER_EMAIL() {
        return ENTER_CHANGE_USER_EMAIL;
    }

    public String getUSER_NOT_FOUND() {
        return USER_NOT_FOUND;
    }

    public String getINCORRECT_TYPE_OF_DATA() {
        return INCORRECT_TYPE_OF_DATA;
    }

    public String getPATH_TO_THE_XML_FILES() {
        return PATH_TO_THE_XML_FILES;
    }
}
