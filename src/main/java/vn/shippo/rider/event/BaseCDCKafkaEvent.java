package vn.shippo.rider.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.exception.EventParsingException;

import java.io.IOException;

public abstract class BaseCDCKafkaEvent extends BaseKafkaEvent {
    protected char _CRUDOperation;
    protected JSONObject _payload;
    protected JSONObject _before;
    protected JSONObject _after;
    protected ObjectMapper mapper = new ObjectMapper();
    private Logger logger;

    public BaseCDCKafkaEvent(ConsumerRecord<String, byte[]> record) {
        super(record);
        logger = LoggerFactory.getLogger(BaseCDCKafkaEvent.class.getName());
        try {
            _payload = getPayloadFromCDC(record.value());
            _CRUDOperation = parseOperator(_payload);
            _mapBeforeAndAfter();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static char parseOperator(JSONObject payload) {
        String type = payload.getString("op");
        return type.charAt(0);
    }

    public static JSONObject getPayloadFromCDC(byte[] recordValue) throws NullPointerException {
        JSONObject event = new JSONObject(new String(recordValue));

        if (event.isNull("payload")) {
            throw new NullPointerException("Payload is empty");
        }
        return (JSONObject) event.get("payload");
    }

    protected void _mapBeforeAndAfter() throws EventParsingException {
        switch (_CRUDOperation) {
            case 'c': {//create
                _after = _payload.getJSONObject("after");
                if (_after == null) {
                    throw new EventParsingException("Can not parse CDC after data");
                }
                break;
            }
            case 'u': {//update
                _after = _payload.getJSONObject("after");
                _before = _payload.getJSONObject("before");
                if (_after == null) {
                    throw new EventParsingException("Can not parse CDC after data");
                }

                if (_before == null) {
                    throw new EventParsingException("Can not parse CDC before data");
                }

                break;
            }
        }
    }

    public <T> T mappingEvent(Class<T> tClass, JSONObject object){
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            if (object.toString().equals("null")) return null;
            return mapper.readValue(object.toString(), tClass);
        }catch (IOException e){
            logger.error(object.toString() + "\n" + e.toString());
            throw new EventParsingException("Can't parse data for Object AFTER");
        }
    }

    public JSONObject getBefore(){
        return _before;
    }

    public JSONObject getAfter(){
        return _after;
    }

    public boolean isCreate(){
        return this._CRUDOperation == 'c';
    }

    public boolean isUpdate(){
        return this._CRUDOperation == 'u';
    }
}
