package beans.backingbeans;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String course;

    private String user_gender;


    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    ///// multipul check boxes code///////
    //start//
    private List<String> val;
    @PostConstruct
    public void init()
    {
        val=new ArrayList<String>();
    }
    public List<String> getVal() {
        return val;
    }
    public void setVal(List<String> val) {
        this.val = val;
    }
    //End//
    ///// multipul check boxes code///////
    //........................................................................./
    ///// Dropdown menu bar code///////
    ///start///
    public String AGE;
    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }
    ///End///
    ///// Dropdown menu bar code///////
    //......................................................................../

}
