package com.github.creoii.greatbigworld.client.screen;

import com.github.creoii.greatbigworld.block.screen.SawmillScreenHandler;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.recipe.SawmillingRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SawmillScreen extends HandledScreen<SawmillScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/gui/container/sawmill.png");
    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;
    private boolean canCraft;

    public SawmillScreen(SawmillScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
        --titleY;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        renderBackground(matrices);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = x;
        int j = y;
        this.drawTexture(matrices, i, j, 0, 0, backgroundWidth, backgroundHeight);
        int k = (int)(41.0F * scrollAmount);
        this.drawTexture(matrices, i + 119, j + 15 + k, 176 + (shouldScroll() ? 0 : 12), 0, 12, 15);
        int l = x + 52;
        int m = y + 14;
        int n = scrollOffset + 12;
        renderRecipeBackground(matrices, mouseX, mouseY, l, m, n);
        renderRecipeIcons(l, m, n);
    }

    protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y) {
        super.drawMouseoverTooltip(matrices, x, y);
        if (canCraft) {
            int i = this.x + 52;
            int j = this.y + 14;
            int k = scrollOffset + 12;
            List<SawmillingRecipe> list = handler.getAvailableRecipes();

            for(int l = scrollOffset; l < k && l < handler.getAvailableRecipeCount(); ++l) {
                int m = l - scrollOffset;
                int n = i + m % 4 * 16;
                int o = j + m / 4 * 18 + 2;
                if (x >= n && x < n + 16 && y >= o && y < o + 18) {
                    renderTooltip(matrices, list.get(l).getOutput(), x, y);
                }
            }
        }

    }

    private void renderRecipeBackground(MatrixStack matrices, int mouseX, int mouseY, int x, int y, int scrollOffset) {
        for(int i = this.scrollOffset; i < scrollOffset && i < handler.getAvailableRecipeCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            int n = backgroundHeight;

            if (i == handler.getSelectedRecipe()) n += 18;
            else if (mouseX >= k && mouseY >= m && mouseX < k + 16 && mouseY < m + 18) n += 36;

            drawTexture(matrices, k, m - 1, 0, n, 16, 18);
        }
    }

    private void renderRecipeIcons(int x, int y, int scrollOffset) {
        List<SawmillingRecipe> list = handler.getAvailableRecipes();

        for (int i = this.scrollOffset; i < scrollOffset && i < handler.getAvailableRecipeCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            client.getItemRenderer().renderInGuiWithOverrides(list.get(i).getOutput(), k, m);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        mouseClicked = false;
        if (canCraft) {
            int i = x + 52;
            int j = y + 14;
            int k = scrollOffset + 12;

            for (int l = scrollOffset; l < k; ++l) {
                int m = l - scrollOffset;
                double d = mouseX - (double)(i + m % 4 * 16);
                double e = mouseY - (double)(j + m / 4 * 18);
                if (d >= 0d && e >= 0d && d < 16d && e < 18d && handler.onButtonClick(client.player, l)) {
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1f));
                    client.interactionManager.clickButton(handler.syncId, l);
                    return true;
                }
            }

            i = x + 119;
            j = y + 9;
            if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 54)) {
                mouseClicked = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (mouseClicked && shouldScroll()) {
            int i = y + 14;
            int j = i + 54;
            scrollAmount = ((float)mouseY - (float)i - 7.5f) / ((float)(j - i) - 15f);
            scrollAmount = MathHelper.clamp(scrollAmount, 0f, 1f);
            scrollOffset = (int)((double)(scrollAmount * (float)getMaxScroll()) + .5d) * 4;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (shouldScroll()) {
            int i = getMaxScroll();
            float f = (float)amount / (float)i;
            scrollAmount = MathHelper.clamp(scrollAmount - f, 0f, 1f);
            scrollOffset = (int)((double)(scrollAmount * (float)i) + .5d) * 4;
        }

        return true;
    }

    private boolean shouldScroll() {
        return canCraft && handler.getAvailableRecipeCount() > 12;
    }

    protected int getMaxScroll() {
        return (handler.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    private void onInventoryChange() {
        canCraft = handler.canCraft();
        if (!canCraft) {
            scrollAmount = 0f;
            scrollOffset = 0;
        }
    }
}
