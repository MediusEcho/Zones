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

import net.mediusecho.zones.common.blockCenter
import net.mediusecho.zones.common.toBlockVector
import net.mediusecho.zones.flags.*
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.plugin.java.JavaPlugin

class ZoneManager(private val plugin: JavaPlugin): ZoneStorage(), Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerMove(event: PlayerMoveEvent) {
        event.to?.let {
            val to = it.toBlockVector()
            val player = event.player
            getZonesAtLocation(it).forEach { zone ->

                // Entry Check
                if (zone.intersects(to)) {
                    zone.findFlags<EntryFlag>().forEach { flag ->
                        if (!flag.predicate(player)) {

                            // Prevent a player from getting stuck in this zone endlessly
                            if (player in zone) {
                                return
                            }

                            event.setTo(event.from.blockCenter())
                            zone.findFlags<FuncFlag>(FlagID.ENTRY_DENIED_FUNC).forEach { f -> f.invoke(player) }
                            return
                        }
                    }

                    if (player !in zone) {
                        zone.addPlayer(event.player)
                        zone.findFlags<StringFlag>(FlagID.GREETING).forEach { flag -> player.sendMessage(flag.value) }
                        zone.findFlags<FuncFlag>(FlagID.ENTRY_FUNC).forEach { flag -> flag.invoke(player) }
                    }
                }

                // Exit Check
                else {
                    zone.findFlags<ExitFlag>().forEach { flag ->
                        if (!flag.predicate(player)) {

                            // Prevent a player from getting stuck in this zone endlessly
                            if (player !in zone) {
                                return
                            }

                            event.setTo(event.from.blockCenter())
                            zone.findFlags<FuncFlag>(FlagID.EXIT_DENIED_FUNC).forEach { flag -> flag.invoke(player) }
                            return
                        }
                    }

                    if (player in zone) {
                        zone.removePlayer(player)
                        zone.findFlags<StringFlag>(FlagID.FAREWELL).forEach { flag -> player.sendMessage(flag.value) }
                        zone.findFlags<FuncFlag>(FlagID.EXIT_FUNC).forEach { flag -> flag.invoke(player) }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerTeleport(event: PlayerTeleportEvent) {
        event.to?.let {
            val to = it.toBlockVector()
            val player = event.player

            getZonesAtLocation(it).forEach { zone ->

                // Teleport In
                if (zone.intersects(to)) {
                    zone.findFlags<EntryFlag>().forEach { flag ->
                        if (!flag.predicate(player)) {
                            if (player !in zone) {
                                zone.findFlags<FuncFlag>(FlagID.ENTRY_DENIED_FUNC).forEach { f -> f.invoke(player) }
                                event.isCancelled = true
                            }
                        }
                    }
                }

                // Teleport Out
                else {
                    zone.findFlags<ExitFlag>().forEach { flag ->
                        if (!flag.predicate(player)) {
                            if (player in zone) {
                                zone.findFlags<FuncFlag>(FlagID.EXIT_DENIED_FUNC).forEach { f -> f.invoke(player) }
                                event.isCancelled = true
                            }
                        }
                    }
                }
            }
        }
    }
}