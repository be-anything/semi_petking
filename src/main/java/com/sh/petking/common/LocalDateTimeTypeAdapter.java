package com.sh.petking.common;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public JsonElement serialize(final LocalDateTime localDateTime, final Type srcType,
                                 final JsonSerializationContext context) {
        if (localDateTime == null) {
            return JsonNull.INSTANCE; // or return new JsonPrimitive("default_value");
        }
        return new JsonPrimitive(formatter.format(localDateTime));
    }

    @Override
    public LocalDateTime deserialize(final JsonElement json,final Type typeOfT,
                                     final JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull()) {
            return null; // or return some default value
        }
        return LocalDateTime.parse(json.getAsString(), formatter);
    }
}
