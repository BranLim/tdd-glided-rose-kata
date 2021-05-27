package com.layhilltech.glidedrose;

public class BackstagePassItem extends Item {
    public BackstagePassItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Item changeQuality() {
        int newQuality = getQuality();
        if (getSellIn() > 10) {
            newQuality = Math.min(MAX_QUALITY, newQuality + 1);
        } else if (getSellIn() > 5 && getSellIn() <= 10) {
            newQuality = Math.min(MAX_QUALITY, newQuality + 2);
        } else if (getSellIn() > 0 && getSellIn() <= 5) {
            newQuality = Math.min(MAX_QUALITY, newQuality + 3);
        } else {
            newQuality = 0;
        }
        return new BackstagePassItem(getName(), getSellIn(), newQuality);
    }
}
