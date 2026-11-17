package net.rachel.rachels_grass.datagen.block;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RachelsGrass.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        tintedAxisBlockWithOverlay(ModBlocks.GRASS_BLOCK);
        tintedAxisBlockWithOverlayOverride(ModBlocks.SLIGHTLY_DRY_GRASS_BLOCK, "grass_block");
        simpleAxisBlock(ModBlocks.DRY_GRASS_BLOCK);
    }


    private void simpleAxisBlock(RegistryObject<Block> block) {
        RotatedPillarBlock pillarBlock = (RotatedPillarBlock) block.get();
        axisBlock(pillarBlock);
        itemModels().withExistingParent(name(pillarBlock), ResourceLocation.parse(RachelsGrass.MODID.concat(":block/").concat(name(pillarBlock))));
    }


    private void tintedAxisBlockWithOverlay(RegistryObject<Block> block) {
        final String BASE_NAME = name(block.get());
        tintedAxisBlockWithOverlay(block, "block/" + BASE_NAME + "_side", "block/" + BASE_NAME + "_end", "block/" + BASE_NAME + "_overlay");
    }


    private void tintedAxisBlockWithOverlay(RegistryObject<Block> block, String baseNameOverride) {
        tintedAxisBlockWithOverlay(block, "block/" + baseNameOverride + "_side", "block/" + baseNameOverride + "_end", "block/" + baseNameOverride + "_overlay");
    }


    private void tintedAxisBlockWithOverlay(RegistryObject<Block> block, String baseNameOverride, String overlayBaseNameOverride) {
        tintedAxisBlockWithOverlay(block, "block/" + baseNameOverride + "_side", "block/" + baseNameOverride + "_end", "block/" + overlayBaseNameOverride + "_overlay");
    }


    private void tintedAxisBlockWithOverlayOverride(RegistryObject<Block> block, String overlayOverride) {
        final String BASE_NAME = name(block.get());
        tintedAxisBlockWithOverlay(block, "block/" + BASE_NAME + "_side", "block/" + BASE_NAME + "_end", "block/" + overlayOverride + "_overlay");
    }


    private void tintedAxisBlockWithOverlay(RegistryObject<Block> block, String sideTextureLocation, String endTextureLocation, String overlayTextureLocation) {
        RotatedPillarBlock pillarBlock = (RotatedPillarBlock) block.get();

        ResourceLocation sideTexture = ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, sideTextureLocation);
        ResourceLocation endTexture = ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, endTextureLocation);
        ResourceLocation overlayTexture = ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, overlayTextureLocation);

        ResourceLocation renderType = ResourceLocation.fromNamespaceAndPath("minecraft", "cutout");

        axisBlock(pillarBlock,
                models().cubeColumn(name(pillarBlock), sideTexture, endTexture).texture("overlay", overlayTexture).renderType(renderType)
                        .element()
                        .face(Direction.UP).uvs(0, 0, 16, 16).texture("#end").tintindex(0).cullface(Direction.UP).end()
                        .face(Direction.DOWN).uvs(0, 0, 16, 16).texture("#end").tintindex(0).cullface(Direction.DOWN).end()
                        .face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.NORTH).end()
                        .face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.SOUTH).end()
                        .face(Direction.EAST).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.EAST).end()
                        .face(Direction.WEST).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.WEST).end()
                        .end()
                        .element()
                        .face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.NORTH).end()
                        .face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.SOUTH).end()
                        .face(Direction.EAST).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.EAST).end()
                        .face(Direction.WEST).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.WEST).end()
                        .end(),
                models().cubeColumnHorizontal(name(pillarBlock) + "_horizontal", sideTexture, endTexture).texture("overlay", overlayTexture).renderType(renderType)
                        .element()
                        .face(Direction.UP).uvs(0, 0, 16, 16).texture("#end").tintindex(0).cullface(Direction.UP).end()
                        .face(Direction.DOWN).uvs(0, 0, 16, 16).texture("#end").tintindex(0).cullface(Direction.DOWN).end()
                        .face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.NORTH).end()
                        .face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.SOUTH).end()
                        .face(Direction.EAST).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.EAST).end()
                        .face(Direction.WEST).uvs(0, 0, 16, 16).texture("#side").tintindex(0).cullface(Direction.WEST).end()
                        .end()
                        .element()
                        .face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.NORTH).end()
                        .face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.SOUTH).end()
                        .face(Direction.EAST).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.EAST).end()
                        .face(Direction.WEST).uvs(0, 0, 16, 16).texture("#overlay").cullface(Direction.WEST).end()
                        .end());

        itemModels().withExistingParent(name(pillarBlock), ResourceLocation.parse(RachelsGrass.MODID.concat(":block/").concat(name(pillarBlock))));
    }


    private String name(Block block) {
        return key(block).getPath();
    }


    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
