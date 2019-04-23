package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import vn.velacorp.eventdispatcher.IEvent;

public class UserAuthFailEvent extends BaseProtobufKafkaEvent implements IEvent {

    public JSONObject before;
    public JSONObject after;
    public String action=super.action;
    public UserAuthFailEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
        before = data.getJSONObject("before");
        after = data.getJSONObject("after");
        logger = LoggerFactory.getLogger(UserAuthFailEvent.class);

    }
}
