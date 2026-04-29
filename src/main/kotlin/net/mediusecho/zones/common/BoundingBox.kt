package net.mediusecho.zones.common

import java.io.Serializable
import kotlin.math.max
import kotlin.math.min

class BoundingBox(val min: BlockVector, val max: BlockVector): Serializable {

    val size: BlockVector = (max - min) + 1

    val center: BlockVector
        get() = min + (size / 2)

    fun intersects(vector: BlockVector): Boolean {
        return vector.x >= min.x && vector.y >= min.y && vector.z >= min.z &&
                vector.x <= max.x && vector.y <= max.y && vector.z <= max.z
    }

    fun iterate(block: (Int, Int, Int) -> Unit) {
        for (x in min.x..<max.x) {
            for (y in min.y..<max.y) {
                for (z in min.z..<max.z) {
                    block(x, y, z)
                }
            }
        }
    }

    companion object {

        fun fromPolygon(vararg points: BlockVector): BoundingBox {
            var minX = points[0].x
            var minY = points[0].y
            var minZ = points[0].z
            var maxX = points[0].x
            var maxY = points[0].y
            var maxZ = points[0].z

            for (i in 1 until points.size) {
                val p = points[i]
                minX = min(p.x, minX)
                minY = min(p.y, minY)
                minZ = min(p.z, minZ)
                maxX = max(p.x, maxX)
                maxY = max(p.y, maxY)
                maxZ = max(p.z, maxX)
            }

            return BoundingBox(BlockVector(minX, minY, minZ), BlockVector(maxX, maxY, maxZ))
        }
    }
}