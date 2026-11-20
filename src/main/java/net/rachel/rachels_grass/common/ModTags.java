package net.rachel.rachels_grass.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rachel.rachels_grass.RachelsGrass;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> GRASS_BLOCKS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, "grass_blocks"));
    }

    public static class Items {
        public static final TagKey<Item> CAN_HARVEST_GRASS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, "can_harvest_grass"));
    }
}
