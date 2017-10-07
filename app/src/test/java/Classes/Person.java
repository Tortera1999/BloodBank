package Classes;

/**
 * Created by default on 10/7/2017.
 */

public class Person {
    //Declaring class variables
    private String Name;
    private BloodType BType;
    private String Email;

    public Person (String name, BloodType btype, String email) {
        this.Name = name;
        this.BType = btype;
        this.Email = email;
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
