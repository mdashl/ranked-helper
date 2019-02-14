package com.github.mdashl.rankedhelper.utils

operator fun MatchResult.get(name: String): String = groups[name]!!.value
