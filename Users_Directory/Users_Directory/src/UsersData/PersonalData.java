package UsersData;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by 1231 on 26.03.2015.
 */
public class PersonalData implements Serializable {

    private String name;
    private String surname;
    private Date birthday;
    private FamilyStatus familyStatus;
    private String profession;
    private String email;

    public PersonalData(String name, String surname, Date birthday, FamilyStatus familyStatus,String profession,String Email ) {
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setFamilyStatus(familyStatus);
        this.setProfession(profession);
        this.setEmail(Email);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public FamilyStatus getFamilyStatus() {
        return this.familyStatus;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
