package com.github.mdashl.rankedhelper.utility

import com.github.mdashl.hypixel.elements.HypixelPlayer
import com.github.mdashl.rankedhelper.thePlayer
import net.minecraft.util.ChatComponentText

fun sendMessage(message: String): Unit = thePlayer.addChatMessage(ChatComponentText(message))


fun hypixelPlayer(name: String): HypixelPlayer? {
    val request =
        HypixelRequest(HypixelRequest.Type.PLAYER, HypixelRequest.Parameter.PLAYER_BY_NAME, name)
    val reply = HypixelAPI.get<PlayerReply>(request)
    val player = reply.player

    return player as? HypixelPlayer
}
