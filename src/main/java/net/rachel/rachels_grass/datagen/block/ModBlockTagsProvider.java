package net.rachel.rachels_grass.datagen.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlocks;
import net.rachel.rachels_grass.common.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RachelsGrass.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.GRASS_BLOCKS)
                .add(ModBlocks.GRASS_BLOCK.get(),
                     ModBlocks.SLIGHTLY_DRY_GRASS_BLOCK.get(),
                     ModBlocks.VERY_DRY_GRASS_BLOCK.get(),
                     ModBlocks.DRY_GRASS_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(ModTags.Blocks.GRASS_BLOCKS);
    }
}
