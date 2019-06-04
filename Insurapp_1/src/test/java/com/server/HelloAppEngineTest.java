package com.server;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class HelloAppEngineTest.
 */
public class HelloAppEngineTest {

  /**
   * Test.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void test() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new HelloAppEngine().doGet(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
}
