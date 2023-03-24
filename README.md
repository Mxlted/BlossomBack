# BlossomBack

BlossomBack is a Minecraft Fabric mod in the Blossom-series mods that provides /back command and utilities

## Table of contents

- [Dependencies](#dependencies)
- [Config](#config)
- [Commands & their permissions](#commands--their-permissions)
- [Translation keys](#translation-keys)

## Dependencies

* [BlossomLib](https://github.com/BlossomMods/BlossomLib)
* [fabric-permissions-api](https://github.com/lucko/fabric-permissions-api) / [LuckPerms](https://luckperms.net/) /
  etc. (Optional)

## Config

This mod's config file can be found at `config/BlossomMods/BlossomBack.json`, after running the server with
the mod at least once.

`back`: [TeleportProps](#teleportprops) - properties of /back, used for /lastdeath if `lastDeath` is set to `null`  
`lastDeath`: [TeleportProps](#teleportprops) or null - properties of /lastdeath

### TeleportProps

`teleportation`: [TeleportationConfig](https://github.com/BlossomMods/BlossomLib/blob/main/README.md#teleportationconfig)
-
teleportation settings  
`standStill`: int - (seconds), how long the player has to stand still before being teleported  
`cooldown`: int - (seconds), how long the player has to wait after teleporting using this command, before being able to
teleport again

## Commands & their permissions

- `/back` - teleport player to position before previous teleport  
  Permission: `blossom.back` (default: true)
- `/lastdeath` - teleport player to position where they died  
  Permission: `blossom.last-death` (default: false)

## Translation keys

only keys with available arguments are shown, for full list, please see
[`src/main/resources/data/blossom/lang/en_us.json`](src/main/resources/data/blossom/lang/en_us.json)
