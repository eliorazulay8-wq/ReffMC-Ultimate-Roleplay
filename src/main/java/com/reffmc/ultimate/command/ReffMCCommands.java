package com.reffmc.ultimate.command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.reffmc.ultimate.registry.ModEntities;
import com.reffmc.ultimate.entity.StoryNpcEntity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import java.util.List;
public final class ReffMCCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("reffmc")
            .then(Commands.literal("help").executes(context -> {
                context.getSource().sendSuccess(() -> Component.literal("ReffMC Ultimate: /reffmc spawn <bozo|sus|officer|normal>. You can also use the four spawn eggs."), false);
                return 1;
            }))
            .then(Commands.literal("spawn")
                .then(Commands.argument("character", StringArgumentType.word())
                    .suggests((context, builder) -> SharedSuggestionProvider.suggest(List.of("bozo", "sus", "officer", "normal"), builder))
                    .executes(context -> spawn(context.getSource(), StringArgumentType.getString(context, "character"))))));
    }
    private static int spawn(CommandSourceStack source, String character) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        Player player = source.getPlayerOrException();
        EntityType<?> type = switch (character.toLowerCase()) {
            case "bozo" -> ModEntities.BOZO.get();
            case "sus" -> ModEntities.SUS.get();
            case "officer" -> ModEntities.OFFICER.get();
            case "normal" -> ModEntities.NORMAL.get();
            default -> null;
        };
        if (type == null) {
            source.sendFailure(Component.literal("Unknown character. Choose bozo, sus, officer or normal."));
            return 0;
        }
        Entity entity = type.create(player.level());
        if (entity == null) return 0;
        Vec3 pos = player.position().add(player.getLookAngle().scale(2.0D));
        entity.moveTo(pos.x, pos.y, pos.z, player.getYRot(), 0.0F);
        if (entity instanceof StoryNpcEntity npc) {
            String name = switch (character.toLowerCase()) {
                case "bozo" -> "Bozo";
                case "sus" -> "Sus Villager";
                case "officer" -> "Officer Max";
                default -> "Roleplay Villager";
            };
            npc.setCustomName(Component.literal(name));
            npc.setCustomNameVisible(true);
        }
        if (player.level().addFreshEntity(entity)) {
            source.sendSuccess(() -> Component.literal("Spawned " + character + "! Right-click the NPC to talk."), false);
            return 1;
        }
        return 0;
    }
    private ReffMCCommands() { }
}
