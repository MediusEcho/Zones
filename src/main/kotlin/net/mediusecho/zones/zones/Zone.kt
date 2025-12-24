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