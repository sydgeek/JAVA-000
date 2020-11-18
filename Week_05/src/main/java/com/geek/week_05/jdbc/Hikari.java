package com.geek.week_05.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hikari {
    public static void main(String[] args) throws Exception {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3308/test");
        config.setUsername("root");
        config.setPassword("root");
        config.setMinimumIdle(10);
        config.setMaximumPoolSize(10);
        config.setPoolName("pool");
        HikariPool pool = new HikariPool(config);

        // 查询
        String query = "select * from user";
        PreparedStatement queryStatement = pool.getConnection().prepareStatement(query);
        ResultSet resultSet = queryStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("USER_ID");
            String name = resultSet.getString("USER_NAME");
            System.out.println("USER_ID:" + id + ",USER_NAME:" + name);
        }

        // 新增一条记录
        String insert = "insert into user (USER_ID, USER_NAME, PASSWORD, REAL_NAME) VALUES ('2', 'floppyfish', '123456', 'syd')";
        PreparedStatement insertStatement = pool.getConnection().prepareStatement(insert);
        insertStatement.execute();

        // 修改一条数据
        String update = "update user set PASSWORD = '16345' where USER_NAME = 'floppyfish'";
        PreparedStatement updateStatement = pool.getConnection().prepareStatement(update);
        updateStatement.execute();

        // 删除一条数据
        String delete = "delete from user where USER_NAME = 'floppyfish'";
        PreparedStatement deleteStatement = pool.getConnection().prepareStatement(delete);
        deleteStatement.executeUpdate();

        pool.shutdown();
    }
}
