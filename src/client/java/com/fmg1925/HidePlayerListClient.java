package com.fmg1925;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class HidePlayerListClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            KeyBinding tab = client.options.playerListKey;
            KeyBinding laOtraMierda = client.options.socialInteractionsKey;
            tab.setBoundKey(InputUtil.UNKNOWN_KEY);
            laOtraMierda.setBoundKey(InputUtil.UNKNOWN_KEY);
            KeyBinding.updateKeysByCode();
        });
    }
}