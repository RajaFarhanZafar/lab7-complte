package beans.managedbeans;


import beans.backingbeans.User;
import database.DbConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@ManagedBean
public class MyBean {
    private User user;
    private DbConnection dbConnection;
    private boolean existing;

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
        AddFormData();

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
    public String list2String(List<String> list) {
        String str = null;
        str = list.toString();
        return str;
    }
    public String AddFormData()
    {
        System.out.println("gender is " +user.getUser_gender());
        System.out.println("color is " +user.getUser_color());
        dbConnection.AddData(user.getName(),user.getUser_gender(),user.getUser_color(),"14");
        return null;
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

