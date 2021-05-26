package com.layhilltech.glidedrose;

public class Item {
    private final String name;
    private final int sellIn;
    private final int quality;

    public Item(String name, int sellIn, int quality) {
        if (quality < 0) {
            throw new IllegalArgumentException("Quality should never be negative");
        }
        if (quality > 50) {
            throw new IllegalArgumentException("Quality should never be more than 50");
        }
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
