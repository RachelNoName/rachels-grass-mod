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
        simpleAxisBlock(ModBlocks.DRY_GRASS_BLOCK);
    }

    private void simpleAxisBlock(RegistryObject<Block> block) {
        RotatedPillarBlock pillarBlock = (RotatedPillarBlock) block.get();
        axisBlock(pillarBlock);
        itemModels().withExistingParent(name(pillarBlock), ResourceLocation.parse(RachelsGrass.MODID.concat(":block/").concat(name(pillarBlock))));
    }

    private void tintedAxisBlockWithOverlay(RegistryObject<Block> block) {
        RotatedPillarBlock pillarBlock = (RotatedPillarBlock) block.get();

        ResourceLocation sideTexture = ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, "block/" + name(pillarBlock) + "_side");
        ResourceLocation endTexture = ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, "block/" + name(pillarBlock) + "_end");
        ResourceLocation overlayTexture = ResourceLocation.fromNamespaceAndPath(RachelsGrass.MODID, "block/" + name(pillarBlock) + "_overlay");

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
