package com.layhilltech.glidedrose;

public class SulfurasItem extends Item {
    public SulfurasItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Item reduceQuality() {
        return new SulfurasItem(getName(), getSellIn(), getQuality());
    }
}
