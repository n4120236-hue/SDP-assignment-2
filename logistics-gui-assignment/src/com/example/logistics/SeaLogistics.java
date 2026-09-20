package com.example.logistics;

public final class SeaLogistics extends Logistics {

    @Override
    protected Transport createTransport() {
        return new Ship();
    }
}
