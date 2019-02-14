package com.github.mdashl.rankedhelper.commandhandler

import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraftforge.client.ClientCommandHandler

abstract class Command(private val alias: String) : CommandBase() {

    override fun getCommandName(): String = alias

    override fun getCommandUsage(sender: ICommandSender): String = "/$alias"

    override fun canCommandSenderUseCommand(sender: ICommandSender): Boolean = true

    override fun processCommand(sender: ICommandSender, args: Array<out String>): Unit = execute(args.toList())

    abstract fun execute(args: List<String>)

    fun register(): Unit = ClientCommandHandler.instance.registerCommand(this)

}
