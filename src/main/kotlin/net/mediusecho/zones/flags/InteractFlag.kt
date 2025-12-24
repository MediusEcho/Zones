package net.mediusecho.zones.flags

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent

class InteractFlag: Flag(FlagID.INTERACT) {

    fun test(player: Player, event: PlayerInteractEvent): Boolean {
        return false
    }
}