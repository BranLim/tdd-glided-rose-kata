package com.layhilltech.glidedrose;

public class Item {
    private final String name;
    private final int sellIn;
    private final int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    public int getSellIn() {
        return sellIn;
    }

    public String getName() {
        return name;
    }
}
