package net.mediusecho.zones.flags

object FlagID {

    const val ENTRY: String = "entry"
    const val EXIT: String = "exit"
    const val GREETING: String = "greeting"
    const val FAREWELL: String = "farewell"
    const val ENTRY_FUNC: String = "entry_allowed"
    const val EXIT_FUNC: String = "exit_allowed"
    const val ENTRY_DENIED_FUNC: String = "entry_denied_func"
    const val EXIT_DENIED_FUNC: String = "exit_denied_func"
    const val INTERACT: String = "interact"

    val values: List<String> = listOf(
        ENTRY,
        EXIT,
        GREETING,
        FAREWELL,
        ENTRY_FUNC,
        EXIT_FUNC,
        ENTRY_DENIED_FUNC,
        EXIT_DENIED_FUNC,
        INTERACT
    )
}