package com.example.credsexampleclient

import org.apache.commons.codec.Charsets

import java.nio.charset.Charset

class IOUtils {

  static String streamToString(InputStream inputStream, Charset charset = Charsets.UTF_8) throws IOException {

    Scanner scanner = new Scanner(inputStream, charset.name())
    return scanner.useDelimiter("\\A").next()
  }
}
