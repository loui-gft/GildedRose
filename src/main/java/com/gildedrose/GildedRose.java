package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                --item.sellIn;
                if (item.name.equals("Aged Brie"))
                    updateBrie(item);
                else if (item.name.startsWith("Backstage passes"))
                    updatePasses(item);
                else if (item.name.startsWith("Conjured"))
                    updateConjured(item);
                else
                    updateRegular(item);
            }
        }
    }

    private void updateBrie(Item item) {
        if(item.quality < 50){
            if(item.sellIn < 0)
                item.quality += 2;
            else
                ++item.quality;

            checkIfQualityTooHigh(item);
        }
    }

    private void updatePasses(Item item) {
        if(canImprove(item)){
            if(item.sellIn < 6)
                item.quality += 3;
            else if(item.sellIn < 11)
                item.quality += 2;
            else
                ++item.quality;

            checkIfQualityTooHigh(item);
        } else if(item.quality > 0){
            item.quality = 0;
        }
    }

    private static boolean canImprove (Item item) {
        return item.sellIn >= 0 && item.quality < 50;
    }

    private void updateConjured(Item item) {
        if (item.quality >= 0){
            decreaseItemQuality(item);
            decreaseItemQuality(item);
        }
    }

    private void updateRegular(Item item) {
        if (item.quality >= 0)
            decreaseItemQuality(item);
    }


    private static void checkIfQualityTooHigh(Item item){
        if (item.quality > 50)
            item.quality = 50;
    }

    private static void decreaseItemQuality(Item item) {
        if(item.sellIn < 0)
            item.quality -= 2;
        else
            --item.quality;

        if(item.quality < 0){
            item.quality = 0;
        }
    }
}
