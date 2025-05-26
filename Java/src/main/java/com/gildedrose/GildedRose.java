package com.gildedrose;

class GildedRose {
    Item[] items;
    int minQuality = 0;
    int maxQuality = 50;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {

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
                case "Conjured muffin":
                    ageConjuredItem(item);
                    break;
                default:
                    ageNormalItem(item);
                    break;
            }
        }
    }

    private void ageNormalItem(Item item) {
        lowerSellIn(item);
        updateQuality(item, -1);
    }

    private void ageConjuredItem(Item item) {
        lowerSellIn(item);
        updateQuality(item, -2);
    }

    private void ageAgedBrie(Item item) {
        lowerSellIn(item);
        updateQuality(item, 1);
    }

    private void ageBackstageItem(Item item) {
        lowerSellIn(item);
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            updateQuality(item, 3);
        } else if (item.sellIn < 10) {
            updateQuality(item, 2);
        } else {
            updateQuality(item, 1);
        }
    }

    private void updateQuality(Item item, int rateOfchange) {
        if (item.sellIn < 0) {
            item.quality += 2*rateOfchange;
        } else {
            item.quality += rateOfchange;
        }
        if(item.quality > maxQuality) {
            item.quality = maxQuality;
        }
        if(item.quality < minQuality) {
            item.quality = minQuality;
        }
    }

    private void lowerSellIn (Item item) {
        item.sellIn -= 1;
    }
}
