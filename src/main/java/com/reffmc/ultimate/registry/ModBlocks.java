package com.reffmc.ultimate.registry;

import com.reffmc.ultimate.ReffMCUltimate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ReffMCUltimate.MODID);

    public static final RegistryObject<Block> SILVER_DESKTOP =
            BLOCKS.register("silver_desktop", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL).strength(2.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> SILVER_GAMING_PC =
            BLOCKS.register("silver_gaming_pc", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL).strength(2.5F).sound(SoundType.METAL)));

    private ModBlocks() {}
}
