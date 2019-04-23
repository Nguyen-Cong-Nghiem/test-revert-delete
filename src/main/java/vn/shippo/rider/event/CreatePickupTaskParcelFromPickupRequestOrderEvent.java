package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import vn.velacorp.eventdispatcher.IEvent;

public class CreatePickupTaskParcelFromPickupRequestOrderEvent extends BaseProtobufKafkaEvent implements IEvent {
    public JSONObject after;
    public JSONArray parcelList;
    public String _crudOperator;

    public CreatePickupTaskParcelFromPickupRequestOrderEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
        after = data.getJSONObject("after");
        _crudOperator = super.action;
        parcelList=data.getJSONArray("parcels");
    }
}
