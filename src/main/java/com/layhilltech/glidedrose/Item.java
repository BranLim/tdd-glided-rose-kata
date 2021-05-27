package com.layhilltech.glidedrose;

public class Item {

    public final static int MAX_QUALITY = 50;
    public final static int MIN_QUALITY = 0;
    private String name;
    private int sellIn;
    private int quality;

    public Item(String name, int sellIn, int quality) {
        if (quality < 0) {
            throw new IllegalArgumentException("Quality should never be negative");
        }
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    protected int getSellIn() {
        return sellIn;
    }

    public String getName() {
        return name;
    }

    public Item changeQuality() {
        if (sellIn == 0) {
            return new Item(name, sellIn, Math.max(MIN_QUALITY, quality - 2));
        }
        return new Item(name, sellIn, Math.max(MIN_QUALITY, quality - 1));
    }


}
