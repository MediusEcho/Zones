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
package net.mediusecho.zones.zones

import net.mediusecho.zones.common.BlockVector
import net.mediusecho.zones.common.BoundingBox
import net.mediusecho.zones.common.ChunkVector

class PolygonZone(private val points: Array<BlockVector>): Zone(BoundingBox.fromPolygon(*points)) {

    init {
        boundingBox.min.iterateChunks(boundingBox.max) { x, z ->
            chunkSet.add(ChunkVector(x, z))
        }
    }

    override fun intersects(vector: BlockVector): Boolean {

        // Don't bother checking the polygon if the bounding box does not have a hit
        if (!boundingBox.intersects(vector)) {
            return false
        }

        var inside = false
        var j = points.size - 1
        for (i in points.indices) {
            if ((points[i].z > vector.z) != (points[j].z > vector.z) &&
                vector.x < (points[j].x - points[i].x) * (vector.z - points[i].z) / (points[j].z - points[i].z) + points[i].x) {
                inside = !inside
            }
            j = i
        }
        return inside
    }
}