package com.layhilltech.glidedrose;

public class Item {
    private String name;
    private int sellIn;
    private int quality;

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

    public Item reduceQuality() {
        if (sellIn == 0) {
            return new Item(name, sellIn, Math.max(0, quality - 2));
        }
        return new Item(name, sellIn, Math.max(0, quality - 1));
    }
}
