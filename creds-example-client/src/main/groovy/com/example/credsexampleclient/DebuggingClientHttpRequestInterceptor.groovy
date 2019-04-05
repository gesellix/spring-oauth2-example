package com.example.credsexampleclient

import groovy.util.logging.Slf4j
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

@Slf4j
class DebuggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  @Override
  ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    log.info("req.headers: ${request.headers}")
    log.info("req.body: ${new String(body)}")
    def response = execution.execute(request, body)
    log.info("res.statusCode: ${response.statusCode}")
    log.info("res.headers: ${response.headers}")
//    log.info("res.body: ${IOUtils.streamToString(response.body)}")
    return response
  }
}
