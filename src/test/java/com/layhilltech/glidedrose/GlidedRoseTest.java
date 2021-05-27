package com.layhilltech.glidedrose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GlidedRoseTest {

    @Test
    void testGlidedRoseAddNewNormalItemNamedFoo() {

        List<Item> items = List.of(new Item("Foo", 5, 1));
        GlidedRose glidedRose = new GlidedRose(items);
        assertEquals("Foo", glidedRose.stocks().get(0).getName());
    }

    @Test
    void testNormalItemQualityWhenNegativeThrowsQualityShouldNotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Item("Foo", 5, -1), "Quality should not be negative");
    }

    @Test
    void testNormalItemQualityWithQuality5ShouldReduceTo4AfterADay() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 5, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(4, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testNormalItemSellByPassedThenQualityAt4ShouldBe2() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 0, 4));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(2, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testNormalItemSellByPassedThenQualityAt1ShouldBe0() {
        List<Item> items = new ArrayList();
        items.add(new Item("Foo", 0, 0));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testAgedBrieQualityAt2IncreaseByOneWithExpiredSellBy() {
        List<Item> items = new ArrayList();
        items.add(new AgedBrieItem("AgedBrie", 1, 2));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(3, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testBackstagePassesItemQualityStartAt5ShouldIncreaseBy1IfSellInDateIs11Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 11, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(6, glidedRose.stocks().get(0).getQuality());
    }


    @Test
    void testBackstagePassesItemQualityStartAt5ShouldIncreaseBy2IfSellInDateIs10Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 10, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(7, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testBackstagePassesItemQualityStartAt5ShouldIncreaseBy2IfSellInDateIs9Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 9, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(7, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testBackstagePassesItemQualityStartAt5ShouldIncreaseBy3IfSellInDateIs5Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 5, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(8, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testBackstagePassesItemQualityStartAt5ShouldDropTo0IfSellInDateIs0Days() {
        List<Item> items = new ArrayList();
        items.add(new BackstagePassItem("BackstagePass", 0, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testSulfurasItemQualityStartAt80ButNeverChange() {
        List<Item> items = new ArrayList();
        items.add(new SulfurasItem("Sulfuras", 0, 80));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(80, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testAddConjuredItemsWithQualityStartAt10ButReducesTo8() {
        List<Item> items = new ArrayList();
        items.add(new ConjuredItem("Conjured", 2, 10));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(8, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testAddConjuredItemsWithQualityStartAt5ButReducesTo3() {
        List<Item> items = new ArrayList();
        items.add(new ConjuredItem("Conjured", 2, 5));

        GlidedRose glidedRose = new GlidedRose(items);
        glidedRose.updateQuality();
        assertEquals(3, glidedRose.stocks().get(0).getQuality());
    }

    @Test
    void testNormalItemWithSellIn4AndDaysQualityStartAt4ShouldDropTo0After5Days() {
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
