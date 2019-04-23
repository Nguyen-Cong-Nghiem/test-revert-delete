package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.velacorp.eventdispatcher.IEvent;

public class UserAuthFromDBZEvent extends BaseCDCKafkaEvent implements IEvent {

    public UserAuthFromDBZEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
    }
}
