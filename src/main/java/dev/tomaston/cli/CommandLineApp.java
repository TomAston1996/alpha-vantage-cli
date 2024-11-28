package dev.tomaston.cli;

import dev.tomaston.api.IStocksApi;
import dev.tomaston.dto.TickerDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class CommandLineApp {

    private final Logger LOG = LoggerFactory.getLogger(CommandLineApp.class);

    private final IStocksApi stocksApi;
    private final Scanner scanner;

    private boolean isRunning = false;

    /**
     * @param stocksApi stock api interface
     */
    public CommandLineApp(IStocksApi stocksApi) {
        this.stocksApi = stocksApi;
        this.scanner = new Scanner(System.in);
        setup();
    }

    private void setup() {
        //TODO any pre-run setup code here
        this.isRunning = true;
    }

    public void run () {
        while (isRunning) {

            System.out.print("[USER_IN] Enter ticker you would like to search: ");
            String tickerSymbolSearchString = scanner.next();
            LOG.info("Ticker search string is: {}", tickerSymbolSearchString);
            List<TickerDataDTO> tickerDataDTOs = stocksApi.getClientTickerData(tickerSymbolSearchString);
            TerminalPrinter.printTickerDataToTerminal(tickerDataDTOs);
            System.out.println();
            System.out.print("[USER_IN] Do you want to exit the app? [y/n]: ");
            String userExitAns = scanner.next();
            if (userExitAns.equals("y")) {
                isRunning = false;
                LOG.info("App exited...");
            }

            System.out.print("\n\n\n");
        }
    }
}
