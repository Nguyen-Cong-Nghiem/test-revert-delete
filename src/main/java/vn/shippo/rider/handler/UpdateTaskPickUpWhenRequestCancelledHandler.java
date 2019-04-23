package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.UpdateTaskPickUpWhenRequestInStateCancelledEvent;
import vn.shippo.rider.event.evententity.PickupRequest;
import vn.shippo.rider.service.UpdateTaskPickupWhenRequestCancelledService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

public class UpdateTaskPickUpWhenRequestCancelledHandler implements IEventHandler<UpdateTaskPickUpWhenRequestInStateCancelledEvent> {
    private Logger logger = LoggerFactory.getLogger(UpdateTaskPickUpWhenRequestCancelledHandler.class.getSimpleName());
    @Override
    public void on(UpdateTaskPickUpWhenRequestInStateCancelledEvent event) {
        logger.info("UpdateTaskPickUpWhenRequestCancelledHandler handle event with after {} ", event.after);
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        Transaction transaction = server.beginTransaction();
        try {
            PickupRequest pickupRequest = event.mappingEvent(PickupRequest.class, event.after);
            UpdateTaskPickupWhenRequestCancelledService.handlerChangeStateTaskPickupWhenPickupRequestCancelled(pickupRequest,transaction);
            transaction.commit();
            logger.info("Handler event UpdateTaskPickUpWhenRequestInStateCancelledEvent successfully !!!!!!!!!!!");
        } catch (Exception e) {
            transaction.rollback();
            logger.error("UpdateTaskPickUpWhenRequestCancelledHandler handle event not done with fail : {} ", e.getMessage());
        }
    }
}
