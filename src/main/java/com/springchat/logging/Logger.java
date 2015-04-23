package com.springchat.logging;

/**
 *
 * @author 984351
 */
public class Logger implements ILogger {

    @Override
    public void log(String logstring) {
        java.util.logging.Logger.getLogger("springchatLogger").info(logstring);
    }

}
