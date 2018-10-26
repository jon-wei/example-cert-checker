package io.imply.security;

import com.fasterxml.jackson.databind.Module;
import com.google.common.base.Supplier;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import io.druid.guice.JsonConfigProvider;
import io.druid.initialization.DruidModule;
import io.druid.java.util.common.logger.Logger;
import io.druid.server.security.TLSCertificateChecker;

import java.util.Collections;
import java.util.List;

public class ExampleCertCheckerModule implements DruidModule
{
  private static final Logger log = new Logger(ExampleCertCheckerModule.class);
  private static final String CHECKER_TYPE = "example";

  public List<? extends Module> getJacksonModules()
  {
    return Collections.emptyList();
  }

  public void configure(Binder binder)
  {
    JsonConfigProvider.bind(binder, "druid.security.exampleCertChecker", ExampleTLSCertificateCheckerConfig.class);
  }

  @Provides
  @Named(CHECKER_TYPE)
  public TLSCertificateChecker getTLSCertificateChecker(
      Supplier<ExampleTLSCertificateCheckerConfig> config
  )
  {
    return new ExampleTLSCertificateChecker(config.get());
  }
}
