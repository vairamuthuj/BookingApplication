package com.book;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	/*@Bean(name = "mySqlDataSource")
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/application");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("admin");
        return dataSourceBuilder.build();
	}
	*/
}
