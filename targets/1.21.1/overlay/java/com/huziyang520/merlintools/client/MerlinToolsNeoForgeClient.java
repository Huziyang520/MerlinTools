// NeoForge client only entrypoint, declared with dist = CLIENT so a dedicated server never even loads this class and the ...

package com.huziyang520.merlintools.client;

import com.huziyang520.merlintools.Constants;
import com.huziyang520.merlintools.network.DamageFeedbackSink;
import com.huziyang520.merlintools.network.ServerSender;
import com.huziyang520.merlintools.tools.hud.CombatFeedback;
import com.huziyang520.merlintools.tools.macro.MacroStorage;
import com.huziyang520.merlintools.tools.ui.ConfigScreens;
import com.huziyang520.merlintools.tools.ui.MerlinScreens;
import com.huziyang520.merlintools.tools.ui.MerlinUi;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.KeyMapping;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class MerlinToolsNeoForgeClient {
    private static final String CATEGORY = "key.categories.merlintools";

    private static final KeyMapping OPEN_EDITOR = new KeyMapping(
            "key.merlintools.open_editor", MerlinUi.KEY_LEFT, CATEGORY);

    private static final KeyMapping OPEN_MACROS = new KeyMapping(
            "key.merlintools.macros", MerlinUi.KEY_RIGHT, CATEGORY);

    public MerlinToolsNeoForgeClient(IEventBus modBus, ModContainer modContainer) {
        IConfigScreenFactory factory = (mod, parent) -> new ConfigurationScreen(mod, parent);
        modContainer.<IConfigScreenFactory>registerExtensionPoint(IConfigScreenFactory.class, () -> factory);
        ConfigScreens.install(parent -> new ConfigurationScreen(modContainer, parent));

        modBus.addListener(this::onRegisterKeyMappings);
        modBus.addListener(this::onRegisterGuiLayers);
        NeoForge.EVENT_BUS.addListener(this::onRegisterClientCommands);
        NeoForge.EVENT_BUS.addListener(this::onClientTick);
        NeoForge.EVENT_BUS.addListener(this::onUseEntity);
        DamageFeedbackSink.setClientSink(payload ->
                CombatFeedback.instance().onServerDamage(payload.damage(), payload.critical(), payload.targetId()));

        ServerSender.setSink(payload ->
                net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                        (net.minecraft.network.protocol.common.custom.CustomPacketPayload) payload));
        MacroStorage.load();
        com.huziyang520.merlintools.tools.HealthEditorItem.setOpener(target ->
                MerlinScreens.openHealthEditor(null, target));
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_EDITOR);
        event.register(OPEN_MACROS);
    }

    private void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("merlintools")
                .then(Commands.literal("editor")
                        .executes(MerlinToolsNeoForgeClient::openEditor))
                .then(Commands.literal("health")
                        .executes(MerlinToolsNeoForgeClient::openHealth))
                .then(Commands.literal("macro")
                        .executes(MerlinToolsNeoForgeClient::openMacros))
                .then(Commands.literal("config")
                        .executes(MerlinToolsNeoForgeClient::openConfig)));
    }

    private static int openEditor(CommandContext<CommandSourceStack> context) {
        MerlinScreens.openItemEditorFromCommand();
        return 1;
    }

    private static int openHealth(CommandContext<CommandSourceStack> context) {
        MerlinScreens.openHealthEditorFromCommand();
        return 1;
    }

    private static int openMacros(CommandContext<CommandSourceStack> context) {
        MerlinScreens.openMacroScreen(null);
        return 1;
    }

    private static int openConfig(CommandContext<CommandSourceStack> context) {
        MerlinScreens.openConfigScreen(null);
        return 1;
    }


    private void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "combat_overlay"),
                CombatFeedback.instance()::render);
        CombatFeedback.announce();
    }

    private void onClientTick(ClientTickEvent.Post event) {
        while (OPEN_EDITOR.consumeClick()) {
            MerlinScreens.openItemEditorFromHotkey();
        }
        while (OPEN_MACROS.consumeClick()) {
            if (net.minecraft.client.Minecraft.getInstance().player != null) {
                MerlinScreens.openMacroScreen(null);
            }
        }
        if (net.minecraft.client.Minecraft.getInstance().player != null) {
            CombatFeedback.instance().tick(net.minecraft.client.Minecraft.getInstance());
            MacroStorage.tick(net.minecraft.client.Minecraft.getInstance());
        }
    }

    private void onUseEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide() && event.getEntity().isShiftKeyDown()
                && event.getTarget() instanceof net.minecraft.world.entity.LivingEntity living) {
            MerlinScreens.openHealthEditor(null, living);
        }
    }
}
