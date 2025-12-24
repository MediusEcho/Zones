package net.mediusecho.zones.common

import org.bukkit.Chunk
import org.bukkit.Location

fun Chunk.toChunkVector(): ChunkVector = ChunkVector(x, z)

fun Location.toBlockVector(): BlockVector = BlockVector(blockX, blockY, blockZ)

fun Location.blockCenter(): Location = Location(world, blockX + 0.5, blockY.toDouble(), blockZ + 0.5, yaw, pitch)