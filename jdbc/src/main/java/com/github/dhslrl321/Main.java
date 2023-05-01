package com.github.dhslrl321;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection connection = Connections.getConnection();
        PreparedStatement ps2 = connection.prepareStatement("insert into member values (?, ?)");
        ps2.setInt(1, 1);
        ps2.setString(2, "jang");
        ps2.executeUpdate();

        PreparedStatement statement = connection.prepareStatement("select count(*) from member;");

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            System.out.println("행의 갯수 : "+resultSet.getInt("count(*)"));
        }
        System.out.println("resultSet = " + resultSet);
    }
}
