package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration()
      .component("netty4-http")
      .port("8080")
      .bindingMode(RestBindingMode.auto);
    
    rest()
      .path("/").consumes("application/json").produces("application/json")
        .post("/customer")
          .to("direct:post-customer")
        .get("/")
          .to("direct:post-customer")
        .get("/customer")
          .to("direct:post-customer");
    
    from("direct:post-customer")
      .setHeader("HTTP_METHOD", constant("POST"))
      .to("direct:request");

    from("direct:request")
      .to("log:DEBUG?showBody=true&showHeaders=true")
      .transform(constant("Hello world"));
      
  }
}