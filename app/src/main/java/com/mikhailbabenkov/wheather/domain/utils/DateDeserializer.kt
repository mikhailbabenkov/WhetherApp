package com.mikhailbabenkov.wheather.domain.utils

import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type
import java.util.Date

class DateDeserializer : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement, typeOF: Type,
        context: JsonDeserializationContext
    ): Date {
        return TODO()
    }
}