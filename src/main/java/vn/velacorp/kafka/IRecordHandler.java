package vn.velacorp.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.velacorp.eventdispatcher.IEvent;

public interface IRecordHandler {
    public IEvent createEvent(ConsumerRecord record);
}
