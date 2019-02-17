package com.github.mdashl.rankedhelper.reference

object Patterns {
    const val GAME_START = "Teaming is not allowed on Ranked mode!"
    const val WHO_OUTPUT = "Mode: RANKED"
    val WHO_OUTPUT_PLAYER = "Team #(?<team>\\d+): (?<player>.+)".toRegex()
}
