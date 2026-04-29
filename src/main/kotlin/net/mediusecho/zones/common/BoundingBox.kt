/*
 *
 *   MIT License
 *
 *   Copyright (c) 2026 Jacob (MediusEcho)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 */
package net.mediusecho.zones.common

import org.bukkit.util.Vector
import java.io.Serializable
import kotlin.math.max
import kotlin.math.min

class BoundingBox(val min: BlockVector, val max: BlockVector): Serializable {

    val size: BlockVector = (max - min) + 1

    val center: Vector
        get() {
            val width: Double = min.x + (size.x / 2.0)
            val depth: Double = min.z + (size.z / 2.0)
            val height: Double = min.y + (size.y / 2.0)
            return Vector(width, height, depth)
        }

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

            for (i in 1..< points.size) {
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