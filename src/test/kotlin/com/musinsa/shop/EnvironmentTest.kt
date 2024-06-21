package com.musinsa.shop

import com.musinsa.shop.common.AcceptanceTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import kotlin.test.Test

class EnvironmentTest: AcceptanceTest() {

    @Autowired
    lateinit var environment: Environment

    @Test
    fun `프로퍼티 출력 테스트`() {
        val dbUrl = environment.getProperty("spring.datasource.url")
        val dbUsername = environment.getProperty("spring.datasource.username")
        val dbPassword = environment.getProperty("spring.datasource.password")

        println("Database URL: $dbUrl")
        println("Database Username: $dbUsername")
        println("Database Password: $dbPassword")

        assertNotNull(dbUrl)
        assertNotNull(dbUsername)
        assertNotNull(dbPassword)
    }
}