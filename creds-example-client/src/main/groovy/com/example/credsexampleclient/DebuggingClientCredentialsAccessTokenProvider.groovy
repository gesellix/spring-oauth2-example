package com.example.credsexampleclient

import groovy.util.logging.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException
import org.springframework.security.oauth2.client.token.AccessTokenRequest
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RequestCallback

@Slf4j
class DebuggingClientCredentialsAccessTokenProvider extends ClientCredentialsAccessTokenProvider {

  @Override
  protected RequestCallback getRequestCallback(OAuth2ProtectedResourceDetails resource, MultiValueMap<String, String> form, HttpHeaders headers) {
    def callback = super.getRequestCallback(resource, form, headers)
    log.info("resource: ${resource}")
    log.info("resource.clientId: ${resource.clientId}")
    log.info("resource.clientAuthenticationScheme: ${resource.clientAuthenticationScheme}")
    log.info("resource.authenticationScheme: ${resource.authenticationScheme}")
    log.info("form: ${form}")
    log.info("headers: ${headers}")
    return callback
  }

  @Override
  OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details, AccessTokenRequest request) throws UserRedirectRequiredException, AccessDeniedException, OAuth2AccessDeniedException {
    def token = super.obtainAccessToken(details, request)
    log.info("token.value: ${token.value}")
    log.info("token.tokenType: ${token.tokenType}")
    return token
  }
}
