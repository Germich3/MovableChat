package es.germich3.movablechat.client.screens;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.toasts.TutorialToast;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;


public class MissingClothConfigScreen extends Screen {

    private final Screen previousScreen;

    public MissingClothConfigScreen(Screen previousScreen) {
        super(Component.translatable("text.autoconfig.movablechat.title"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(
            Button.builder(
                CommonComponents.GUI_COPY_LINK_TO_CLIPBOARD,
                (button) -> {
                    this.minecraft.keyboardHandler.setClipboard(Component.translatable("text.movablechat.config.error.clothconfig").getString());
                    this.minecraft.gui.toastManager().addToast(
                        new TutorialToast(
                            this.font,
                            TutorialToast.Icons.MOUSE,
                            Component.literal(""),
                            Component.translatable("text.movablechat.config.error.clipboard"),
                            false,
                            3000
                        )
                    );
                }
            )
            .bounds(this.width / 2 - 100, 140, 200, 20)
            .build()
        );
        this.addRenderableWidget(
            Button.builder(
                CommonComponents.GUI_BACK,
                (button) -> this.minecraft.gui.setScreen(previousScreen)
            )
            .bounds(this.width / 2 - 100, 180, 200, 20)
            .build()
        );
    }

    @Override
    public void extractRenderState(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        graphics.centeredText(this.font, this.title, this.width / 2, 90, -1);
        graphics.centeredText(this.font, Component.translatable("text.movablechat.config.error.message"), this.width / 2, 110, -1);
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(previousScreen);
    }

}
