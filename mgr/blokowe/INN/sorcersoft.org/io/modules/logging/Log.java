package sorcer.core.util;

import java.util.logging.Logger;

/**
 * Generic Sorcer Logging Utility.
 * <p>
 * Please use only these logging facilities in your providers.
 * <p>
 * Here is an example: <br>
 * <code>Log.getProviderLog().info("some informal message");</code><br>
 * <code>Log.getProviderLog().severe(jgapp.util.Debug.stackTraceToString(ex));</code><br>
 * <p>
 * The loggers can be configured via the standard java logger configuration
 * interface, if the system property <code>java.util.logging.config.file</code>
 * is set. It should be set to <code>${IGRID_HOME}/configs/sorcer.logging</code>.
 * This already has a good example configuration
 * 
 * You should generally consider using a logger for each your class, following 
 * the standard of setting the logger name to be the same as the fully qulified 
 * name of the class. This allows for finer-grained control of logging due to 
 * ability to turn logging on and off based on namespaces. The Log class predefines
 * the top-level namespace for SORCER.
 * 
 * @author Mike Sobolewski
 * @author Max Berger
 * @version $Id: Log.java,v 1.8 2006/04/16 03:59:30 sobol Exp $
 * @see java.util.logging.Logger
 * @see jgapp.util.Debug#stackTraceToString(Throwable)
 */

public class Log {

    /** Our main sorcer logger */
    private static Logger sorcer = Logger.getLogger("sorcer");

    /** Suns service starter logger */
    private static Logger starter = Logger
            .getLogger("com.sun.jini.start.service.starter");

    /** Our provider logger */
    private static Logger provider = Logger
            .getLogger("sorcer.core.provider");

    /** Logger solely for testing purposes */
    private static Logger test = Logger.getLogger("sorcer.test");

    /** Everythign that doesn't fit anywhere else */
    private static Logger random = Logger.getLogger("sorcer.random");

    /**
     * Is automatically called from the logging framework using 
     * the config property in the "sorcer.logging" confifuration file.
     */
    public Log() {
        /* Set additional Formatters, Filters and Handlers for
    	the loggers here. You cannot specify the Handlers for 
    	loggers except the root logger from the sorcer.logging 
    	configuration file.
    	*/
    }

    /**
     * This can be used to test the logging configuration. Call this class with
     * your logging config file as system parameter: <br>
     * <code>-Djava.util.logging.config.file=sorcer.logging</code><br>
     * and it will try all loggers and send all kinds of messages.
     * 
     * @param args
     */
    public static void main(String[] args) {
        sendLogMessages(sorcer);
        sendLogMessages(starter);
        sendLogMessages(provider);
        sendLogMessages(test);
        sendLogMessages(random);
    }

    /**
     * Sends a "Hello world" message for this logger. Only used for testing from
     * main function.
     * 
     * @param logger
     */
    private static void sendLogMessages(Logger logger) {
        System.out.println(" Logger Name : " + logger.getName() + " Level: "
                + logger.getLevel());
        logger.finest("Finest");
        logger.finer("Finer");
        logger.fine("Fine");
        logger.config("Config");
        logger.info("Info");
        logger.warning("Warning");
        logger.severe("Severe");
    }

    /**
     * This logger should be used in any service providers for logging.
     * 
     * @return Returns the provider logger.
     */
    public static Logger getProviderLog() {
        return provider;
    }

    /**
     * This logger should be used for everything that does not fit into other
     * loggers.
     * 
     * @return Returns the random logger.
     */
    public static Logger getRandomLog() {
        return random;
    }

    /**
     * This logger should be used for all sorcer.* packages except providers.
     * 
     * @return Returns the main sorcer logger.
     */
    public static Logger getSorcerLog() {
        return sorcer;
    }

    /**
     * This is the Jini 2 Starter logger. It may be used by sorcer service
     * starter as well.
     * 
     * @return Returns the jini service starter logger.
     */
    public static Logger getStarterLog() {
        return starter;
    }

    /**
     * This logger is for testing purposed. Add your extensive debug messages
     * here.
     * 
     * @return Returns the test logger.
     */
    public static Logger getTestLog() {
        return test;
    }
}
