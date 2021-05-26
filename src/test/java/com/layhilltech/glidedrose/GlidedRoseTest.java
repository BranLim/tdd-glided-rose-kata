package com.layhilltech.glidedrose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GlidedRoseTest {

    @Test
    void testGlidedRoseAddNewItemNamedFoo() {

        List<Item> items = List.of(new Item("Foo", 5, 1));
        GlidedRose glidedRose = new GlidedRose(items);
        assertEquals("Foo", glidedRose.stocks().get(0).getName());
    }

    @Test
    void testItemQualityWhenNegativeThrowsQualityShouldNotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Item("Foo", 5, -1), "Quality should not be negative");
    }

    @Test
    void testItemQualityWhen51ThrowsQualityShouldNotBeMoreThanFifty() {
        assertThrows(IllegalArgumentException.class, () -> new Item("Foo", 5, 51), "Quality should not be more than 50");
    }

    @Test
    void testItemQualityWithQuality5ShouldReduceTo4AfterADay() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 5, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(4, glidedRose.stocks().get(0).getQuality());
    }
}
