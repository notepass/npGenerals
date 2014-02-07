package de.notepass.general.V1R1.configuration;

import java.io.File;

/**
 * <p>This class contains the default Internal Configuration.
 * If you can't change something in the config-file you can hopefully here</p>
 */
public class InternalConfigDummy {
    //Folder Configuration
    /**
     * Root-Folder in which all data will be written
     */
    final public static File ROOT_FOLDER = new File(".npData");
    /**
     * Folder for configuration scripts. Will inherit from {@link de.notepass.general.V1R1.configuration.InternalConfigDummy#ROOT_FOLDER}.
     */
    final public static File CONFIG_ROOT = new File(ROOT_FOLDER.getAbsolutePath() + "/conf");
    /**
     * Root-Folder for language files.
     */
    final public static File LANG_ROOT = new File(ROOT_FOLDER.getAbsoluteFile() + "/language");

    //Properties Configuration
    /**
     * <p>Configuration File</p>
     */
    final public static File CONFIG_FILE = new File(CONFIG_ROOT + "/configuration.properties");
    /**
     * <p>The prefix for the log-properties</p>
     */
    final public static String CONFIG_LOG_PREFIX = "npGenerals.log.";
    /**
     * <p>The prefix for the main-properties</p>
     */
    final public static String CONFIG_MAIN_PREFIX = "npGenerals.main.";
    /**
     * <p>Config-properties for path of the log-file</p>
     */
    final public static String CONFIG_LOG_FILEPATH = "path";
    /**
     * <p>Config-properties for boolean value to decide if debug-messages should be logged</p>
     */
    final public static String CONFIG_LOG_LOGDEBUG = "debug";
    /**
     * <p>Config-properties for boolean value to decide if warning-messages should be logged</p>
     */
    final public static String CONFIG_LOG_LOGWARN = "warn";
    /**
     * <p>Config-properties for boolean value to decide if info-messages should be logged</p>
     */
    final public static String CONFIG_LOG_LOGINFO = "info";
    /**
     * <p>Config-properties for boolean value to decide if error-messages should be logged</p>
     */
    final public static String CONFIG_LOG_LOGERROR = "error";
    /**
     * <p>Config-properties for the debug-log-prefix</p>
     */
    final public static String CONFIG_LOG_DEBUGTEXT = "debugText";
    /**
     * <p>Config-properties for the warning-log-prefix</p>
     */
    final public static String CONFIG_LOG_WARNTEXT = "warnText";
    /**
     * <p>Config-properties for the info-log-prefix</p>
     */
    final public static String CONFIG_LOG_INFOTEXT = "infoText";
    /**
     * <p>Config-properties for the error-log-prefix</p>
     */
    final public static String CONFIG_LOG_ERRORTEXT = "errorText";
    /**
     * <p>Config-properties for the date/time format</p>
     */
    final public static String CONFIG_LOG_DATETIMEFORMAT = "dateTimeFormat";
    /**
     * <p>Config-properties for the date/time prefix</p>
     */
    final public static String CONFIG_LOG_DATETIMEPREFIX = "dateTimePrefix";
    /**
     * <p>Config-properties for the date/time suffix</p>
     */
    final public static String CONFIG_LOG_DATETIMESUFFIX = "dateTimeSuffix";
    /**
     * <p>Config-properties for the path of the language file</p>
     */
    final public static String CONFIG_MAIN_LANGFILE = "langFile";
}
