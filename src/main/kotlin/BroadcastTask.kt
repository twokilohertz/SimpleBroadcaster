package today.theladpack.simplebroadcast

import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class BroadcastTask(private val simpleBroadcast: SimpleBroadcast) : BukkitRunnable() {
    private var index = 0

    override fun run() {
        val currentPlayers = Bukkit.getOnlinePlayers()

        if (!currentPlayers.isEmpty()) {
            val players = currentPlayers.iterator()

            while (players.hasNext()) {
                simpleBroadcast.listOfMessages?.get(index)?.let {
                    val player = players.next()

                    player.sendMessage(it)

                    index++

                    if (index >= simpleBroadcast.listOfMessages.size) index = 0
                }
            }
        }
    }
}