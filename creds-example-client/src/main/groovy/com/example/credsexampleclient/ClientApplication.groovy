package com.example.credsexampleclient

import groovy.util.logging.Slf4j
import org.apache.commons.codec.Charsets
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.web.client.RestTemplate

import java.nio.charset.Charset

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

    String mode = System.getenv("MODE") ?: "DEMO"

    String response

    switch (mode) {
      case "DEMO":
        response = restTemplate().getForObject(serverBaseUrl + "/mod", String)
        break
      default:
        response = restTemplate().getForObject(serverBaseUrl + "/mod", String)
        break
    }
    log.info("Response: {}", response)
  }

  private String toString(InputStream inputStream, Charset charset = Charsets.UTF_8) throws IOException {

    Scanner scanner = new Scanner(inputStream, charset.name())
    return scanner.useDelimiter("\\A").next()
  }
}
