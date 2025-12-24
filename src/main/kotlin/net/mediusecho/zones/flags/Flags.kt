package net.mediusecho.zones.flags

object Flags {

    val ENTRY: EntryFlag
        get() = EntryFlag(true)

    val EXIT: ExitFlag
        get() = ExitFlag(true)

    val GREETING: StringFlag
        get() = StringFlag(FlagID.GREETING, "")

    val ENTRY_FUNC: FuncFlag
        get() = FuncFlag(FlagID.ENTRY_FUNC)

    val ENTRY_DENIED_FUNC: FuncFlag
        get() = FuncFlag(FlagID.ENTRY_DENIED_FUNC)

    val EXIT_FUNC: FuncFlag
        get() = FuncFlag(FlagID.EXIT_FUNC)

    val EXIT_DENIED_FUNC: FuncFlag
        get() = FuncFlag(FlagID.EXIT_DENIED_FUNC)

    val FAREWELL: StringFlag
        get() = StringFlag(FlagID.FAREWELL, "")

    val INTERACT: InteractFlag
        get() = InteractFlag()
}