package dev.tomaston.cli;

import dev.tomaston.dto.TickerDataDTO;

import java.util.List;

public class TerminalPrinter {
    public static void printTickerDataToTerminal(List<TickerDataDTO> tickerDataDTOs) {
        System.out.println("[RESULT]: Printing results below...");
        int id = 0;
        for (TickerDataDTO tickerDataDTO : tickerDataDTOs) {
            id++;
            System.out.println("\t [ID-" + id + "] Symbol: " + tickerDataDTO.symbol + ", Name: " + tickerDataDTO.name);
        }
    }
}
