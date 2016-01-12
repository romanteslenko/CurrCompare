package com.teslenko.currcomp.dao.db;

/**
 * Handle database driver initialization.
 */
public class JbdcDriverInitializer {
    /**
     * If value=false it means the database driver wasn't initialized.
     */
    private static boolean initialized;

    /**
     * This method is designed for certain DB driver initialization.
     * First of all it checks if driver wasn't already initialized
     * and then load driver to memory using reflection API.
     * Finally method changes initialization status.
     * @param driverClass the name of class which provides
     *                    opportunity to work with specific database
     */
    public static synchronized void initDriver(String driverClass) {
        if (!initialized) {
            try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                System.out.println("DB driver wasn't found");
                e.printStackTrace();
            }
        }
        initialized = true;
    }
}
