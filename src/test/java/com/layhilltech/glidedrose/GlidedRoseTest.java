package com.layhilltech.glidedrose;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlidedRoseTest {

    @Test
    void testGlidedRoseAddNewItemNamedFoo() {

        List<Item> items = List.of(new Item("Foo", 5, 1));
        GlidedRose glidedRose = new GlidedRose(items);
        assertEquals("Foo", glidedRose.stocks().get(0).getName());

    }



}
