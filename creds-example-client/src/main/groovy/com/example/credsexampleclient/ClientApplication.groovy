package com.example.credsexampleclient

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.web.client.RestTemplate

@Slf4j
@SpringBootApplication
class ClientApplication implements CommandLineRunner {

  @Value("#{ @environment['example.baseUrl'] }")
  private String serverBaseUrl

  static void main(String[] args) {
    SpringApplication.run(ClientApplication, args)
  }

  @Bean
  @ConfigurationProperties("example.oauth2.client")
  protected ClientCredentialsResourceDetails oAuthDetails() {
    return new ClientCredentialsResourceDetails()
  }

  @Bean
  protected RestTemplate restTemplate() {
    return new OAuth2RestTemplate(oAuthDetails())
  }

  @Override
  void run(String... args) {
    String response = restTemplate().getForObject(serverBaseUrl + "/mod", String)
    log.info("MOD: {}", response)
  }
}
