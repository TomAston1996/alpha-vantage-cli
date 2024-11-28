package dev.tomaston.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class AppConfig {

    private final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    private final Properties apiKeyProperties;
    private final String rootPath;

    private String avApiKey;

    public AppConfig() {
        this.apiKeyProperties = new Properties();
        this.rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        LOG.info("App root path is: {}", rootPath);
        setAvApiKey();
    }

    private void setAvApiKey() {
        try {
            apiKeyProperties.load(new FileInputStream(rootPath + "/apiKey.properties"));
            avApiKey = apiKeyProperties.getProperty("alphaVantageApiKey");
            LOG.info("Success loading Alpha Vantage api key from apiKeys.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAvApiKey() {
        return avApiKey;
    }
}
