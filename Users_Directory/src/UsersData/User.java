package UsersData;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 1231 on 26.03.2015.
 */
public class User implements Serializable {
    private PersonalData personalData;
    private AccessData accessData;

    public User(String name, String surname, Date birthday,
                FamilyStatus familyStatus,String profession,String email) {
        personalData=new PersonalData(name,surname,birthday,familyStatus,profession,email);
        accessData = new AccessData();//логин и пароль - задаются после создания пользователя
    }

    public String getName(){
        return this.personalData.getName();
    }
    public String getSurname(){
        return this.personalData.getSurname();
    }
    public Date getBirthday(){
        return this.personalData.getBirthday();
    }
    public FamilyStatus getFamilyStatus(){
        return this.personalData.getFamilyStatus();
    }
    public String getProfessionUser(){
        return this.personalData.getProfession();
    }
    public String getEmail(){
        return this.personalData.getEmail();
    }
    public String getLogin(){
        return this.accessData.getLogin();
    }
    public int getPassword(){
        return this.accessData.getPassword();
    }

    public void setName(String name) {
        this.personalData.setName(name);
    }
    public void setSurname(String surname) {
        this.personalData.setSurname(surname);
    }
    public void setBirthday(Date birthday) {
        this.personalData.setBirthday(birthday);
    }
    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.personalData.setFamilyStatus(familyStatus);
    }
    public void setProfession(String profession){
        this.personalData.setProfession(profession);
    }
    public void setEmail(String email){
        this.personalData.setEmail(email);
    }
    public void setLogin(String login){
        this.accessData.setLogin(login);
    }
    public void setPassword(int password){
        this.accessData.setPassword(password);
    }

}