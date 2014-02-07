package de.notepass.general.V1R1.Cache;


/**
 * <p>This is the Global incarnation of the cache. You can get and set values here and pass them around classes</p>
 */
public class GlobalCache {
    /**
     * <p>The used Cache-Object which is from now on globally aviable</p>
     */
    private static DynamicCache cache = new DynamicCache();

    /**
     * <p>Returns the global cache-object</p>
     *
     * @return DynamicCache - The cache
     */
    public static DynamicCache getCache() {
        return cache;
    }
}
