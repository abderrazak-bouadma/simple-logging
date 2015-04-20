package com.cdcfast;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Abderrazak BOUADMA
 * Date: 4/20/15
 * Time: 3:59 PM
 */
public class SimpleLoggingTest {


    @Test
    public void testSimpleLogging() {

        FileAppender file = new FileAppender();
        file.setFile("/tmp/mylogs.log");
        file.setName("LOGGER_FILE");
        final String PATTERN = "%d [%p|%c|%C{1}] %m%n";
        file.setLayout(new PatternLayout(PATTERN));
        file.setThreshold(Level.DEBUG);
        file.activateOptions();
        file.setAppend(true);

        file.addFilter(new Filter() {
            @Override
            public int decide(LoggingEvent event) {
                return event.getMessage().toString().contains("Hello") ? -1 : 1;
            }
        });

        Logger.getRootLogger().addAppender(file);
        final Logger logger = Logger.getLogger(SimpleLoggingTest.class);
        logger.debug("Hello there !");
        logger.debug("Bye there !");

    }
}
