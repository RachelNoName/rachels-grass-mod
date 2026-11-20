package net.rachel.rachels_grass.datagen.loot;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.common.ModTags;
import net.rachel.rachels_grass.items.ModItems;
import net.rachel.rachels_grass.loot.AddItemDamageToolModifier;


public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, RachelsGrass.MODID);
    }

    @Override
    protected void start() {
        add("grass_bundle_from_grass", new AddItemDamageToolModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.parse("blocks/grass")).build(),
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.CAN_HARVEST_GRASS)).build()
        }, ModItems.GRASS_BUNDLE.get(), 1, 1));

        add("grass_bundle_from_tall_grass", new AddItemDamageToolModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.parse("blocks/tall_grass")).build(),
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.CAN_HARVEST_GRASS)).build()
        }, ModItems.GRASS_BUNDLE.get(), 2, 1));
    }
}
