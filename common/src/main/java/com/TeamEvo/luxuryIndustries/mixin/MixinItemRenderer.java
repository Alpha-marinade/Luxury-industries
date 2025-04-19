package com.TeamEvo.luxuryIndustries.mixin;

import com.TeamEvo.luxuryIndustries.Register.TagReg;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {
    private static final ResourceLocation TEXTURE =  ResourceLocation.fromNamespaceAndPath(MODID, "textures/ui/key.png");
    private static final RenderType RENDER_TYPE = RenderType.entityTranslucent(TEXTURE);

    @Shadow @Final private TextureManager textureManager;
    private AbstractTexture textureInstance;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V", shift = At.Shift.AFTER))
    private void onRenderItem(ItemStack stack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, BakedModel model, CallbackInfo ci) {
        if (displayContext == ItemDisplayContext.GUI && stack.has(TagReg.HAS_KEY.get())) {
            renderKeyOverlay(poseStack, buffer);
        }
    }
    private void renderKeyOverlay(PoseStack poseStack, MultiBufferSource buffer) {
        if (textureInstance == null) {
            textureInstance = textureManager.getTexture(TEXTURE);
            textureInstance.setFilter(false, false);
        }
        poseStack.pushPose();
        try {
            poseStack.translate(0, 0.62f, 1);
            poseStack.scale(16f / 256.0F, 16f / 256.0F, 1.0F);

            VertexConsumer vertexConsumer = buffer.getBuffer(RENDER_TYPE);
            Matrix4f matrix = poseStack.last().pose();
            Vector3f normal = new Vector3f(0, 0, 1);
            normal.mulTranspose(poseStack.last().normal());
            final int packLight = LightTexture.pack(15, 15);
            final float[] vertices = {
                    0, 0, 0, 0,
                    16, 0, 1, 0,
                    16, 16, 1, 1,
                    0, 16, 0, 1
            };
            for (int i = 0; i < 4; i++) {
                int idx = i * 4;
                vertexConsumer.addVertex(matrix, vertices[idx], vertices[idx+1], 0)
                        .setColor(255, 255, 255, 255)
                        .setUv(vertices[idx+2], vertices[idx+3])
                        .setUv2(packLight,0)
                        .setOverlay(OverlayTexture.NO_OVERLAY)
                        .setNormal(normal.x(), normal.y(), normal.z());
            }
        } finally {
            poseStack.popPose();
        }
    }
}
/*
@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Shadow @Final private TextureManager textureManager;

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MODID, "textures/ui/key.png");

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V", shift = At.Shift.AFTER))
    private void onRenderItem(ItemStack stack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, BakedModel model, CallbackInfo ci) {
        if (displayContext == ItemDisplayContext.GUI) {
            poseStack.pushPose();
            poseStack.translate(-0.001F, -0.001F, 1);
            if (stack.has(TagReg.HAS_KEY.get())) {
                renderGuiItem(poseStack, TEXTURE, 16, 16, new ItemStack(Items.APPLE), buffer, packedLight, packedOverlay);
            }
            poseStack.popPose();
        }
    }
    public void renderGuiItem(PoseStack poseStack, ResourceLocation texture, int width, int height, ItemStack dummyStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        textureManager.getTexture(texture).setFilter(false, false);
        RenderSystem.setShaderTexture(0, texture);
        int packLight = LightTexture.pack(15, 15);
        int packOverlay = OverlayTexture.NO_OVERLAY;
        poseStack.pushPose();
        poseStack.scale(width / 256.0F, height / 256.0F, 1.0F);
        poseStack.translate(0,height/2f,0);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(texture));
        Matrix4f matrix = poseStack.last().pose();
        Vector3f normal = new Vector3f(0, 0, 1);
        Matrix3f normalMatrix = poseStack.last().normal();
        normal.mulTranspose(normalMatrix);
        for (int i = 0; i < 4; i++) {
            float x = (i == 0 || i == 3) ? 0 : 16;
            float y = (i < 2) ? 0 : 16;
            float u = (i == 0 || i == 3) ? 0 : 1;
            float v = (i < 2) ? 0 : 1;

            vertexConsumer.addVertex(matrix, x, y, 0)
                    .setColor(255, 255, 255, 255)
                    .setUv(u, v)
                    .setUv2(packLight,0)
                    .setOverlay(packOverlay)
                    .setUv1(0, 0)
                    .setNormal(normal.x(), normal.y(), normal.z());
        }
        poseStack.popPose();
    }

}

 */




