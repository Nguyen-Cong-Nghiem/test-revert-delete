package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.velacorp.eventdispatcher.IEvent;

public class ParcelUpdateEvent extends BaseCDCKafkaEvent implements IEvent {

    public ParcelUpdateEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
    }
}
