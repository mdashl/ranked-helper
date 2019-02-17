package com.github.mdashl.rankedhelper.utility

operator fun MatchResult.get(name: String): String = groups[name]!!.value
