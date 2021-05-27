package com.layhilltech.glidedrose;

public class AgedBrieItem extends Item {
    public AgedBrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Item reduceQuality() {
        return new AgedBrieItem(this.getName(), super.getSellIn(), Math.min(50, this.getQuality() + 1));
    }
}
