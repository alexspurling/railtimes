package mvt.feed /**
 * Created by alex on 31/05/14.
 */
import net.ser1.stomp.Listener;
import java.util.Map;

/**
 * Example listener process that receives messages
 * in JSON format from the Network Rail ActiveMQ
 *
 * @author Martin.Swanson@blackkitetechnology.com
 */
public class MyListener : Listener {
    override fun message(header: kotlin.Map<out Any?, Any?>?, body: String?) {
        System.out.println("| Got header: " + header)
        System.out.println("| Got body: " + body)
    }
}