package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.CreatePickupTaskParcelFromPickupRequestOrderEvent;
import vn.shippo.rider.event.evententity.Parcel;
import vn.shippo.rider.event.evententity.PickupRequestOrder;
import vn.shippo.rider.service.PickupTaskParcelService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

import java.util.ArrayList;
import java.util.List;

public class CreatePickupTaskParcelHandler implements IEventHandler<CreatePickupTaskParcelFromPickupRequestOrderEvent> {

    private Logger logger = LoggerFactory.getLogger(CreatePickupTaskParcelHandler.class.getSimpleName());

    @Override
    public void on(CreatePickupTaskParcelFromPickupRequestOrderEvent event) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        Transaction transaction = server.beginTransaction();
        try {
            List<Object> parcelListCheck = new ArrayList<>();
            PickupRequestOrder pickupRequestOrder = event.mappingEvent(PickupRequestOrder.class, event.after);
            logger.info("Handling event CreatePickupTaskParcelHandler with pickup_request_orders after {}", event.after);
            JSONArray jArray = event.parcelList;
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    parcelListCheck.add(jArray.getJSONObject(i));
                }
            }
            List<Parcel> parcelListAfterParse = new ArrayList<>();
            for (Object object : parcelListCheck) {
                Parcel parcel = event.mappingEvent(Parcel.class, (JSONObject) object);
                parcelListAfterParse.add(parcel);
            }
            handlePickupRequestOrderEvent(pickupRequestOrder, parcelListAfterParse, transaction, server);
            transaction.commit();
            logger.info("Handler event 'CreatePickupTaskParcelFromPickupRequestOrderEvent' successfully");

        } catch (Exception e) {
            transaction.rollback();
            logger.error("HandlerPickupRequestOrderEvent fail with {}", e.getMessage());
        }


    }

    //region method xu li event
    private void handlePickupRequestOrderEvent(PickupRequestOrder pickupRequestOrder, List<Parcel> parcels, Transaction transaction, EbeanServer server) {
        PickupTaskParcelService.updateTaskDescription(pickupRequestOrder, parcels, server, transaction);
        PickupTaskParcelService.handleCreatePickupTaskParcel(pickupRequestOrder, parcels, server, transaction);
    }
    //endregion
}
