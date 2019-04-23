package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;

public abstract class BaseKafkaEvent {
    protected String topic;
    protected long offset;
    protected String key;
    protected int partition;
    protected byte[] value;
    Logger logger;

    public BaseKafkaEvent(ConsumerRecord<String,byte[]> record) {
        this.topic = record.topic();
        this.offset = record.offset();
        this.partition = record.partition();
        this.value = record.value();
    }
}
