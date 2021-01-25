package com.thewickettk.creativecore.mysql;

import com.thewickettk.creativecore.Main;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlMain {
    private static mysqlMain instance = new mysqlMain();
    public static mysqlMain getInstance() {
        return instance;
    }
    public static java.sql.Connection connection;



    public void mysqlConnect(){
        try {
            synchronized(this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }
            }

                Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ Main.config.getString("mysql.host")+":"+ Main.config.getString("mysql.port") +"/"+ Main.config.getString("mysql.database")+"?autoReconnect=true", Main.config.getString("mysql.username"), Main.config.getString("mysql.password"));
        } catch (Exception e){
            System.err.println("(CreativeCore) Could not connect to database!");
            e.printStackTrace();
        }
    }

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }
}
