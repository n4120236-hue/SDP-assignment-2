package com.example.app;

import com.example.gui.Button;
import com.example.gui.Checkbox;
import com.example.gui.GUIFactory;
import com.example.logistics.Logistics;

public final class DeliveryApplication {

    private final GUIFactory guiFactory;
    private final Logistics logistics;

    public DeliveryApplication(GUIFactory guiFactory, Logistics logistics) {
        this.guiFactory = guiFactory;
        this.logistics = logistics;
    }

    public void run(String cargo, String destination) {
        renderUserInterface();
        logistics.planDelivery(cargo, destination);
    }

    private void renderUserInterface() {
        Button button = guiFactory.createButton();
        Checkbox checkbox = guiFactory.createCheckbox();
        button.paint();
        checkbox.paint();
    }
}
