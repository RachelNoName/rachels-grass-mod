package net.rachel.rachels_grass.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;

public class ModCreativeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RachelsGrass.MODID);
	
	public static final RegistryObject<CreativeModeTab> RACHELS_GRASS_TAB = CREATIVE_MODE_TABS.register("rachels_grass_tab", 
			() -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(ModItems.GRASS_BUNDLE.get()))
			.title(Component.translatable("creativetab.rachels_grass"))
			.displayItems((pParamaters, pOutput) -> {
				pOutput.accept(ModItems.GRASS_BUNDLE.get());
				pOutput.accept(ModBlocks.GRASS_BLOCK.get());
				pOutput.accept(ModItems.DRY_GRASS_BUNDLE.get());
			})
			.build());
	
	
	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TABS.register(eventBus);
	}
}
