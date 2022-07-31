package es.germich3.movablechat.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WarningScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class MissingClothConfigScreen extends WarningScreen {

    private final Screen previousScreen;

    protected MissingClothConfigScreen(Screen previousScreen) {
        super(
            ScreenTexts.EMPTY,
            Text.translatable("text.movablechat.config.error.message"),
            Text.translatable("text.movablechat.config.error.message")
        );
        this.previousScreen = previousScreen;
    }

    @Override
    protected void initButtons(int yOffset) {
        drawCopyLinkButton();
        drawBackButton();
    }

    private void drawCopyLinkButton() {
        Text text = Text.translatable("text.movablechat.config.error.clothconfig");
        int w = this.textRenderer.getWidth(text);
        int h = 10;
        int x = (this.width / 2) - (w / 2);
        int y = (this.height / 2);
        this.addDrawableChild(new PressableTextWidget(x, y, w, h, text, button -> client.keyboard.setClipboard(text.getString()), this.textRenderer));
    }

    private void drawBackButton() {
        int w = 150;
        int h = 20;
        int x = (this.width / 2) - (w / 2);
        int y = (this.height / 2) + (this.height / 4);
        this.addDrawableChild(new ButtonWidget(x, y, w, h, ScreenTexts.BACK, button -> client.setScreen(previousScreen)));
    }

}
