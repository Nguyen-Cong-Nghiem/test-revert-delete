package vn.shippo.rider.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.UserAuthFailEvent;
import vn.shippo.rider.event.evententity.UserAuth;
import vn.shippo.rider.service.RiderService;
import vn.velacorp.eventdispatcher.IEventHandler;

public class UpdatedAndDeletedRiderFromEventHandler implements IEventHandler<UserAuthFailEvent> {
    Logger logger = LoggerFactory.getLogger(UpdatedAndDeletedRiderFromEventHandler.class.getSimpleName());

    @Override
    public void on(UserAuthFailEvent event) {
        try {
            UserAuth userAuth = event.mappingEvent(UserAuth.class, event.after);
            logger.info("handling event UpdatedAndDeletedRiderFromEventHandler data: {}", userAuth);
            if (event.isUpdate()) {
                RiderService.updatedRiderOnEvent(userAuth);
            } else if (event.isCreate()) {
                RiderService.deletedRiderOnEvent(userAuth);

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
