package beans.managedbeans;


import beans.backingbeans.User;
import database.DbConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.List;


@ManagedBean
public class MyBean {
    private User user;
    private DbConnection dbConnection;
    private boolean existing;
    private String user_gender;
    private List<String> user_color;

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public MyBean() {

        user=new User();
        dbConnection=new DbConnection();
    }
    public String registerAccount()
    {
        System.out.println("name is " +user.getName());
        System.out.println("email is " +user.getEmail());
        System.out.println("password is " +user.getPassword());
        dbConnection.insertRecord(user.getName(),user.getEmail(),user.getPassword());

        return null;
    }
    public String loginAccount()
{
        ResultSet resultSet= dbConnection.loginUser(user.getEmail(),user.getPassword());
        if(resultSet!=null)
        {
            return null;
        }
        HttpSession session= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("uemail",user.getEmail());
        session.setMaxInactiveInterval(15*60);
        return "auth/userpage.xhtml?faces-redirect=true";
    }
    public boolean isLoggedIn()
    {
        HttpSession session= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session!=null) {
            String username=(String) session.getAttribute("uemail");
            if(username!=null)
            {
             return true;
            }
        }
        return false;
    }
    public String logoutUser()
    {
        HttpSession session= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session!=null)
        {
            session.invalidate();
        }
        return "/index.xhtml?faces-redirect=true";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
