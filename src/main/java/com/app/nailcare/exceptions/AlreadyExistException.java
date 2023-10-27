package com.app.nailcare.exceptions;

/**
 * An exception thrown to indicate that an entity of a specific class already exists with a given attribute and value.
 */
public class AlreadyExistException extends RuntimeException {

    /**
     * Constructs an AlreadyExistException with information about the existing entity.
     *
     * @param clazz The class of the entity that already exists.
     * @param attribute The attribute (field) of the entity with the duplicate value.
     * @param value The value that is already associated with the given attribute.
     */
    public <T> AlreadyExistException(Class<T> clazz, String attribute, String value){
        super(clazz.getSimpleName() + " already exists with " + attribute + ": " + value);
    }
}
