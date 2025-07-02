package com.diworksdev.webproj2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.webproj2.util.DBConnector;

public class HelloStrutsDAO {

    public boolean select() {
        boolean ret = false;
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();

        // null チェックを追加して安全に処理
        if (con == null) {
            System.out.println(" DB接続に失敗しました（null connection）");
            return false;
        }

        String sql = "SELECT * FROM users";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLエラー: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}