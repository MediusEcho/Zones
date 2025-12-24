package net.mediusecho.zones.flags

import org.bukkit.entity.Player

class EntryFlag(isAllowed: Boolean = true): BooleanFlag(FlagID.ENTRY, isAllowed) {

    var predicate: (Player) -> Boolean = { true }
}