package com.github.mdashl.rankedhelper.commands

import com.github.mdashl.rankedhelper.commandhandler.Command
import com.github.mdashl.rankedhelper.thePlayer
import com.github.mdashl.rankedhelper.utils.sendMessage

object WDRCommand : Command("wdr") {
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            sendMessage("§cPlease, provide a player to report!")
            return
        }

        val player = args.first()

        thePlayer.sendChatMessage("/wdr $player ka antikb reach speed fly autoclicker")
    }
}