package com.risksense.controllers;

import com.risksense.converters.ConverterFactory;
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
public class JsonToXmlController {

  @Autowired
  ConverterFactory converterFactory;

  @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<?> convertToXml(@RequestBody String data) {
    String xmlData = converterFactory.convertJSONtoXML(data);
    if (xmlData == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(xmlData, HttpStatus.OK);
  }
}
