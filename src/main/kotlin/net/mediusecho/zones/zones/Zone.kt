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
import net.mediusecho.zones.flags.Flag
import org.bukkit.entity.Player

abstract class Zone(val boundingBox: BoundingBox) {

    var priority: Int = 1

    private val flagList: ArrayList<Flag> = ArrayList()
    val flags: List<Flag>
        get() = flagList

    inline fun <reified T: Flag> findFlags(name: String = ""): List<T> = flags.filterIsInstance<T>()
        .filter { name.isEmpty() || it.name == name }

    private val playersInside: MutableList<Player> = ArrayList()
    protected val chunkSet: MutableSet<ChunkVector> = HashSet()

    //
    // Abstract
    //

    abstract fun intersects(vector: BlockVector): Boolean

    //
    // Public
    //

    fun addFlag(flag: Flag) {
        flagList.add(flag)
        flag.parent = this
    }

    fun removeFlag(flag: Flag) {
        flagList.remove(flag)
    }

    operator fun contains(player: Player): Boolean = player in playersInside

    //
    // Internal
    //

    internal val chunks: Array<ChunkVector>
        get() = chunkSet.toTypedArray()

    internal fun addPlayer(player: Player) {
        playersInside.add(player)
    }

    internal fun removePlayer(player: Player) {
        playersInside.remove(player)
    }
}