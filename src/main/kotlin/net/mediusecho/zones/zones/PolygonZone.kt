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