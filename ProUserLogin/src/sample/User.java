package sample;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty ID;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public User(String id, String uname, String pass) {
        this.ID = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(uname);
        this.password = new SimpleStringProperty(pass);
    }

    public String getID() {
        return ID.get();
    }
    public void setId(String id){
        ID.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String uname) {
       username.set(uname);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String pass) {
        password.set(pass);
    }
}
