package com.github.mdashl.rankedhelper

import com.github.mdashl.hypixel.HypixelAPI
import com.github.mdashl.hypixel.HypixelPlayer
import com.github.mdashl.hypixel.SkyWars
import com.github.mdashl.hypixel.activeKitRanked
import com.github.mdashl.hypixel.displayname
import com.github.mdashl.hypixel.formattedDisplaynameColorized
import com.github.mdashl.hypixel.level
import com.github.mdashl.hypixel.stats
import com.github.mdashl.rankedhelper.commands.WDRCommand
import com.github.mdashl.rankedhelper.listeners.Listener
import com.github.mdashl.rankedhelper.utils.getHypixelPlayer
import com.github.mdashl.rankedhelper.utils.sendMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.jsoup.Jsoup
import java.util.UUID
import java.util.concurrent.Executors

val thePlayer: EntityPlayerSP
    get() = Minecraft.getMinecraft().thePlayer

@Mod(modid = "rankedhelper", name = "RankedHelper", version = "1.0")
class RankedHelper {

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(Listener)

        registerCommands()
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        HypixelAPI.apiKey = UUID.fromString("67d67cc8-f0d8-4de9-820b-7297fae3e017")
    }

    private fun registerCommands() {
        WDRCommand.register()
    }

    companion object {
        fun sendInformation(team: String, name: String) {
            GlobalScope.launch(Executors.newFixedThreadPool(4).asCoroutineDispatcher()) {
                val player = getHypixelPlayer(name)

                if (player == null) {
                    sendMessage("Team #$team: §7$name §cNICKED")
                    return@launch
                }

                val rating = rating(player)
                val level = player.level.toInt()
                val kit = player.stats.SkyWars.activeKitRanked.localizedName

                sendMessage(
                    "Team #$team: ${player.formattedDisplaynameColorized} §e$rating §f[§a$level§f] §f(§c$kit§f)"
                )
            }
        }

        private fun rating(player: HypixelPlayer): String {
            Jsoup.connect("https://hypixel.net/player/${player.displayname}").get().run {
                return select("#stats-content-skywars > table > tbody > tr:nth-child(14) > td.statValue")
                    ?.first()
                    ?.text()
                    ?: "§4-"
            }
        }
    }

}
