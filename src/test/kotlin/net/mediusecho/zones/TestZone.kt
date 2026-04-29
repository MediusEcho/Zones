package net.mediusecho.zones

import net.mediusecho.zones.common.BlockVector
import net.mediusecho.zones.zones.CuboidZone
import net.mediusecho.zones.zones.PolygonZone
import org.junit.jupiter.api.Test

class TestZone {

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
}