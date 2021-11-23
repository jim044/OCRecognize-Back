package com.ocrecognize.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.inject.Qualifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Configuration
@Component("ClientAuthentificationRestemplate")
public class RestemplateCustomService extends RestTemplate {

    @Bean
    public RestTemplate getRestTemplateClientAuthentication() {
        final String allPassword = "trustoreDevPassword";
        final String keyStorePassword = "keystoreDevPassword";

        SSLContext sslContext = null;
        RestTemplate restTemplate = null;
        try {
            sslContext = SSLContextBuilder
                    .create()
                    .loadKeyMaterial(ResourceUtils.getFile("classpath:jks/dev/keystore.jks"), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                    .loadTrustMaterial(ResourceUtils.getFile("classpath:jks/dev/trustore.jks"), allPassword.toCharArray())
                    .build();

            HttpClient httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .build();

            ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

            restTemplate.setRequestFactory(requestFactory);

        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | CertificateException | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        return restTemplate;
    }
}
