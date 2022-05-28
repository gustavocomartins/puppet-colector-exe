package com.mycompany.puppet.colector;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {

    private BasicDataSource dataSource;

    public Connection(String banco) {

        if (banco.equals("Mysql")) {
            dataSource = new BasicDataSource();
            dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://172.17.0.2:3306/puppetCollectorBackup?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10");
//            dataSource​.setUrl("jdbc:mysql://localhost:3306/puppetCollectorBackup?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC");
            dataSource​.setUsername("root");
            dataSource​.setPassword("urubu100");
        } else if (banco.equals("Azure")) {
            dataSource = new BasicDataSource();
            dataSource​.setDriverClassName(
                    "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource​.setUrl(
                    "jdbc:sqlserver://svr-puppet.database.windows.net:1433;database=Puppet;user=admin-puppet@svr-puppet;password=2adsb#grupo8;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            dataSource.setUsername(
                    "admin-puppet");
            dataSource.setPassword(
                    "2ads#grupo8");
        }
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
