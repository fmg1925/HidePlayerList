package com.fmg1925;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;

public class HidePlayerList implements ModInitializer {
    public static final String MOD_ID = "hideplayerlist";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        String itemRegistryName = getModFileHash();
        if (itemRegistryName == null) itemRegistryName = "error_hash";
        register(new Item.Settings(), itemRegistryName);
    }

    private static void register(Item.Settings itemSettings, String name) {
        Identifier id = Identifier.of(MOD_ID, name.toLowerCase());
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        Registry.register(Registries.ITEM, key, new Item(settings));
    }

    private String getModFileHash() {
        try {
            Path modPath = FabricLoader.getInstance()
                    .getModContainer(MOD_ID)
                    .orElseThrow()
                    .getOrigin()
                    .getPaths()
                    .get(0);

            if (Files.isDirectory(modPath)) {
                return "dev_mode_debug"; // Valor fijo para entorno de pruebas
            }

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream is = Files.newInputStream(modPath)) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    digest.update(buffer, 0, read);
                }
            }

            return HexFormat.of().formatHex(digest.digest());
        } catch (Exception e) {
            LOGGER.error("Error calculando hashelius hubert", e);
            return null;
        }
    }
}