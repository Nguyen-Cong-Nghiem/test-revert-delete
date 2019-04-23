package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import vn.velacorp.eventdispatcher.IEvent;

public class UpdateTaskPickUpWhenRequestInStateCancelledEvent extends BaseProtobufKafkaEvent implements IEvent {
    public JSONObject before;
    public JSONObject after;
    public UpdateTaskPickUpWhenRequestInStateCancelledEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
        before = data.getJSONObject("before");
        after = data.getJSONObject("after");
    }
}
