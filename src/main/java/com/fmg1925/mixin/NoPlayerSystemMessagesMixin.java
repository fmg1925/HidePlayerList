package com.fmg1925.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public class NoPlayerSystemMessagesMixin {

    @Redirect(
            method = "onPlayerConnect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"
            )
    )
    private void hideJoinMessage(PlayerManager instance, Text message, boolean overlay, @Local(argsOnly = true) ServerPlayerEntity ignore) {}

    @Redirect(
            method = "updatePlayerLatency",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerManager;sendToAll(Lnet/minecraft/network/packet/Packet;)V"
            )
    )
    private void hideLatency(PlayerManager instance, Packet<?> packet) {}


    @Redirect(
            method = "sendScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"
            )
    )
    private void redirectSendScoreboard(ServerPlayNetworkHandler instance, Packet<?> packet) {}
}
