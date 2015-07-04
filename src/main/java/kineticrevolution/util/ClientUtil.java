package kineticrevolution.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class ClientUtil {

    public static final ResourceLocation MC_BLOCK_SHEET = TextureMap.locationBlocksTexture;
    public static final ResourceLocation MC_ITEM_SHEET = TextureMap.locationItemsTexture;

    public static Minecraft minecraft() {
        return Minecraft.getMinecraft();
    }

    public static FontRenderer fontRenderer() {
        return minecraft().fontRenderer;
    }

    public static TextureManager textureManager() {
        return minecraft().getTextureManager();
    }

    public static SoundHandler soundHandler() {
        return minecraft().getSoundHandler();
    }

    public static void bindTexture(ResourceLocation location) {
        textureManager().bindTexture(location);
    }

    public static World world() {
        return minecraft().theWorld;
    }

    public static EntityPlayer player() {
        return minecraft().thePlayer;
    }

    public static IIcon blockMissingIcon() {
        return ((TextureMap) textureManager().getTexture(MC_BLOCK_SHEET)).getAtlasSprite("missingno");
    }

    public static IIcon itemMissingIcon() {
        return ((TextureMap) textureManager().getTexture(MC_ITEM_SHEET)).getAtlasSprite("missingno");
    }
}
