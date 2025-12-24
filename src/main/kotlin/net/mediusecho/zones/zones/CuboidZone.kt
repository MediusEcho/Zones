package net.mediusecho.zones.zones

import net.mediusecho.zones.common.BlockVector
import net.mediusecho.zones.common.BoundingBox
import net.mediusecho.zones.common.ChunkVector

open class CuboidZone(minVector: BlockVector, maxVector: BlockVector): Zone(BoundingBox(minVector, maxVector)) {

    init {
        minVector.iterateChunks(maxVector) { x, z ->
            chunkSet.add(ChunkVector(x, z))
        }
    }

    override fun intersects(vector: BlockVector): Boolean {
        return boundingBox.intersects(vector)
    }
}