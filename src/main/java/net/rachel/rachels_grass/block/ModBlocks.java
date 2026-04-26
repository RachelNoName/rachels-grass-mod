package net.rachel.rachels_grass.block;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.custom.GrassBlock;
import net.rachel.rachels_grass.items.ModItems;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RachelsGrass.MODID);
	
	public static final RegistryObject<Block> BLOCK_OF_GRASS = registerBlock("grass_block", 
			() -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).mapColor(DyeColor.LIME)));
	
	
	
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> newBlock = BLOCKS.register(name, block);
		registerBlockItem(name, newBlock);
		return newBlock;
	}
	
	
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	
	public static void register(IEventBus eventBUs) {
		BLOCKS.register(eventBUs);
	}
}
