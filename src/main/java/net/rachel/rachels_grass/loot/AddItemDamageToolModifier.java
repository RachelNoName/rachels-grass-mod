package net.rachel.rachels_grass.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddItemDamageToolModifier extends LootModifier {
    public static final Supplier<Codec<AddItemDamageToolModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.create(inst -> codecStart(inst).and(
                    inst.group(
                            ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item),
                            Codec.INT.fieldOf("amount").forGetter(m -> m.amount),
                            Codec.INT.fieldOf("damage").forGetter(m -> m.damage)
                    )
            ).apply(inst, AddItemDamageToolModifier::new)
    ));

    private final Item item;
    private final int amount;
    private final int damage;

    public AddItemDamageToolModifier(LootItemCondition[] conditionsIn, Item item, int amount, int damage) {
        super(conditionsIn);
        this.item = item;
        this.amount = amount;
        this.damage = damage;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition: this.conditions) {
            if (!condition.test(context)) {
               return generatedLoot;
            }
        }
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

        if (entity instanceof Player player) {
            ItemStack item = player.getMainHandItem();

            if (item.isDamageableItem()) {
                item.hurtAndBreak(damage, player, (Player p) -> {});
            }
        }

        for (int i = 0; i < amount; i++) {
            generatedLoot.add(new ItemStack(this.item));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
