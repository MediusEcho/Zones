package net.mediusecho.zones

import net.mediusecho.zones.common.ChunkVector
import net.mediusecho.zones.common.toChunkVector
import net.mediusecho.zones.zones.Zone
import org.bukkit.Location

open class ZoneStorage {

    private val zoneMap: HashMap<ChunkVector, MutableList<Zone>> = HashMap()
    private val emptyZones: List<Zone> = emptyList()

    fun addZone(zone: Zone) {
        zone.chunks.forEach { zoneMap.getOrPut(it) { ArrayList() }.add(zone) }
    }

    fun removeZone(zone: Zone) {
        zone.chunks.forEach { zoneMap[it]?.remove(zone) }
    }

    fun clearZones() {
        zoneMap.clear()
    }

    fun getZonesAtLocation(location: Location): List<Zone> {
        return zoneMap[location.chunk.toChunkVector()]?.sortedBy { it.priority } ?: emptyZones
    }

    fun getZones(): List<Zone> {
        val list = mutableListOf<Zone>()
        zoneMap.values.forEach { zoneList -> zoneList.forEach { list.add(it) } }
        return list
    }
}