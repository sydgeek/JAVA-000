package com.geek.week_05.jdbc;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/test", "root", "root");

        // 查询
        String query = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String id = resultSet.getString("USER_ID");
            String name = resultSet.getString("USER_NAME");
            System.out.println("USER_ID:" + id + ",USER_NAME:" + name);
        }

        // 新增一条记录
        String insert = "insert into user (USER_ID, USER_NAME, PASSWORD, REAL_NAME) VALUES ('2', 'floppyfish', '123456', 'syd')";
        Statement insertStatement = connection.createStatement();
        insertStatement.execute(insert);

        // 修改一条数据
        String update = "update user set PASSWORD = '16345' where USER_NAME = 'floppyfish'";
        Statement updateStatement = connection.createStatement();
        updateStatement.execute(update);

        // 删除一条数据
        String delete = "delete from user where USER_NAME = 'floppyfish'";
        Statement deleteStatement = connection.createStatement();
        deleteStatement.executeUpdate(delete);

        connection.close();
    }
}
