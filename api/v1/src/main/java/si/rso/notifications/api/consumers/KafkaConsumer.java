package si.rso.notifications.api.consumers;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import si.rso.notifications.lib.NotificationsStreamConfig;
import si.rso.notifications.services.EventStreamingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaConsumer {
    
    private static final Logger LOG = LogManager.getLogger(KafkaConsumer.class.getSimpleName());

    @Inject
    private EventStreamingService eventStreamingService;
    
    @StreamListener(topics = {NotificationsStreamConfig.KAFKA_NOTIFICATIONS_CHANNEL})
    public void onMessage(ConsumerRecord<String, String> record) {
        LOG.info("Consumed Kafka record!");
        eventStreamingService.handleMessage(record.value());
    }

}
