package com.example.app;

import com.example.gui.GUIFactory;
import com.example.gui.MacOSFactory;
import com.example.gui.WindowsFactory;
import com.example.logistics.Logistics;
import com.example.logistics.RoadLogistics;
import com.example.logistics.SeaLogistics;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Main {

    private static final String CARGO_DESCRIPTION = "laboratory equipment";
    private static final String DESTINATION = "Aktau warehouse";
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        String deliveryModeInput = readValue(args, 0, "Delivery mode (ROAD/SEA): ");
        DeliveryMode deliveryMode = parseDeliveryMode(deliveryModeInput);
        if (deliveryMode == null) {
            return;
        }

        String platformInput = readValue(args, 1, "UI platform (WINDOWS/MACOS): ");
        UIPlatform platform = parsePlatform(platformInput);
        if (platform == null) {
            return;
        }

        Logistics logistics = createLogistics(deliveryMode);
        GUIFactory guiFactory = createGuiFactory(platform);

        System.out.println("Delivery mode: " + deliveryMode);
        System.out.println("UI platform: " + platform);

        DeliveryApplication application = new DeliveryApplication(guiFactory, logistics);
        application.run(CARGO_DESCRIPTION, DESTINATION);
    }

    private static String readValue(String[] args, int index, String prompt) {
        if (args.length > index && args[index] != null && !args[index].isBlank()) {
            return args[index].trim();
        }
        System.out.print(prompt);
        try {
            String line = CONSOLE_INPUT.nextLine();
            return line == null ? "" : line.trim();
        } catch (NoSuchElementException | IllegalStateException exception) {
            return "";
        }
    }

    private static DeliveryMode parseDeliveryMode(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            System.out.println("No delivery mode was provided. Supported values: ROAD, SEA.");
            return null;
        }
        try {
            return DeliveryMode.valueOf(rawValue.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            System.out.println("Unsupported delivery mode: '" + rawValue + "'. Supported values: ROAD, SEA.");
            return null;
        }
    }

    private static UIPlatform parsePlatform(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            System.out.println("No UI platform was provided. Supported values: WINDOWS, MACOS.");
            return null;
        }
        try {
            return UIPlatform.valueOf(rawValue.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            System.out.println("Unsupported UI platform: '" + rawValue + "'. Supported values: WINDOWS, MACOS.");
            return null;
        }
    }

    private static Logistics createLogistics(DeliveryMode mode) {
        switch (mode) {
            case ROAD:
                return new RoadLogistics();
            case SEA:
                return new SeaLogistics();
            default:
                throw new IllegalStateException("Unhandled delivery mode: " + mode);
        }
    }

    private static GUIFactory createGuiFactory(UIPlatform platform) {
        switch (platform) {
            case WINDOWS:
                return new WindowsFactory();
            case MACOS:
                return new MacOSFactory();
            default:
                throw new IllegalStateException("Unhandled UI platform: " + platform);
        }
    }
}
