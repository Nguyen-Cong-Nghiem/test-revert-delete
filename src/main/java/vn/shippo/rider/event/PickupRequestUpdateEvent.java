package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.velacorp.eventdispatcher.IEvent;

public class PickupRequestUpdateEvent extends BaseCDCKafkaEvent implements IEvent {
    public PickupRequestUpdateEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
    }
}
