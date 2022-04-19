package com.mycompany.login.swing;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {
    
    private BasicDataSource dataSource;

    public Connection() {
        this.dataSource = new BasicDataSource();
        
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource​.setUrl("jdbc:mysql://localhost:3306/puppet");
        dataSource​.setUsername("jarPuppet");
        dataSource​.setPassword("urubu100");
        
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
