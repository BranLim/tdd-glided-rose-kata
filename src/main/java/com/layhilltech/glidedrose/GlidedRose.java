package com.layhilltech.glidedrose;

import java.util.List;
import java.util.Map;

public class GlidedRose {

    private List<Item> items;

    public GlidedRose(List<Item> items) {
        this.items = items;
    }

    public List<Item> stocks() {
        return items;
    }
}
