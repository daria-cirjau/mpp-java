package eu.ase.sqldao;

import java.io.File;
import java.sql.*;

public class SqlDAO {
    private Connection sqlConn;
    private static SqlDAO currentInstance;

    private SqlDAO() {
        boolean cdb = false;
        File f = new File("./users.db");
        if(!f.exists()) {
            cdb = true;
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            sqlConn = DriverManager.getConnection("jdbc:sqlite:users.db");
            sqlConn.setAutoCommit(false);

            if(cdb) {
                createDBTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDBTable() throws SQLException {
        Statement statement = sqlConn.createStatement();
        String sqlCreateTable = "create table USERS (ID INT PRIMARY KEY NOT NULL," +
                " NAME TEXT NOT NULL, EMAIL CHAR(50), PASSWORD TEXT NOT NULL)";
        statement.executeUpdate(sqlCreateTable);
        statement.close();
        sqlConn.commit();
    }

    public void insertIntoDB(int id, String name, String email, String password) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            if (sqlConn != null) {
                PreparedStatement ps = sqlConn.prepareStatement("insert into USERS(ID, NAME, EMAIL, PASSWORD) values (?, ?, ?, ?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, password);

                ps.executeUpdate();

                ps.close();
                sqlConn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayDB() {
        System.out.println("Display the db: ");
        try {
            Statement stm = sqlConn.createStatement();
            String sqlSel = "select * from USERS;";
            ResultSet rs = stm.executeQuery(sqlSel);

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");

                System.out.printf("\nID = %d, Name = %s, Email = %s, Password = %s", id, name, email, password);
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized SqlDAO getInstance() {
        if(currentInstance == null) {
            currentInstance = new SqlDAO();
        }
        return currentInstance;
    }
}
