package com.example.credsexampleclient

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.SecureRandom
import java.security.cert.X509Certificate

class InsecureHttpsUrlConnection {

  static void allowInsecureHttpsUrlConnections() {
//    final Properties props = System.getProperties()
//    props.setProperty("jdk.internal.httpclient.disableHostnameVerification", Boolean.TRUE.toString())

    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

      @Override
      boolean verify(String s, SSLSession sslSession) {
        return true
      }
    })
    TrustManager[] trustAllCerts = [new X509TrustManager() {

      X509Certificate[] getAcceptedIssuers() {
        return null
      }

      void checkClientTrusted(
          X509Certificate[] certs, String authType) {
      }

      void checkServerTrusted(
          X509Certificate[] certs, String authType) {
      }
    }]
    try {
      SSLContext sc = SSLContext.getInstance("SSL")
      sc.init(null, trustAllCerts, new SecureRandom())
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
    }
    catch (Exception ignored) {
    }
  }
}
