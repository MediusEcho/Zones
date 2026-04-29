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