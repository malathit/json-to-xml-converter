package com.risksense.converters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Factory class for creating instances of {@link XmlJsonConverterI}.
 */
@Service
public final class ConverterFactory implements XmlJsonConverterI {

  @Override
  public String convertJSONtoXML(String data) {
    Object jsonData;

    try {
      jsonData = new JSONObject(data);
    } catch (JSONException e) {
      try {
        jsonData = new JSONArray(data);
      } catch (JSONException e1) {
        return null;
      }
    }

    return convertToXml(null, jsonData);
  }

  private String convertToXml(String name, Object data) {
    if (name == "lastName") {
      System.out.println("type of lastName : " + name == null);
    }
    String nameValue = (name == null) ? "" : " name=\"" + name + "\"";
    if (data instanceof Number) {
      return "<number" + nameValue + ">" + data + "</number>";
    } else if (data instanceof String) {
      return "<string" + nameValue + ">" + data + "</string>";
    } else if (data instanceof Boolean) {
      return "<boolean" + nameValue + ">" + data + "</boolean>";
    } else if (data instanceof JSONArray) {
      StringBuilder builder = new StringBuilder("<array" + nameValue + ">");
      for (int i = 0; i < ((JSONArray) data).length(); i++) {
        builder.append(convertToXml(null, ((JSONArray) data).get(i)));
      }
      builder.append("</array>");
      return builder.toString();
    } else if (data instanceof JSONObject) {
      StringBuilder builder = new StringBuilder("<object" + nameValue + ">");
      for (String key : ((JSONObject) data).keySet()) {
        Object objData = ((JSONObject) data).get(key);
        builder.append(convertToXml(key, objData));
      }
      builder.append("</object>");
      return builder.toString();
    } else if (data == JSONObject.NULL) {
      return "<null" + nameValue + "/>";
    } else {
      throw new UnsupportedOperationException("Data type "
          + data.getClass() + " not yet supported");
    }
  }
}
