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