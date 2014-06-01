package mvt.process

import com.google.gson.GsonBuilder
import java.io.InputStreamReader
import com.google.gson.reflect.TypeToken
import java.util.ArrayList
import com.google.gson.JsonObject
import java.util.Date
import com.google.gson.JsonPrimitive

public class ProcessMvt {

    private val gson = GsonBuilder().create()!!

    fun getMvtFromFile() :List<JsonObject> {
        val inputStream = javaClass<ProcessMvt>().getResourceAsStream("/all_mvt.json")!!
        val listType = object : TypeToken<ArrayList<JsonObject>>() {}.getType()
        return gson.fromJson(InputStreamReader(inputStream), listType)!!
    }

    fun process() {
        val allMvt = getMvtFromFile()
        for (mvt in allMvt) {
            val body = mvt.get("body") as JsonObject
            val planned = getLong(body, "planned_timestamp")
            val actual = getLong(body, "actual_timestamp")
            val eventType = getString(body, "event_type")
            println("planned time: " + timestamp(planned) + ", actual: " + timestamp(actual) + " (" + eventType + ")")
        }
    }

    fun timestamp(timestamp :String?) :String {
        if (timestamp != null) {
            try {
                return timestamp(java.lang.Long.parseLong(timestamp))
            }catch (e :NumberFormatException) {
                return "invalid"
            }
        }
        return "null"
    }

    fun timestamp(timestamp :Long?) :String {
        if (timestamp != null) {
            return Date(timestamp).toString()
        }
        return "null"
    }

    fun getString(jsonObject :JsonObject, property :String): String? {
        val element = jsonObject.get(property)
        if (element is JsonPrimitive) {
            return element.getAsString()
        }
        return null
    }

    fun getLong(jsonObject :JsonObject, property :String): Long? {
        val element = jsonObject.get(property)
        if (element is JsonPrimitive) {
            try {
                return element.getAsBigInteger()?.longValue()
            } catch (e :NumberFormatException) {
                return null
            }
        }
        return null
    }
}

fun main(args: Array<String>) {
    ProcessMvt().process()
}