package vn.shippo.rider;

import org.apache.kafka.common.config.ConfigException;
import vn.shippo.rider.event.KafkaRecordHandler;
import vn.velacorp.Registry;
import vn.velacorp.kafka.SingleConsumer;

import java.util.*;

public class Factory {
    @SuppressWarnings("unchecked")
    public static SingleConsumer getKafkaConsumer() throws ConfigException, NullPointerException {
        Properties prop = Registry.getProperties();
        String consumerGroup = prop.getProperty("kafka.consumer.group");
        String brokers = prop.getProperty("kafka.brokers");
        List<String>topics = Arrays.asList(prop.getProperty("kafka.topics").split(",\\s*"));
        Long pollTime = null != prop.getProperty("kafka.consumer.poll")?
                Integer.parseInt(prop.getProperty("kafka.consumer.poll"))
                : Long.MAX_VALUE;

        if (null == consumerGroup || null == brokers || topics.size() < 1) {
            throw new ConfigException("Kafka configs were wrong, plz check config.properties files");
        }

        //add option
        Map<String, Object> options = new HashMap<>();
        options.put("fetch.max.bytes", "12500000"); //100 MB
        options.put("max.partition.fetch.bytes", "12500000"); //100 MB
        options.put("max.poll.records", prop.getProperty("kafka.consumer.poll_record", "10"));
        options.put("auto.offset.reset", prop.getProperty("kafka.auto.offset.reset", "latest"));

        return new SingleConsumer(KafkaRecordHandler.class,
                brokers, consumerGroup, topics, pollTime,
                options);
    }
}
