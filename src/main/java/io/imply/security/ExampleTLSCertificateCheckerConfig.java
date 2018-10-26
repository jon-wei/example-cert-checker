package io.imply.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExampleTLSCertificateCheckerConfig
{
  @JsonProperty
  private String exampleParameter = null;

  private ExampleTLSCertificateCheckerConfig() {}

  public ExampleTLSCertificateCheckerConfig(
      String exampleParameter
  )
  {
    this.exampleParameter = exampleParameter;
  }

  public String getExampleParameter()
  {
    return exampleParameter;
  }
}
