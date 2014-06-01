package mvt.feed

import net.ser1.stomp.Client

public class MvtFeed {

    //Network Rail ActiveMQ server
    private val SERVER: String = "datafeeds.networkrail.co.uk"

    // Server port for STOMP clients
    private val PORT: Int = 61618

    // Your account username, typically an email address
    private val USERNAME: String = "alexspurling@gmail.com"

    // Your account password
    private val PASSWORD: String = "9(kf02Jjdw"

    // Example topic (this one is for Southern Train Movements)
    private val TOPIC: String = "/topic/TRAIN_MVT_ALL_TOC"


    /*
     * Connect to a single topic and subscribe a listener
     * @throws Exception Too lazy to implement exception handling....
     */
    fun go() {
        System.out.println("| Connecting...")

        val client = Client(SERVER, PORT, USERNAME, PASSWORD)
        if (client.isConnected()) {
            System.out.println("| Connected to " + SERVER + ":" + PORT)
        } else {
            System.out.println("| Could not connect")
            return
        }
        System.out.println("| Subscribing...")
        val listener = MyListener()
        client.subscribe(TOPIC, listener)
        System.out.println("| Subscribed to " + TOPIC)
        System.out.println("| Waiting for message...")
    }

}

public fun main(args: Array<String>) {
    MvtFeed().go()
}