package beans.managedbeans;


import beans.backingbeans.User;
import database.DbConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;


@ManagedBean
public class MyBean {
    private User user;
    private DbConnection dbConnection;

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
        return "Userpage.xhtml?faces-redirect=true";
    }
    public boolean isloggedIn()
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
