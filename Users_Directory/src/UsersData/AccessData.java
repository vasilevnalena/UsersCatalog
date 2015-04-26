package UsersData;

import java.io.Serializable;

/**
 * Created by 1231 on 26.03.2015.
 */
public class AccessData implements Serializable {
    private String login;
    private int password;

    public AccessData(String login, int password) {
        this.setLogin(login);
        this.setPassword(password);
    }
    public AccessData() {
    }

    public String getLogin() {
        return this.login;
    }
    public int getPassword() {
        return this.password;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(int password) {
        this.password = password;
    }
}
