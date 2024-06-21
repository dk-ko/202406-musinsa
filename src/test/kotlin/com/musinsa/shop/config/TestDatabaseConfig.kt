package com.musinsa.shop.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@TestConfiguration
class TestDatabaseConfig {
    @Bean
    @Primary
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.h2.Driver")
        dataSource.url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
        dataSource.username = "sa"
        dataSource.password = ""
        return dataSource
    }
}