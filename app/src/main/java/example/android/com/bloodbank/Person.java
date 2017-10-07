package example.android.com.bloodbank;

import java.io.Serializable;

/**
 * Created by default on 10/7/2017.
 */

public class Person implements Serializable {
    //Declaring class variables
    private String Name;
    private BloodType BType;
    private String Email;
    private String password;

    public Person(String name, BloodType btype, String email, String pass) {
        this.Name = name;
        this.BType = btype;
        this.Email = email;
        this.password = pass;
    }

    public Person(String email, String pass) {
        this.Email = email;
        this.password = pass;
    }
    public Person(BloodType btype, String name) {
        this.BType = btype;
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BloodType getBType() {
        return BType;
    }

    public void setBType(BloodType BType) {
        this.BType = BType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
