package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items) {
            switch (item.name) {
                case "Sulfuras, Hand of Ragnaros":
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    ageBackstageItem(item);
                    break;
                case "Aged Brie":
                    ageAgedBrie(item);
                    break;
                default:
                    ageNormalItem(item);
                    break;
            }
        }
    }

    private void ageNormalItem(Item item) {
        lowerSellIn(item);
        lowerQuality(item);
        if (item.sellIn < 0) {
            lowerQuality(item);
        }
    }

    private void ageAgedBrie(Item item) {
        lowerSellIn(item);
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void ageBackstageItem(Item item) {
        lowerSellIn(item);
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            increaseQuality(item);
            increaseQuality(item);
            increaseQuality(item);
        } else if (item.sellIn < 10) {
            increaseQuality(item);
            increaseQuality(item);
        } else {
            increaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void lowerQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    private void lowerSellIn (Item item) {
        item.sellIn -= 1;
    }
}
