package com.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving objectifyWeb events.
 * The class that is interested in processing a objectifyWeb
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addObjectifyWebListener<code> method. When
 * the objectifyWeb event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ObjectifyWebEvent
 */
@WebListener
public class ObjectifyWebListener implements ServletContextListener {

  /**
   * Context initialized.
   *
   * @param event the event
   */
  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.init();
    // Here is also a great place to register your POJO entity classes.
    // ObjectifyService.register(YourEntity.class);
  }

  /**
   * Context destroyed.
   *
   * @param event the event
   */
  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }
}