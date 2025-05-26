package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void sellInAndQualityOfItemLowerDaily() {
        Item[] items = new Item[] { new Item("placeholder", 3, 4) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("placeholder", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void qualityLowersTwiceAsFastAfterSellInDate() {
        Item[] items = new Item[] { new Item("placeholder", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("placeholder", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void qualityNeverNegative() {
        Item[] items = new Item[] { new Item("placeholder", 1, 0), new Item("Conjured muffin", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();

        assertEquals("placeholder", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        assertEquals("Conjured muffin", app.items[1].name);
        assertEquals(0, app.items[1].sellIn);
        assertEquals(0, app.items[1].quality);

        app.updateInventory();

        assertEquals("placeholder", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        assertEquals("Conjured muffin", app.items[1].name);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    void agedBrieAndBackstagePassesIncreaseInQualityOverTime () {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 4), new Item("Backstage passes to a TAFKAL80ETC concert", 12, 2) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[1].name);
        assertEquals(11, app.items[1].sellIn);
        assertEquals(3, app.items[1].quality);
    }

    @Test
    void qualityNeverIncreasesAbove50 () {
        Item[] items = new Item[] {
            new Item("Aged Brie", 3, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 50)
        };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[1].name);
        assertEquals(10, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[2].name);
        assertEquals(5, app.items[2].sellIn);
        assertEquals(50, app.items[2].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(1, app.items[3].sellIn);
        assertEquals(50, app.items[3].quality);
    }

    @Test
    void qualityOrSellInOfSulfurasNeverChanges() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 3, 80)};
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityIncreasesBy2WithSellInLowerThan10 () {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5)};
        GildedRose app = new GildedRose(items);
        app.updateInventory();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(7, app.items[0].quality);

        app.updateInventory();
        app.updateInventory();
        app.updateInventory();
        app.updateInventory();

        assertEquals(5, app.items[0].sellIn);
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityIncreasesBy3WithSellInLowerThan5 () {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 2)};
        GildedRose app = new GildedRose(items);
        app.updateInventory();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);

        app.updateInventory();
        app.updateInventory();
        app.updateInventory();
        app.updateInventory();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(17, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityDropsToZeroAfterSellInHasPassed () {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 22)};
        GildedRose app = new GildedRose(items);
        app.updateInventory();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityOfConjuredItemsDecreasesTwiceAsFastAsNormalItemsBeforeSellIn() {
        Item[] items = new Item[] { new Item("Conjured muffin", 2, 5) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("Conjured muffin", app.items[0].name);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);

        app.updateInventory();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void qualityOfConjuredItemsDecreasesTwiceAsFastAsNormalItemsAfterSellIn() {
        Item[] items = new Item[] { new Item("Conjured muffin", 0, 9) };
        GildedRose app = new GildedRose(items);
        app.updateInventory();
        assertEquals("Conjured muffin", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);

        app.updateInventory();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

}
