package net.mediusecho.zones

import net.mediusecho.zones.common.BlockVector
import net.mediusecho.zones.zones.CuboidZone
import net.mediusecho.zones.zones.PolygonZone
import org.junit.jupiter.api.Test

class TestZone {

    private val zoneStorage: ZoneStorage = ZoneStorage()

    @Test
    fun testCuboid() {
        val zone = CuboidZone(BlockVector(-104, 86, 55), BlockVector(-102, 84, 55))
        println("chunks: ${zone.chunks.size}")
    }

    @Test
    fun fuck() {
        for (i in 1..1) {
            println(i)
        }
    }

    @Test
    fun testPolygon() {
        val zone = PolygonZone(arrayOf(
            BlockVector(0, 0, 0),
            BlockVector(3, 0, 0),
            BlockVector(3, 0, 2),
            BlockVector(4, 0, 2),
            BlockVector(4, 0, 4),
            BlockVector(2, 0, 4),
            BlockVector(2, 0, 2),
            BlockVector(0, 0, 2)
        ))

        println(zone.intersects(BlockVector(2, 0, 0)))
    }

    @Test
    fun testMerge() {
        println(mergeAlternately("ab", "pqrs"))
    }

    fun mergeAlternately(word1: String, word2: String): String {

    }
}