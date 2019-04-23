package vn.shippo.rider.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.json.JSONObject;
import protos.NotificationProtos;
import vn.shippo.rider.event.evententity.MessageEvent;
import vn.velacorp.Registry;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Properties;

public class Utils {
    public static Producer producer;

    static {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", Registry.getProperties().getProperty("kafka.brokers"));
        producer = new Producer(new ProducerConfig(properties));
    }

    public static String _buildEventData(Object objBefore, Object objAfter, String actionBy, String actionType ) {
        ObjectMapper mapper = new ObjectMapper();
        String before = "";
        String after = "";
        try {
            before = mapper.writeValueAsString(objBefore);
            after = mapper.writeValueAsString(objAfter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONObject event2Send = new JSONObject();
        event2Send.put("before", new JSONObject(before));
        event2Send.put("after", new JSONObject(after));
        event2Send.put("action_by", actionBy == null ? JSONObject.NULL : actionBy);
        event2Send.put("action_type", actionType == null ? JSONObject.NULL : actionType);
        return event2Send.toString();
    }

    public static void sendEvent(MessageEvent message, String topic) {
        NotificationProtos.Message msg = NotificationProtos.Message.newBuilder()
                .setEventName(message.getEventName())
                .setEventTime(message.getEventTime().getTime())
                .setEventData(message.getEventData())
                .build();

        byte[] binaryMsg = msg.toByteArray();
        producer.send(new KeyedMessage(topic, binaryMsg));
    }

    public static MessageEvent generateMessage(String eventName, Timestamp eventTime, String eventData) {
        return new MessageEvent(eventName, eventTime, eventData);
    }

    public static String timeStamp2PostgresTimeString(Timestamp timestamp) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(3).toFormatter();
        Instant instant = timestamp.toInstant();
        return formatter.format(instant);
    }
}
