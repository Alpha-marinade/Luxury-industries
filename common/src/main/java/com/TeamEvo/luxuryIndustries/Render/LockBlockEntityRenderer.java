package com.TeamEvo.luxuryIndustries.Render;

import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.LockBlockEntity;
import com.TeamEvo.luxuryIndustries.Register.TagReg;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;
public class LockBlockEntityRenderer implements BlockEntityRenderer<LockBlockEntity> {
    private static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MODID, "textures/ui/lock_1.png");

    public LockBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(LockBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player.getItemInHand(InteractionHand.MAIN_HAND).has(TagReg.HAS_KEY.get())) {
            HitResult hitResult = mc.hitResult;
            if (hitResult instanceof BlockHitResult blockHitResult && blockEntity.getBlockPos().equals(blockHitResult.getBlockPos())) {

                VertexConsumer buffer = bufferSource.getBuffer(RenderType.entityTranslucent(OVERLAY_TEXTURE));
                Matrix4f matrix = poseStack.last().pose();
                Direction face = blockHitResult.getDirection();

                renderFaceOverlay(matrix, buffer, face, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
            }
        }
    }

    private void renderFaceOverlay(Matrix4f matrix, VertexConsumer buffer, Direction face,
                                   int packedLight, int packedOverlay) {
        float depth = 0.001f;
        Vector3f normal = new Vector3f(
                face.getStepX(),
                face.getStepY(),
                face.getStepZ()
        );
        normal.normalize();

        switch (face) {
            case SOUTH -> {
                renderQuad(buffer, matrix,
                        1, 1, 1 + depth,
                        0, 1, 1+depth,
                        0, 0, 1 + depth,
                        1, 0, 1+depth,
                        0, 0, 1, 1, normal, packedLight, packedOverlay);
            }
            case NORTH  -> {
                renderQuad(buffer, matrix,
                        0, 1, 0 - depth,
                        1, 1, 0 - depth,
                        1, 0, 0 - depth,
                        0, 0, 0 - depth,
                        0, 0, 1, 1, normal, packedLight, packedOverlay);
            }
            case EAST -> renderQuad(buffer, matrix,
                    1 + depth, 0, 1,
                    1 + depth, 0, 0,
                    1 + depth, 1, 0,
                    1 + depth, 1, 1,
                    0, 0, 1, 1, normal, packedLight, packedOverlay);

            case WEST -> renderQuad(buffer, matrix,
                    0 - depth, 0, 0,
                    0 - depth, 0, 1,
                    0 - depth, 1, 1,
                    0 - depth, 1, 0,
                    0, 0, 1, 1, normal, packedLight, packedOverlay);

            case UP -> renderQuad(buffer, matrix,
                    0, 1 + depth, 1,
                    1, 1 + depth, 1,
                    1, 1 + depth, 0,
                    0, 1 + depth, 0,
                    0, 0, 1, 1, normal, packedLight, packedOverlay);

            case DOWN -> renderQuad(buffer, matrix,
                    0, 0 - depth, 0,
                    1, 0 - depth, 0,
                    1, 0 - depth, 1,
                    0, 0 - depth, 1,
                    0, 0, 1, 1, normal, packedLight, packedOverlay);
        }
    }

    private void renderQuad(VertexConsumer buffer, Matrix4f matrix,
                            float x1, float y1, float z1,
                            float x2, float y2, float z2,
                            float x3, float y3, float z3,
                            float x4, float y4, float z4,
                            float u1, float v1, float u2, float v2,
                            Vector3f normal, int packedLight, int packedOverlay) {
        buffer.addVertex(matrix, x1, y1, z1)
                .setColor(255, 255, 255, 255)
                .setUv(u1, v1)
                .setOverlay(packedOverlay)
                .setLight(packedLight)
                .setNormal(normal.x(), normal.y(), normal.z());

        buffer.addVertex(matrix, x2, y2, z2)
                .setColor(255, 255, 255, 255)
                .setUv(u2, v1)
                .setOverlay(packedOverlay)
                .setLight(packedLight)
                .setNormal(normal.x(), normal.y(), normal.z());

        buffer.addVertex(matrix, x3, y3, z3)
                .setColor(255, 255, 255, 255)
                .setUv(u2, v2)
                .setOverlay(packedOverlay)
                .setLight(packedLight)
                .setNormal(normal.x(), normal.y(), normal.z());

        buffer.addVertex(matrix, x4, y4, z4)
                .setColor(255, 255, 255, 255)
                .setUv(u1, v2)
                .setOverlay(packedOverlay)
                .setLight(packedLight)
                .setNormal(normal.x(), normal.y(), normal.z());
    }

}