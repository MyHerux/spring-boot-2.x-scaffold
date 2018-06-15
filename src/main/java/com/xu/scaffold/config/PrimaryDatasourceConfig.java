package com.xu.scaffold.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = PrimaryDatasourceConfig.PACKAGE,sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDatasourceConfig {

    static final String PACKAGE = "com.xu.scaffold.repository.primary";

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "hikari.primary")
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }
}
