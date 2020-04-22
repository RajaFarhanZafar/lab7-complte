package database;

import java.sql.*;

/**
 * Created by fawad.tariq on 9/26/2019.
 */
public class DbConnection {
    private String dbURL = "jdbc:mysql://localhost:3306/user";
    private String username = "root";
    private String password = "";
    private Connection connection;
    public DbConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL,username,password);
            if(connection!=null){
                System.out.println("Success");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(String name, String email,String password){
        try {
            String sqlQuery = "INSERT INTO users_table(`name`, `email`, `password`) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);


            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet loginUser(String email,String password){
        try {
            String sqlQuery = "SELECT * from users_table where email=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);


            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void AddData(String addBy, String gender,String color,String age) {
    try {
        String sqlQuery = "INSERT INTO users_detail(`addby`, `gender`, `course`, `age`) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQuery);
        preparedStatement1.setString(1,addBy);
        preparedStatement1.setString(2,gender);
        preparedStatement1.setString(3,color);
        preparedStatement1.setString(4,age);


        int noOfRowsInserted = preparedStatement1.executeUpdate();
        if(noOfRowsInserted>0){
            System.out.println(noOfRowsInserted + "  data added rows inserted!");
        }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public void updateRecord(int id,String firstName,String lastName,String email){
        try {
            String sqlQuery = "UPDATE student SET first_name=?,last_name=?,email=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,email);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id){
        try {
            String sqlQuery = "DELETE from student WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM student where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getRecords(){
        try {
            String sqlQuery = "SELECT * FROM users_detail";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlQuery);

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
