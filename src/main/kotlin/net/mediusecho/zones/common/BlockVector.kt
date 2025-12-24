package net.mediusecho.zones.common

data class BlockVector(val x: Int, val y: Int, val z: Int) {

    operator fun plus(other: BlockVector): BlockVector = BlockVector(x + other.x, y + other.y, z + other.z)

    operator fun plus(other: Int): BlockVector = BlockVector(x + other, y + other, z + other)

    operator fun minus(other: BlockVector): BlockVector = BlockVector(x - other.x, y - other.y, z - other.z)

    operator fun minus(other: Int): BlockVector = BlockVector(x - other, y - other, z - other)

    operator fun div(other: BlockVector): BlockVector = BlockVector(x / other.x, y / other.y, z / other.z)

    operator fun div(other: Int): BlockVector = BlockVector(x / other,y / other, z / other)

    operator fun times(other: BlockVector): BlockVector = BlockVector(x * other.x, y * other.y, z * other.z)

    operator fun times(other: Int): BlockVector = BlockVector(x * other, y * other, z * other)

    fun center(other: BlockVector): BlockVector = this + ((other - this) / 2)

    fun iterateChunks(other: BlockVector, block:(Int, Int) -> Unit) {
        for (xx in x..other.x) {
            for (zz in z..other.z) {
                block(xx shr 4, zz shr 4)
            }
        }
    }
}