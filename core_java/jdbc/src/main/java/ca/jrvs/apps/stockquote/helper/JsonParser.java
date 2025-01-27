package ca.jrvs.apps.stockquote.helper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonParser {

  /**
   * Convert a java object to JSON string
   *
   * @param object input object
   * @return JSON String
   * @throws JsonProcessingException
   */
  public static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
      throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();
    if (!includeNullValues) {
      m.setSerializationInclusion(Include.NON_NULL);
    }
    if (prettyJson) {
      m.enable(SerializationFeature.INDENT_OUTPUT);
    }
    return m.writeValueAsString(object);
  }

  /**
   * Parse JSON string to an object
   *
   * @param json  JSON str
   * @param clazz object class
   * @param <T>   Type
   * @return Object
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
    // Read the JSON and convert it into a tree of JsonNode objects
    JsonNode rootNode = m.readTree(json);
    // Extract the "Global Quote" root node and then map the tree to the clazz object
    JsonNode quoteNode = rootNode.get("Global Quote");
    return (T) m.treeToValue(quoteNode, clazz);
    //m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //return (T) m.readValue(json, clazz);
  }
}