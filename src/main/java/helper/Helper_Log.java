package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Helper_Log {

    private static final Logger Log = LogManager.getLogger(Helper_Log.class);

    public static void add_debug(String message) {
        Log.debug(message);
        System.out.println("[DEBUG] " + message);
    }

    public static void add_info(String message) {
        Log.info(message);
        System.out.println("[INFO] " + message);
    }

    public static void add_warn(String message) {
        Log.warn(message);
        System.out.println("[WARN] " + message);
    }

    public static void add_error(String message) {
        Log.error(message);
        System.err.println("[ERROR] " + message);
    }

    public static void add_fatal(String message) {
        Log.fatal(message);
        System.err.println("[FATAL] " + message);
    }
}
