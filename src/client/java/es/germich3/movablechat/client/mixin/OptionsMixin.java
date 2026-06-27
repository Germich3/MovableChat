package es.germich3.movablechat.client.mixin;

import es.germich3.movablechat.config.MovableChatConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @Unique
    private OptionInstance<Integer> chatPositionY;

    @Unique
    private OptionInstance<Boolean> isCalcByArmorAbsorption;

    @Inject(
            method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V",
            at = @At("TAIL")
    )
    private void onConstructed(final Minecraft minecraft, final File workingDirectory, CallbackInfo ci) {
        chatPositionY = new OptionInstance<>(
                "text.autoconfig.movablechat.option.verticalityChat",
                OptionInstance.noTooltip(),
                (caption, value) -> Component.translatable("options.pixel_value", caption, value),
                new OptionInstance.IntRange(-25, 787, false),
                MovableChatConfigManager.getConfig().getVerticalityChat(),
                (value) -> {
                    MovableChatConfigManager.getConfig().setVerticalityChat(value);
                    MovableChatConfigManager.save();
                }
        );
        isCalcByArmorAbsorption = new OptionInstance<>(
                "text.movablechat.config.RecalcByArmorAbsorption",
                (var) -> Tooltip.create(Component.translatable("text.movablechat.config.tooltip.RecalcByArmorAbsorption")),
                OptionInstance.BOOLEAN_TO_STRING,
                OptionInstance.BOOLEAN_VALUES,
                MovableChatConfigManager.getConfig().isRecalcByArmorAbsorption(),
                (value) -> {
                    MovableChatConfigManager.getConfig().setRecalcByArmorAbsorption(value);
                    MovableChatConfigManager.save();
                }
        );
    }

}
