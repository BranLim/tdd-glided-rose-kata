package com.layhilltech.glidedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GlidedRoseTest {

    private List<Item> items;
    private GlidedRose glidedRose;

    @BeforeEach
    void setUp() {
        items = new ArrayList();
        glidedRose = new GlidedRose(items);
    }

    @Test
    void newNormalItemNamedFoo() {

        items.add(new Item("Foo", 5, 1));
        assertEquals("Foo", glidedRose.stocks().get(0).getName());
    }

    @Test
    void normalItemQualityWhenNegativeThrowsQualityShouldNotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Item("Foo", 5, -1), "Quality should not be negative");
    }

    @Test
    void normalItemQualityStartAt5ShouldReduceTo4AfterOneDay() {

        items.add(new Item("Foo", 5, 5));

        glidedRose.updateQuality();
        assertEquals(4, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void normalItemQualityStartAt4ShouldReduceTo2AfterExpiry() {

        items.add(new Item("Foo", 0, 4));

        glidedRose.updateQuality();
        assertEquals(2, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void normalItemQualityStartAt1ShouldBe0AfterExpiry() {

        items.add(new Item("Foo", 0, 1));

        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void agedBrieQualityAt2ShouldIncreaseBy1AfterExpiry() {

        items.add(new AgedBrieItem("AgedBrie", 0, 2));

        glidedRose.updateQuality();
        assertEquals(3, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy1IfSellInDateIs11Days() {

        items.add(new BackstagePassItem("BackstagePass", 11, 5));

        glidedRose.updateQuality();
        assertEquals(6, glidedRose.stocks().get(0).getQuality());
    }


    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy2IfSellInDateIs10Days() {

        items.add(new BackstagePassItem("BackstagePass", 10, 5));

        glidedRose.updateQuality();
        assertEquals(7, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy2IfSellInDateIs9Days() {

        items.add(new BackstagePassItem("BackstagePass", 9, 5));

        glidedRose.updateQuality();
        assertEquals(7, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldIncreaseBy3IfSellInDateIs5Days() {

        items.add(new BackstagePassItem("BackstagePass", 5, 5));

        glidedRose.updateQuality();
        assertEquals(8, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void backstagePassesItemQualityStartAt5ShouldDropTo0IfSellInDateIs0Days() {

        items.add(new BackstagePassItem("BackstagePass", 0, 5));

        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void sulfurasItemQualityStartAt80ButNeverChange() {

        items.add(new SulfurasItem("Sulfuras", 0, 80));

        glidedRose.updateQuality();
        assertEquals(80, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void conjuredItemsWithQualityStartAt10ButReducesTo8After1Day() {

        items.add(new ConjuredItem("Conjured", 2, 10));

        glidedRose.updateQuality();
        assertEquals(8, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void conjuredItemsWithQualityStartAt5ButReducesTo3After1Day() {

        items.add(new ConjuredItem("Conjured", 2, 5));

        glidedRose.updateQuality();
        assertEquals(3, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void normalItemWithSellIn4AndDaysQualityStartAt4ShouldDropTo0After5Days() {

        items.add(new Item("Foo", 4, 4));

        glidedRose.updateQuality();
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }
}
