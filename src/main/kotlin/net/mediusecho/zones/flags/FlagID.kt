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