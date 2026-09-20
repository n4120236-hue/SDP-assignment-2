package com.example.logistics;

public final class RoadLogistics extends Logistics {

    @Override
    protected Transport createTransport() {
        return new Truck();
    }
}
