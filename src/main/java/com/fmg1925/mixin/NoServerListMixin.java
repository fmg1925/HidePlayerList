package com.fmg1925.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.List;

@Mixin(MinecraftServer.class)
public class NoServerListMixin {
    private static List<ServerPlayerEntity> WAZA = Collections.emptyList();

    @Redirect(method = "createMetadataPlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;getPlayerList()Ljava/util/List;"))
    private List<ServerPlayerEntity> noServerList(PlayerManager instance) {
        return WAZA;
    }
}
