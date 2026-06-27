package es.germich3.movablechat.client.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import es.germich3.movablechat.config.MovableChatConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import org.joml.Matrix3x2f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.function.Consumer;

@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {

	@Shadow
	@Final
	private Minecraft minecraft;

	@Unique
	private static boolean enabled(String injector) {
		return switch (injector) {
			case "modifyConstant" -> false;
			case "modifyExpressionValue" -> false;
			case "modifyVariable" -> false;
			case "inject" -> true;
			case "modifyArg" -> false;
			case "modifyArgs" -> false;
			case "wrapOperation" -> false;
			case "redirect" -> false;
			default -> false;
		};
	}

	@Unique
	private int getOffset() {
		if (MovableChatConfigManager.getConfig().isAbsorptionAutoMoveEnabled()) {
			return getOffsetCalcByArmorAbsorption();
		}
		int verticalityChat = MovableChatConfigManager.getConfig().getVerticalityChat();
		int multiplierChat = MovableChatConfigManager.getConfig().getMultiplierChat();
		int plusChat = MovableChatConfigManager.getConfig().getPlusChat();
		int offsetCalcByArmorAbsorption = MovableChatConfigManager.getConfig().isRecalcByArmorAbsorption() ? getOffsetCalcByArmorAbsorption() : 0;
		return (verticalityChat * multiplierChat) + plusChat + offsetCalcByArmorAbsorption;
	}

	@Unique
	private int getOffsetCalcByArmorAbsorption() {
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

	@ModifyConstant(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			constant = @Constant(intValue = 40, ordinal = 0)
	)
	private int modifyContantFirst40(int original) {
		if (!enabled("modifyConstant")) {
			return original;
		}
		return original + getOffset();
	}

	@ModifyExpressionValue(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/util/Mth;floor(F)I"
			)
	)
	private int modifyMthFloor(int original) {
		if (!enabled("modifyExpressionValue")) {
			return original;
		}
		return original - getOffset();
	}

	@ModifyVariable(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			at = @At("STORE"),
			name = "chatBottom"
	)
	private int modifyChatBottom(int value) {
		if (!enabled("modifyVariable")) {
			return value;
		}
		return value - getOffset();
	}

	@Inject(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			at = @At(
					value = "INVOKE", // <- can be HEAD/RETURN/INVOKE/TAIL
					target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V",
					shift = At.Shift.AFTER
			)
	)
	private void injectOnExtractRenderStateAfterUpdatePose(ChatComponent.ChatGraphicsAccess graphics, int i, int j, ChatComponent.DisplayMode displayMode, CallbackInfo ci) {
		if (!enabled("inject")) {
			return;
		}
		graphics.updatePose((pose) -> {
			pose.translate(0.0F, -getOffset());
		});
	}

	@ModifyArg(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			index = 0,
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V"
			)
	)
	private Consumer<Matrix3x2f> modifyConsumerUpdatePose(Consumer<Matrix3x2f> originalConsumer) {
		if (!enabled("modifyArg")) {
			return originalConsumer;
		}
		return (pose) -> {
			originalConsumer.accept(pose);
			pose.translate(0.0F, -getOffset());
		};
	}

	@ModifyArgs(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V"
			)
	)
	private void modifyUpdatePose(Args originalArgs) {
		if (!enabled("modifyArgs")) {
			return;
		}
		Consumer<Matrix3x2f> arg0 = originalArgs.get(0);
		Consumer<Matrix3x2f> modifiedArg0 = pose -> {
			arg0.accept(pose);
			pose.translate(0.0F, -getOffset());
		};
		originalArgs.setAll(modifiedArg0);
	}

	@WrapOperation(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V"
			)
	)
	private void wrapUpdatePose(ChatComponent.ChatGraphicsAccess graphics, Consumer<Matrix3x2f> originalConsumer, Operation<Void> original) {
		if (!enabled("wrapOperation")) {
			original.call(graphics, originalConsumer);
			return;
		}
		Consumer<Matrix3x2f> modified = pose -> {
			originalConsumer.accept(pose);
			pose.translate(0.0F, -getOffset());
		};
		original.call(graphics, modified);
	}

	@Redirect(
			method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;updatePose(Ljava/util/function/Consumer;)V",
					ordinal = 0
			)
	)
	private void redirectUpdatePose(ChatComponent.ChatGraphicsAccess graphics, Consumer<Matrix3x2f> originalConsumer) {
		if (!enabled("redirect")) {
			graphics.updatePose(originalConsumer);
			return;
		}
		graphics.updatePose((pose) -> {
			originalConsumer.accept(pose);
			pose.translate(0.0F, -getOffset());
		});
	}

}