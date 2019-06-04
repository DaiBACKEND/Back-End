package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * This mock class is created to enable basic unit testing of the
 * {@link HelloAppEngine} class. Only methods used in the unit test
 * have a non-trivial implementation.
 * 
 * Feel free to change this class or replace it using other ways for testing
 * {@link HttpServlet}s, e.g. Spring MVC Test or Mockito to suit your needs.
 */
public class MockHttpServletResponse implements HttpServletResponse {

  /** The content type. */
  private String contentType;
  
  /** The encoding. */
  private String encoding;
  
  /** The writer content. */
  private StringWriter writerContent = new StringWriter();
  
  /** The writer. */
  private PrintWriter writer = new PrintWriter(writerContent);

  /**
   * Sets the content type.
   *
   * @param contentType the new content type
   */
  @Override
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * Gets the content type.
   *
   * @return the content type
   */
  @Override
  public String getContentType() {
    return contentType;
  }

  /**
   * Gets the writer.
   *
   * @return the writer
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public PrintWriter getWriter() throws IOException {
    return writer;
  }

  /**
   * Gets the writer content.
   *
   * @return the writer content
   */
  public StringWriter getWriterContent() {
    return writerContent;
  }

  // anything below is the default generated implementation

  /**
   * Flush buffer.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public void flushBuffer() throws IOException {
  }

  /**
   * Gets the buffer size.
   *
   * @return the buffer size
   */
  @Override
  public int getBufferSize() {
    return 0;
  }

  /**
   * Gets the character encoding.
   *
   * @return the character encoding
   */
  @Override
  public String getCharacterEncoding() {
    return encoding;
  }

  /**
   * Gets the locale.
   *
   * @return the locale
   */
  @Override
  public Locale getLocale() {
    return null;
  }

  /**
   * Gets the output stream.
   *
   * @return the output stream
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return null;
  }

  /**
   * Checks if is committed.
   *
   * @return true, if is committed
   */
  @Override
  public boolean isCommitted() {
    return false;
  }

  /**
   * Reset.
   */
  @Override
  public void reset() {
  }

  /**
   * Reset buffer.
   */
  @Override
  public void resetBuffer() {
  }

  /**
   * Sets the buffer size.
   *
   * @param arg0 the new buffer size
   */
  @Override
  public void setBufferSize(int arg0) {
  }

  /**
   * Sets the character encoding.
   *
   * @param encoding the new character encoding
   */
  @Override
  public void setCharacterEncoding(String encoding) {
    this.encoding = encoding;
  }

  /**
   * Sets the content length.
   *
   * @param arg0 the new content length
   */
  @Override
  public void setContentLength(int arg0) {
  }

  /**
   * Sets the locale.
   *
   * @param arg0 the new locale
   */
  @Override
  public void setLocale(Locale arg0) {
  }

  /**
   * Adds the cookie.
   *
   * @param arg0 the arg 0
   */
  @Override
  public void addCookie(Cookie arg0) {
  }

  /**
   * Adds the date header.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void addDateHeader(String arg0, long arg1) {
  }

  /**
   * Adds the header.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void addHeader(String arg0, String arg1) {
  }

  /**
   * Adds the int header.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void addIntHeader(String arg0, int arg1) {
  }

  /**
   * Contains header.
   *
   * @param arg0 the arg 0
   * @return true, if successful
   */
  @Override
  public boolean containsHeader(String arg0) {
    return false;
  }

  /**
   * Encode redirect URL.
   *
   * @param arg0 the arg 0
   * @return the string
   */
  @Override
  public String encodeRedirectURL(String arg0) {
    return null;
  }

  /**
   * Encode redirect url.
   *
   * @param arg0 the arg 0
   * @return the string
   */
  @Override
  public String encodeRedirectUrl(String arg0) {
    return null;
  }

  /**
   * Encode URL.
   *
   * @param arg0 the arg 0
   * @return the string
   */
  @Override
  public String encodeURL(String arg0) {
    return null;
  }

  /**
   * Encode url.
   *
   * @param arg0 the arg 0
   * @return the string
   */
  @Override
  public String encodeUrl(String arg0) {
    return null;
  }

  /**
   * Send error.
   *
   * @param arg0 the arg 0
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public void sendError(int arg0) throws IOException {
  }

  /**
   * Send error.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public void sendError(int arg0, String arg1) throws IOException {
  }

  /**
   * Send redirect.
   *
   * @param arg0 the arg 0
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public void sendRedirect(String arg0) throws IOException {
  }

  /**
   * Sets the date header.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void setDateHeader(String arg0, long arg1) {
  }

  /**
   * Sets the header.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void setHeader(String arg0, String arg1) {
  }

  /**
   * Sets the int header.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void setIntHeader(String arg0, int arg1) {
  }

  /**
   * Sets the status.
   *
   * @param arg0 the new status
   */
  @Override
  public void setStatus(int arg0) {
  }

  /**
   * Sets the status.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   */
  @Override
  public void setStatus(int arg0, String arg1) {
  }

  /**
   * Sets the content length long.
   *
   * @param length the new content length long
   */
  // Servlet API 3.0 and 3.1 methods
  public void setContentLengthLong(long length) {
  }

  /**
   * Gets the status.
   *
   * @return the status
   */
  public int getStatus() {
    return 0;
  }

  /**
   * Gets the header.
   *
   * @param name the name
   * @return the header
   */
  public String getHeader(String name) {
    return null;
  }

  /**
   * Gets the headers.
   *
   * @param name the name
   * @return the headers
   */
  public Collection<String> getHeaders(String name) {
    return null;
  }

  /**
   * Gets the header names.
   *
   * @return the header names
   */
  public Collection<String> getHeaderNames() {
    return null;
  }
}
