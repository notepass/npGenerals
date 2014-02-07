package de.notepass.general.V1R1.Cache;

/**
 * <p>This Exception will be thrown, if the index wanted to use by the cache is already taken</p>
 */
public class DuplicateIndexException extends Exception {
    /**
     * <p>This Exception will be thrown, if the index wanted to use by the cache is already taken</p>
     */
    public DuplicateIndexException() {
        super("The requested index was already taken");
    }

    /**
     * <p>This Exception will be thrown, if the index wanted to use by the cache is already taken</p>
     *
     * @param message Message to show
     */
    public DuplicateIndexException(String message) {
        super(message);
    }
}
