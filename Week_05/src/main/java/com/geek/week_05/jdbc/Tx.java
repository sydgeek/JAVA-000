package com.geek.week_05.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Tx {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/test", "root", "root");
        connection.setAutoCommit(false);

        // 查询
        String query = "select * from user";
        PreparedStatement queryStatement = connection.prepareStatement(query);
        ResultSet resultSet = queryStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("USER_ID");
            String name = resultSet.getString("USER_NAME");
            System.out.println("USER_ID:" + id + ",USER_NAME:" + name);
        }

        // 新增一条记录
        String insert = "insert into user (USER_ID, USER_NAME, PASSWORD, REAL_NAME) VALUES ('2', 'floppyfish', '123456', 'syd')";
        PreparedStatement insertStatement = connection.prepareStatement(insert);
        insertStatement.execute();

        // 修改一条数据
        String update = "update user set PASSWORD = '16345' where USER_NAME = 'floppyfish'";
        PreparedStatement updateStatement = connection.prepareStatement(update);
        updateStatement.execute();

        // 删除一条数据
        String delete = "delete from user where USER_NAME = 'floppyfish'";
        PreparedStatement deleteStatement = connection.prepareStatement(delete);
        deleteStatement.executeUpdate();

        connection.commit();
        connection.close();
    }
}
