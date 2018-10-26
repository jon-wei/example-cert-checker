package io.imply.security;

import io.druid.java.util.common.logger.Logger;
import io.druid.server.security.TLSCertificateChecker;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ExampleTLSCertificateChecker implements TLSCertificateChecker
{
  private static final Logger log = new Logger(ExampleCertCheckerModule.class);

  private ExampleTLSCertificateCheckerConfig config;

  public ExampleTLSCertificateChecker(
      ExampleTLSCertificateCheckerConfig config
  )
  {
    this.config = config;
  }

  @Override
  public void checkClient(
      X509Certificate[] chain,
      String authType,
      SSLEngine engine,
      X509ExtendedTrustManager baseTrustManager
  ) throws CertificateException
  {
    log.info("Example client check, example parameter from config[%s]", config.getExampleParameter());

    // run the standard cert check
    baseTrustManager.checkClientTrusted(chain, authType, engine);
  }

  @Override
  public void checkServer(
      X509Certificate[] chain,
      String authType,
      SSLEngine engine,
      X509ExtendedTrustManager baseTrustManager
  ) throws CertificateException
  {
    log.info("Example server check, example parameter from config[%s]", config.getExampleParameter());

    // run the standard cert check
    baseTrustManager.checkServerTrusted(chain, authType, engine);
  }
}
