package dev.tomaston;

import dev.tomaston.api.AlphaVantageApi;
import dev.tomaston.cli.CommandLineApp;
import dev.tomaston.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("App starting...");

        //setup app config -> read in application context
        AppConfig appConfig = new AppConfig();

        //choose Alpha Vantage API as stocks API option
        AlphaVantageApi alphaVantageApi = new AlphaVantageApi(appConfig);

        CommandLineApp cliApp = new CommandLineApp(alphaVantageApi);

        cliApp.run();
    }
}
