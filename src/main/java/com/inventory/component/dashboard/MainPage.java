package com.inventory.component.dashboard;

import com.inventory.component.cards.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

// This is where all the components will be added to the main page, such as the dashboard, products, etc.
public class MainPage extends JPanel {

    private StocksCard stock;
    private Card2 card2;
    private Card3 card3;
    private HistoryCard stockHistory;
    private Card5 card5;

    public MainPage() {
        this.setLayout(new MigLayout("fill, gap 8"));
        this.setBackground(new Color(0xd9d9d9));

        stock = new StocksCard();
        card2 = new Card2();
        card3 = new Card3();
        stockHistory = new HistoryCard();
        card5 = new Card5();

        this.add(Box.createVerticalStrut(1), "cell 0 3");
        this.add(stock, "cell 0 0, grow");
        this.add(card2, "cell 1 0, grow");
        this.add(card3, "cell 2 0 1 2, grow");
        this.add(stockHistory, "cell 0 1 2 3, grow");
        this.add(card5, "cell 2 2 1 2, grow");
    }

    public void showPage(String pageName) {
        if (pageName.equals("Dashboard")) {
            System.out.println("Dashboard Button Clicked");
        } else if (pageName.equals("Create")) {
            System.out.println("Create Button Clicked");
        } else if (pageName.equals("Update")) {
            System.out.println("Update Button Clicked");
        } else if (pageName.equals("Delete")) {
            System.out.println("Delete Button Clicked");
        } else if (pageName.equals("Logout")) {
            System.out.println("Logout Button Clicked");
        }
    }
}
