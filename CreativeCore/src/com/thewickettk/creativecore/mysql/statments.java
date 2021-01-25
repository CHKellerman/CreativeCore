package com.thewickettk.creativecore.mysql;

import com.thewickettk.creativecore.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class statments {
    public static String sql;

    public static void createPlayerDataTable() {
        sql = "CREATE TABLE IF NOT EXISTS CreativeCore_Points ( `id` INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY, `Player` VARCHAR(16) NULL DEFAULT NULL , `Points` INT(20) NOT NULL DEFAULT '0')";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            if (Main.config.getBoolean("debug")) {
                e.printStackTrace();
            }
        }
    }
    public static void createMarryDataTable() {
        sql = "CREATE TABLE IF NOT EXISTS CreativeCore_Marry ( `id` INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,`Player` VARCHAR(16) NULL DEFAULT NULL,`isMarried` BOOLEAN NOT NULL DEFAULT FALSE ,`MarriedTo` VARCHAR(16) NULL DEFAULT NULL )";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            if (Main.config.getBoolean("debug")) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkPlayerExists(String username) {
        sql = "SELECT * FROM CreativeCore_Points WHERE Player='" + username + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                statement.close();
                rs.close();
                return true;
            }
            statement.close();
            rs.close();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static int getPlayerPoints(String username){
        sql = "SELECT * FROM CreativeCore_Points WHERE Player='" + username + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
               int ret = rs.getInt("Points");
                statement.close();
                rs.close();
                return ret;
            }
            statement.close();
            rs.close();
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void setPlayerPoints(String username,int ammount){
        sql = "UPDATE CreativeCore_Points SET Points='"+ ammount+ "' WHERE Player='" + username + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void createPlayer(String username ){
        sql = "INSERT INTO CreativeCore_Points (Player,Points) VALUES ('"+username +"',0)";
        try {
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createMarriedPlayer(String username ){
        sql = "INSERT INTO CreativeCore_Marry (Player) VALUES ('"+ username +"')";
        try {
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setMarriedTo(String Player,String Marriedto){
        sql = "UPDATE CreativeCore_Marry SET MarriedTo='"+ Marriedto + "' WHERE Player='" + Player + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void setisMarried(String Player,int bol){
        sql = "UPDATE CreativeCore_Marry SET isMarried='"+ bol + "' WHERE Player='" + Player + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean checkMarriedPlayerExists(String username) {
        sql = "SELECT * FROM CreativeCore_Marry WHERE Player='" + username + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                statement.close();
                rs.close();
                return true;
            }
            statement.close();
            rs.close();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getisMarried(String username) {
        sql = "SELECT * FROM CreativeCore_Marry WHERE Player='" + username + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Boolean ret = rs.getBoolean("isMarried");
                if (ret){
                    return true;
                }
            }
            statement.close();
            rs.close();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
//id Player GamesPlayed Wins Kills Deaths
    public static String getPlayerMarriedTo(String username){
        sql = "SELECT * FROM CreativeCore_Marry WHERE Player='" + username + "'";
        try{
            java.sql.PreparedStatement statement = mysqlMain.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String ret = rs.getString("MarriedTo");
                statement.close();
                rs.close();
                return ret;
            }
            statement.close();
            rs.close();
            return "";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

}

