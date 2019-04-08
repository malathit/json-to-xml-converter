package com.risksense.controllers;

import com.risksense.converters.ConverterFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/json2xml", produces = MediaType.APPLICATION_XML_VALUE)
@Api(value="/api/v1/json2xml",description="Json to XML converter", produces = MediaType.APPLICATION_XML_VALUE)
public class JsonToXmlController {

  @Autowired
  ConverterFactory converterFactory;

  /**
   * Given the json input, converts the json to xml.
   */
  @ApiOperation(value = "Convert Json",response = String.class)

  @ApiResponses(value={
      @ApiResponse(code = 200, message = "Json converted to Xml",response = String.class),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "Invalid json given as input")
  })
  @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<?> convertToXml(@RequestBody String data) {
    String xmlData = converterFactory.convertJsonToXml(data);
    if (xmlData == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(xmlData, HttpStatus.OK);
  }
}
