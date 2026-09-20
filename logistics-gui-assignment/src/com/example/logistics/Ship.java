package com.example.logistics;

public final class Ship implements Transport {

    @Override
    public void deliver(String cargo, String destination) {
        System.out.println("Ship delivers " + cargo + " to " + destination + " by sea.");
    }
}
