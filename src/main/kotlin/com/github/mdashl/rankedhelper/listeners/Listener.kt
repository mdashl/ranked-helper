package com.github.mdashl.rankedhelper.listeners

import com.github.mdashl.rankedhelper.RankedHelper
import com.github.mdashl.rankedhelper.playerSP
import com.github.mdashl.rankedhelper.reference.Patterns
import com.github.mdashl.rankedhelper.utility.get
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object Listener {

    var rankedGame: Boolean = false

    @SubscribeEvent
    fun onClientChatReceived(event: ClientChatReceivedEvent) {
        val message = event.message.unformattedText

        if (event.type != 0.toByte())
            return


        when {
            message == Patterns.GAME_START -> {
                rankedGame = true
                playerSP.sendChatMessage("/who")

                event.isCanceled = true
            }
            message == Patterns.WHO_OUTPUT -> {
                event.isCanceled = true
            }
            Patterns.WHO_OUTPUT_PLAYER.matches(message) -> {
                if (!rankedGame)
                    return


                event.isCanceled = true

                val match = Patterns.WHO_OUTPUT_PLAYER.find(message)!!

                val team = match["team"]
                val player = match["player"]

                RankedHelper.sendInformation(team, player)
            }
        }
    }

    @SubscribeEvent
    fun onWorldLoad(event: WorldEvent.Load) {
        rankedGame = false
    }

}
