package com.mycompany.puppet.colector;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {

    private final BasicDataSource dataSource;

    public Connection() {
        dataSource = new BasicDataSource();

//        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource​.setUrl("jdbc:mysql://localhost:3306/puppet");
//        dataSource​.setUsername("jarPuppet");
//        dataSource​.setPassword("urubu100");
        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource​.setUrl("jdbc:sqlserver://svr-puppet.database.windows.net:1433;database=Puppet;user=admin-puppet@svr-puppet;password=2adsb#grupo8;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername("admin-puppet");
        dataSource.setPassword("2ads#grupo8");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}