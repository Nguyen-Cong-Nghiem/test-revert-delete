package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.velacorp.eventdispatcher.IEvent;

public class MerchantChangeInfoEvent extends BaseCDCKafkaEvent implements IEvent {
    public MerchantChangeInfoEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
    }
}
