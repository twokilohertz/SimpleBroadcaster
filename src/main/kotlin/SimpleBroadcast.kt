package today.theladpack.simplebroadcast

import org.bukkit.plugin.java.JavaPlugin

class SimpleBroadcast : JavaPlugin() {
    override fun onEnable() {
        // Store the default config on first run
        //this.saveDefaultConfig()

        // Start the scheduled message task
        scheduleTasks()

        logger.info("Plugin enabled, messages broadcasting")
    }

    override fun onDisable() {
        // Cancel message broadcasting task
        if (!task.isCancelled) task.cancel()

        logger.info("Plugin disabled")
    }

    private val timeBetweenMessages = this.config.getInt("interval", 3)
    val listOfMessages: MutableList<String>? = this.config.getList("messages") as MutableList<String>?
    //val listOfIgnoredUUIDs: MutableList<String>? = this.config.getList("ignore") as MutableList<String>?
    private val task = BroadcastTask(this)

    private fun scheduleTasks() {
        task.runTaskTimer(this, 20, timeBetweenMessages.toLong() * 20)
    }
}