package com.layhilltech.glidedrose;

public class ConjuredItem extends Item {
    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Item changeQuality() {
        return new ConjuredItem(getName(), getSellIn(), Math.max(MIN_QUALITY, getQuality() - 2));
    }
}
