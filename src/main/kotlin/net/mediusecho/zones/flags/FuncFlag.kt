package net.mediusecho.zones.flags

import org.bukkit.entity.Player

class FuncFlag(name: String): Flag(name) {

    var invoke: ((Player) -> Unit) = {  }
}