package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import vn.velacorp.eventdispatcher.IEvent;

public class TransportationTaskChangeStateEvent extends BaseProtobufKafkaEvent implements IEvent {
    private JSONObject before;
    private JSONObject after;

    public TransportationTaskChangeStateEvent(ConsumerRecord<String,byte[]> record) {
        super(record);
        before = data.getJSONObject("before");
        after = data.getJSONObject("after");

        logger = LoggerFactory.getLogger(TransportationTaskChangeStateEvent.class.getName());
    }

    public JSONObject getBefore() {
        return before;
    }

    public JSONObject getAfter() {
        return after;
    }
}
