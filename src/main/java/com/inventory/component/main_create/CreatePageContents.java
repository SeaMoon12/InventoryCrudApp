package com.inventory.component.main_create;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class CreatePageContents extends JPanel {

    private CreateCard createCard;

    public CreatePageContents() {
        this.setLayout(new MigLayout("fill, gap 8"));
        createCard = new CreateCard();

        this.add(createCard, "grow");
    }
}
