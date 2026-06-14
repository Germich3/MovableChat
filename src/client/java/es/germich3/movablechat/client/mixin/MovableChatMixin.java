package es.germich3.movablechat.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import es.germich3.movablechat.client.config.MovableChatConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import org.joml.Matrix3x2f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(ChatComponent.class)
public abstract class MovableChatMixin {

	@Shadow
	@Final
	private Minecraft minecraft;

	private int getOffset() {
		if (MovableChatConfig.getConfig().isAbsorptionAutoMoveEnabled()) {
			int offset = 0;
			if (minecraft.player == null || minecraft.player.isCreative() || minecraft.player.isSpectator()) {
				return offset;
			}
			if (minecraft.player.getArmorValue() > 0) {
				offset += 10;
			}
			if (minecraft.player.getAbsorptionAmount() > 0) {
				offset += 10;
			}
			return offset;
		}
		else {
			int verticalityChat = MovableChatConfig.getConfig().getVerticalityChat();
			int multiplierChat = MovableChatConfig.getConfig().getMultiplierChat();
			int plusChat = MovableChatConfig.getConfig().getPlusChat();
			return (verticalityChat * multiplierChat) + plusChat;
		}
	}

	@WrapOperation(
		method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V"
		)
	)
	private void wrapUpdatePose(ChatComponent.ChatGraphicsAccess graphics, Consumer<Matrix3x2f> originalConsumer, Operation<Void> original) {
		Consumer<Matrix3x2f> modified = pose -> {
			originalConsumer.accept(pose);
			pose.translate(0.0F, -getOffset());
		};
		original.call(graphics, modified);
	}

}
