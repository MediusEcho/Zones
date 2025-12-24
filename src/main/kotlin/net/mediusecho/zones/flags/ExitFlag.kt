package net.mediusecho.zones.flags

import org.bukkit.entity.Player

class ExitFlag(isAllowed: Boolean): BooleanFlag(FlagID.EXIT, isAllowed) {

    var predicate: (Player) -> Boolean = { true }
}