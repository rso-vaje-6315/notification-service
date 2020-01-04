package si.rso.notifications.api.consumers;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.metrics.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metric;
import si.rso.event.streaming.EventStreamMessage;
import si.rso.notifications.lib.NotificationsStreamConfig;
import si.rso.notifications.services.EventStreamingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaConsumer {
    
    private static final Logger LOG = LogManager.getLogger(KafkaConsumer.class.getSimpleName());

    @Inject
    private EventStreamingService eventStreamingService;
    
    @Inject
    @Metric(name = "notifications-counter")
    private ConcurrentGauge counter;
    
    @StreamListener(topics = {NotificationsStreamConfig.NOTIFICATIONS_CHANNEL})
    public void onMessage(ConsumerRecord<String, EventStreamMessage> record) {
        counter.inc();
        LOG.info("Consumed Kafka record!");
        eventStreamingService.handleMessage(record.value());
    }

}
