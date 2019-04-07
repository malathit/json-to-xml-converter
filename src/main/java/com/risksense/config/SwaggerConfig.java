package com.risksense.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Autowired
  BuildProperties buildProperties;

  @Autowired
  GitProperties gitProperties;

  /**
   * Creates the information required to show in swagger ui.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/v1/*"))
        .build()
        .apiInfo(apiEndPointsInfo());
  }

  private ApiInfo apiEndPointsInfo() {
    return new ApiInfoBuilder().title("Json to XML converter")
        .description("Built by " + gitProperties.get("build.user.name")
            + " with code " + gitProperties.getShortCommitId()
            + " from branch " + gitProperties.getBranch())
        .contact(new Contact("Malathi", "", "malu.t90@gmail.com"))
        .license("Apache 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
        .version(buildProperties.getVersion())
        .build();
  }
}
