package ru.clevertec.parser;

import ru.clevertec.exception.JsonParseException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


public class JsonParser implements Parser<Object, String> {
    private static final String FIELD_WRAP = "\"%s\":%s";

    @Override
    public String fromObject(Object object) {
        try {
            var result = parse(object);
            result.setLength(result.length() - 1);

            return result.toString();
        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public Object toObject(String source, Class<Object> rootType) {
        throw new UnsupportedOperationException();
        //TODO to be implemented
    }

    private StringBuilder parse(Object object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        StringBuilder innerBuilder = new StringBuilder();

        var objectType = Type.getType(object);

        switch (objectType) {
            case SIMPLE, SIMPLE2 -> builder.append(String.format(objectType.getFormat(), object));
            case DATE -> builder.append(String.format(objectType.getFormat(), object.toString()));
            case ARRAY -> {
                var length = Array.getLength(object);
                var array = new Object[length];
                for (int i = 0; i < length; i++) {
                    array[i] = Array.get(object, i);
                }

                for (var element : array) {
                    innerBuilder.append(parse(element));
                }
            }
            case OBJECT -> {
                Field[] fields = object.getClass().getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    innerBuilder.append(String.format(FIELD_WRAP, field.getName(), parse(field.get(object))));
                }
            }
            case COLLECTION -> {
                var collection = (Collection<Object>) object;

                for (Object element : collection) {
                    innerBuilder.append(parse(element));
                }
            }
            case MAP -> {
                var map = (Map<Object, Object>) object;

                for (var entry : map.entrySet()) {
                    var keyString = parse(entry.getKey()).toString().replace(",", "");
                    var valueString = parse(entry.getValue());

                    innerBuilder.append(String.format("%s:%s", keyString, valueString));
                }
            }
        }
        if (!innerBuilder.isEmpty()) {
            innerBuilder.setLength(innerBuilder.length() - 1);
            builder.append(String.format(objectType.getFormat(), innerBuilder));
        }

        return builder;
    }

    public enum Type {
        SIMPLE("%s,"),
        SIMPLE2("\"%s\","),
        ARRAY("[%s],"),
        COLLECTION("[%s],"),
        MAP("{%s},"),
        DATE("\"%s\","),
        OBJECT("{%s},");

        private final String format;

        Type(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }

        public boolean isSimple() {
            return this.equals(SIMPLE) || this.equals(SIMPLE2);
        }

        public static Type getType(Object object) {
            if (object == null || object instanceof Number || object instanceof Boolean) {
                return SIMPLE;
            } else if (object instanceof CharSequence || object instanceof Enum<?> || object instanceof Character) {
                return SIMPLE2;
            } else if (object.getClass().getName().charAt(0) == '[') {
                return ARRAY;
            } else if (object instanceof Collection<?>) {
                return COLLECTION;
            } else if (object instanceof TemporalAccessor || object instanceof Date) {
                return DATE;
            } else if (object instanceof Map<?, ?>) {
                return MAP;
            } else return OBJECT;
        }
    }
}
