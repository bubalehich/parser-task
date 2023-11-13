package ru.clevertec.parser;

public interface Parser<T, R> {
    /**
     * Convert an object to R type
     *
     * @param object
     *
     * @return converted output
     */
    R fromObject(T object);

    /**
     * Convert source to T type object
     *
     * @param source which shall be converted
     * @param rootType as a conversion type
     *
     * @return an object with T type
     */
    T toObject(R source, Class<T> rootType);
}
