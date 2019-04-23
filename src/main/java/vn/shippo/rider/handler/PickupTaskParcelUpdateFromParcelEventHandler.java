package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.PickupTaskParcel;
import vn.shippo.rider.event.ParcelUpdateEvent;
import vn.shippo.rider.event.evententity.Parcel;
import vn.shippo.rider.service.PickupTaskParcelService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

import java.util.ArrayList;
import java.util.List;

public class PickupTaskParcelUpdateFromParcelEventHandler implements IEventHandler<ParcelUpdateEvent> {
    public static final Logger logger
            = LoggerFactory.getLogger(PickupTaskParcelUpdateFromParcelEventHandler.class.getSimpleName());
    private static EbeanServer riderServer = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

    @Override
    public void on(ParcelUpdateEvent event) {
        Transaction transaction = riderServer.beginTransaction();
        try {
            if (event.isUpdate()) {
                Parcel before = event.mappingEvent(Parcel.class, event.getBefore());
                Parcel after = event.mappingEvent(Parcel.class, event.getAfter());
                logger.info("handling event PickupTaskParcelUpdateFromParcelEventHandler before:{}, after: {}", before, after);
                if (!(event.getBefore() == null || event.getAfter() == null)) {
                    if (TransportTaskUpdateFromParcelHandler.isUpdateParcelInfo(before, after)) {
                        List<PickupTaskParcel> beforePickupTaskParcelChangeInfos = getPickupTaskParcels(after);
                        List<PickupTaskParcel> afterPickupTaskParcelChangeInfos = new ArrayList<>();
                        PickupTaskParcelService.updateParcelInfoIntoPickupTaskParcel(beforePickupTaskParcelChangeInfos, afterPickupTaskParcelChangeInfos, transaction, before, after);
                    }
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        }

    }

    private List<PickupTaskParcel> getPickupTaskParcels(Parcel parcel) {
        return riderServer.find(PickupTaskParcel.class)
                .where()
                .and().eq("barcode", parcel.getBarcode())
                .findList();
    }

}
