package dev.codedsakura.blossom.back.data;

import com.google.gson.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.lang.reflect.Type;

public class ServerWorldSerializer implements JsonSerializer<ServerWorld>, JsonDeserializer<ServerWorld> {
    public static MinecraftServer server;

    @Override
    public ServerWorld deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return server.getWorld(RegistryKey.of(RegistryKeys.WORLD, Identifier.of(jsonElement.getAsString())));
    }

    @Override
    public JsonElement serialize(ServerWorld serverWorld, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(serverWorld.getRegistryKey().getValue().toString());
    }
}
