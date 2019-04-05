package com.example.credsexampleclient

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner)
@SpringBootTest(properties = ["spring.main.allow-bean-definition-overriding=true"])
@ContextConfiguration(classes = [ClientApplication, TestConfig])
class ClientApplicationTests {

  @Test
  void contextLoads() {
  }

  static class TestConfig {

    @Bean
    RestTemplate restTemplate() {
      return new RestTemplateBuilder().build()
    }
  }
}
