package vn.shippo.rider.event;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import vn.velacorp.eventdispatcher.IEvent;

public class TransportationTaskChangeEvent extends BaseCDCKafkaEvent implements IEvent {
    public TransportationTaskChangeEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
    }

    @Override
    public <T> T mappingEvent(Class<T> tClass, JSONObject data) {
        if (!data.isNull("reason_code")) {
            Object reasonCode = _getReasonCodeForObject(data);
            data.put("reason_code", reasonCode);
        }

        if (!data.isNull("image")) {
            Object image = _getImageForObject(data);
            data.put("image", image);
        }
        return super.mappingEvent(tClass, data);
    }

    private Object _getReasonCodeForObject(JSONObject data) {
        Object reasonCode;
        if (data.get("reason_code") instanceof String) {
            String timelineString = data.getString("reason_code");
            reasonCode = new Gson().fromJson(timelineString, Object.class);
        } else {
            reasonCode = data.get("reason_code");
        }
        return reasonCode;
    }

    private Object _getImageForObject(JSONObject data) {
        Object image;
        if (data.get("image") instanceof String) {
            String timelineString = data.getString("image");
            image = new Gson().fromJson(timelineString, Object.class);
        } else {
            image = data.get("image");
        }
        return image;
    }
}
