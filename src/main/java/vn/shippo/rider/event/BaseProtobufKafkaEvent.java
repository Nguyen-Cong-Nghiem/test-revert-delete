package vn.shippo.rider.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import protos.MessageProtos;
import vn.shippo.rider.event.exception.EventNotDefinedException;

import java.io.IOException;

public abstract class BaseProtobufKafkaEvent extends BaseKafkaEvent {
    protected String type;

    protected String action;

    protected String state;

    protected JSONObject data;

    protected ObjectMapper mapper = new ObjectMapper();

    public BaseProtobufKafkaEvent(ConsumerRecord<String,byte[]> record) {
        super(record);
        _parseMessageFromBinary(record.value());
    }

    public void _parseMessageFromBinary(final byte[] binaryMessage) {
        try
        {
            MessageProtos.Message message = protos.MessageProtos.Message.parseFrom(binaryMessage);
            type = message.getType();
            action = message.getAction();
            state = message.getState();
            data = new JSONObject(String.valueOf(message.getData()));

        }
        catch (InvalidProtocolBufferException ipbe) {
            logger.error("ERROR: Unable to instantiate MessageProtos.Message instance from provided binary data", ipbe);
        }
    }

    public <T> T mappingEvent(Class<T> tClass, JSONObject object) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            if (object.toString().equals("null")) return null;
            return mapper.readValue(object.toString(), tClass);
        } catch (IOException e) {
            logger.error(object.toString() + "\n" + e.toString());
            throw new EventNotDefinedException("Can not parse CDC after data");
        }
    }

    public boolean isCreate() {
        return this.action.equals("c");
    }

    public boolean isUpdate() {
        return this.action.equals("u");
    }
}
