package com.risksense.converters;

import java.io.IOException;

/**
 * This interface provides methods that are required for creating a converter from XML to JSON.
 */
public interface XmlJsonConverterI {

  /**
   * Reads in the JSON from the given file and outputs the data, converted to
   * XML, to the given file. Exceptions are thrown by this method so that the
   * caller can clean up the before exiting.
   *
   * @param json JSON data.
   * @return XML data.
   * @throws java.io.IOException throws {@link IOException}
   */
  public String convertJsonToXml(String json) throws IOException;
}
