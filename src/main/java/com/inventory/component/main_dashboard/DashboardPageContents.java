package com.inventory.component.main_dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class DashboardPageContents extends JPanel {

    private StocksCard stock;
    private AmountOfProductsCard amountOfProductsCard;
    private AmountOFLowStockCard amountOFLowStockCard;
    private HistoryCard stockHistory;
    private AmountOfOutOfStockCard amountOfOutOfStockCard;
    private String role;

    public DashboardPageContents(String role) {
        this.role = role;
        this.setLayout(new MigLayout("fill, gap 8", "[grow, fill] [grow, fill] [30%!, fill]", "[30%!, fill] [grow, fill]"));

        stock = new StocksCard();
        amountOfProductsCard = new AmountOfProductsCard();
        amountOFLowStockCard = new AmountOFLowStockCard();
        stockHistory = new HistoryCard(role);
        amountOfOutOfStockCard = new AmountOfOutOfStockCard();

        this.add(Box.createVerticalStrut(1), "cell 0 3");
        this.add(stock, "cell 0 0, grow");
        this.add(amountOfProductsCard, "cell 1 0, grow");
        this.add(amountOFLowStockCard, "cell 2 0 1 2, grow");
        this.add(stockHistory, "cell 0 1 2 3, grow");
        this.add(amountOfOutOfStockCard, "cell 2 2 1 2, grow");
    }

    public HistoryCard getStockHistory() {
        return stockHistory;
    }

    public AmountOFLowStockCard getAmountOFLowStockCard() {
        return amountOFLowStockCard;
    }

    public AmountOfProductsCard getAmountOfProductsCard() {
        return amountOfProductsCard;
    }

    public AmountOfOutOfStockCard getAmountOfOutOfStockCard() {
        return amountOfOutOfStockCard;
    }

    public StocksCard getStock() {
        return stock;
    }
}
