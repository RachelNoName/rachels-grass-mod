package net.rachel.rachels_grass.datagen.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlocks;
import net.rachel.rachels_grass.items.ModCreativeTabs;
import net.rachel.rachels_grass.items.ModItems;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, RachelsGrass.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        createTab(ModCreativeTabs.RACHELS_GRASS_TAB, "Rachels Grass");

        this.add(ModItems.GRASS_BUNDLE.get(), "Grass Bundle");
        this.add(ModItems.DRY_GRASS_BUNDLE.get(), "Dry Grass Bundle");

        this.add(ModBlocks.GRASS_BLOCK.get(), "Block of Grass");
        this.add(ModBlocks.SLIGHTLY_DRY_GRASS_BLOCK.get(), "Slightly Dry Block of Grass");
        this.add(ModBlocks.VERY_DRY_GRASS_BLOCK.get(), "Very Dry Block of Grass");
        this.add(ModBlocks.DRY_GRASS_BLOCK.get(), "Dry Block of Grass");
    }

    private void createTab(RegistryObject<CreativeModeTab> creativeTab, String value) {
        this.add(creativeTab.get().getDisplayName().getString(), value);
    }
}
