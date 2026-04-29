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