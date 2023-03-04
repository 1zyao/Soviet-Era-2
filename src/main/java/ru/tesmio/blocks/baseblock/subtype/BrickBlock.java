package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import ru.tesmio.blocks.baseblock.BaseBlock;

public class BrickBlock extends BaseBlock {
    public BrickBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3f,8f));
    }
}
