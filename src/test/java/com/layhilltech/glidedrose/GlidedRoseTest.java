package com.layhilltech.glidedrose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GlidedRoseTest {

    @Test
    void glidedRoseAddNewNormalItemNamedFoo() {

        List<Item> items = List.of(new Item("Foo", 5, 1));
        GlidedRose glidedRose = new GlidedRose(items);
        assertEquals("Foo", glidedRose.stocks().get(0).getName());
    }

    @Test
    void normalItemQualityWhenNegativeThrowsQualityShouldNotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Item("Foo", 5, -1), "Quality should not be negative");
    }

    @Test
    void normalItemQualityStartAt5ShouldReduceTo4AfterOneDay() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 5, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(4, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void normalItemQualityStartAt4ShouldReduceTo2AfterExpiry() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 0, 4));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(2, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void normalItemQualityStartAt1ShouldBe0AfterExpiry() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 0, 1));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void agedBrieQualityAt2ShouldIncreaseBy1AfterExpiry() {
        List<Item> items = new ArrayList();
        items.add(new AgedBrieItem("AgedBrie", 0, 2));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(3, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy1IfSellInDateIs11Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 11, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(6, glidedRose.stocks().get(0).getQuality());
    }


    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy2IfSellInDateIs10Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 10, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(7, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy2IfSellInDateIs9Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 9, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(7, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy3IfSellInDateIs5Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 5, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(8, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldDropTo0IfSellInDateIs0Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 0, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void sulfurasItemQualityStartAt80ButNeverChange() {
        List<Item> items = new ArrayList();
        items.add(new SulfurasItem("Sulfuras", 0, 80));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(80, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void conjuredItemsWithQualityStartAt10ButReducesTo8After1Day() {
        List<Item> items = new ArrayList();
        items.add(new ConjuredItem("Conjured", 2, 10));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(8, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void conjuredItemsWithQualityStartAt5ButReducesTo3After1Day() {
        List<Item> items = new ArrayList();
        items.add(new ConjuredItem("Conjured", 2, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(3, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void normalItemWithSellIn4AndDaysQualityStartAt4ShouldDropTo0After5Days() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 4, 4));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }
}
