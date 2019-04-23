package vn.shippo.rider.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.shippo.rider.event.exception.EventNotDefinedException;
import vn.velacorp.eventdispatcher.IEvent;
import vn.velacorp.kafka.IRecordHandler;

public class KafkaRecordHandler implements IRecordHandler {
    @Override
    @SuppressWarnings("unchecked")
    public IEvent createEvent(ConsumerRecord record) {
        switch (record.topic()) {
            case "shippo.rider_service.topic.task.switch.status":{
                return new TransportationTaskChangeStateEvent(record);
            }
            case "operation_service.public.parcels":{
                return  new ParcelUpdateEvent(record);
            }
            case "operation_service.pickup_request.state.updation": {
                return new UpdateTaskPickUpWhenRequestInStateCancelledEvent(record);
            }
            case "auth_service.public.users":{
                return new UserAuthFromDBZEvent(record);
            }
            case "auth.create.rider.fail": {
                return new UserAuthFailEvent(record);
            }
            case "operation_service.public.pickup_requests":{
                return new PickupRequestUpdateEvent(record);
            }
            case "operation_service.created.pickup_request_orders":
                return new CreatePickupTaskParcelFromPickupRequestOrderEvent(record);

            case "rider_service.public.transportation_tasks":
                return new TransportationTaskChangeEvent(record);

            case "delivery_service.public.merchants":
                return new MerchantChangeInfoEvent(record);

        }

        throw new EventNotDefinedException();
    }
}
