package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    GildedRose gildedRose;

    @Test
    void updateQuality_ItemsNormalAging_Test() {
        gildedRose = new GildedRose(new Item[]{getExampleItem("cheese",7,20)});

        updateQualityMultipleTimes(3);
        assertEquals(4, gildedRose.items[0].sellIn);
        assertEquals(17, gildedRose.items[0].quality);

        updateQualityMultipleTimes(6);
        assertEquals(-2, gildedRose.items[0].sellIn);
        assertEquals(9, gildedRose.items[0].quality);
    }

    @Test
    void updateQuality_AgedBrie_Test() {
        gildedRose = new GildedRose(new Item[]{getExampleItem("Aged Brie",2,30)});

        updateQualityMultipleTimes(2);
        assertEquals(0, gildedRose.items[0].sellIn);
        assertEquals(32, gildedRose.items[0].quality);

        gildedRose.updateQuality();
        assertEquals(-1, gildedRose.items[0].sellIn);
        assertEquals(34, gildedRose.items[0].quality);

    }

    @Test
    void updateQuality_Sulfuras_Test() {
        gildedRose = new GildedRose(new Item[]{new Item("Sulfuras, Hand of Ragnaros",0, 80)});

        updateQualityMultipleTimes(5);

        assertEquals(0, gildedRose.items[0].sellIn);
        assertEquals(80, gildedRose.items[0].quality);
    }

    @Test
    void updateQuality_BackstagePasses_Test() {
        gildedRose = new GildedRose(new Item[]{getExampleItem("Backstage passes for some band",12,10)});

        gildedRose.updateQuality();
        assertEquals(11, gildedRose.items[0].sellIn);
        assertEquals(11, gildedRose.items[0].quality);

        gildedRose.updateQuality();
        assertEquals(10, gildedRose.items[0].sellIn);
        assertEquals(13, gildedRose.items[0].quality);

        updateQualityMultipleTimes(5);
        assertEquals(5, gildedRose.items[0].sellIn);
        assertEquals(24, gildedRose.items[0].quality);

        updateQualityMultipleTimes(5);
        assertEquals(0, gildedRose.items[0].sellIn);
        assertEquals(39, gildedRose.items[0].quality);

        gildedRose.updateQuality();
        assertEquals(-1, gildedRose.items[0].sellIn);
        assertEquals(0, gildedRose.items[0].quality);

    }

    @Test
    void updateQuality_MinimumQuality_Test() {
        gildedRose = new GildedRose(new Item[]{getExampleItem("ham",1,2)});

        updateQualityMultipleTimes(5);

        assertEquals(-4, gildedRose.items[0].sellIn);
        assertEquals(0, gildedRose.items[0].quality);

    }

    @Test
    void updateQuality_MaximumQuality_Test() {
        gildedRose = new GildedRose(new Item[]{getExampleItem("Aged Brie",0,49)});

        updateQualityMultipleTimes(2);

        assertEquals(-2, gildedRose.items[0].sellIn);
        assertEquals(50, gildedRose.items[0].quality);
    }

    @Test
    void updateQuality_ConjuredItem_Test(){
        gildedRose = new GildedRose(new Item[]{getExampleItem("Conjured Sword", 10, 25)});

        updateQualityMultipleTimes(11);

        assertEquals(-1, gildedRose.items[0].sellIn);
        assertEquals(1, gildedRose.items[0].quality);
    }


    private void updateQualityMultipleTimes(int times){
        while (times > 0){
            gildedRose.updateQuality();
            --times;
        }

    }

    private static Item getExampleItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }
}
