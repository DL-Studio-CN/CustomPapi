package me.mical.custompapi.command.sub

import io.izzel.taboolib.module.command.base.Argument
import io.izzel.taboolib.module.command.base.BaseSubCommand
import io.izzel.taboolib.module.locale.TLocale
import me.mical.custompapi.CustomPapi
import me.mical.custompapi.util.Util.toCPPlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandTake : BaseSubCommand() {

    override fun getArguments() = arrayOf(
            Argument("内部ID") { CustomPapi.getCPConfig().getGlobalMap().map { it.key } },
            Argument("数量", false)
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>) {
        val player = if (sender is Player) sender else null
        if (player != null)
            kotlin.runCatching {
                args[1].let {
                    player.toCPPlayer().takeDum(args[0], it.toInt())
                    TLocale.sendTo(player, "Successful")
                }
            }.onFailure { TLocale.sendTo(player, "Command.IllegalArgument") }
    }
}