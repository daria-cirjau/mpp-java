package eu.ase.jdbc_sqlite;

import java.sql.*;

public class SQLiteJDBC {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            createDBTable(c);
            insertData(c);
            selectData(c);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createDBTable(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        String sqlDropTable = "drop table if exists COMPANY";
        String sqlCreateTable = "create table COMPANY (ID INT PRIMARY KEY NOT NULL," +
                " NAME TEXT NOT NULL, AGE INT, ADDRESS CHAR(50), SALARY REAL)";
        stmt.executeUpdate(sqlDropTable);
        stmt.executeUpdate(sqlCreateTable);

        stmt.close();
        c.commit();
    }

    public static void insertData(Connection c) throws SQLException {
        Statement stmt = null;
        String sql = null;

        stmt = c.createStatement();
        sql = "insert into COMPANY(ID, NAME, AGE, ADDRESS, SALARY) values (1, 'Paul', 32, 'B', 21000)";
        stmt.executeUpdate(sql);
        stmt.close();

        PreparedStatement ps = c.prepareStatement("insert into COMPANY(ID, NAME, AGE, ADDRESS, SALARY) values (?, ?, ?, ?, ?)");
        ps.setInt(1, 2);
        ps.setString(2, "John");
        ps.setInt(3, 23);
        ps.setString(4, "BV");
        ps.setFloat(5, 15000);
        ps.executeUpdate();

        ps.setInt(1, 3);
        ps.setString(2, "David");
        ps.setInt(3, 25);
        ps.setString(4, "IS");
        ps.setFloat(5, 17000);
        ps.executeUpdate();

        ps.close();
        c.commit();
    }

    public static void selectData(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        String sqlSel = "select * from COMPANY where AGE < 30";
        ResultSet rs = stmt.executeQuery(sqlSel);

        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String address = rs.getString("address");
            float salary = rs.getFloat("salary");

            System.out.printf("\nID = %d, name = %s, age = %s, address = %s, salary = %f", id, name, age, address, salary);
        }
        rs.close();
        stmt.close();
    }
}
