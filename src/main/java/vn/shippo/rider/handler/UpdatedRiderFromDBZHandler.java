package vn.shippo.rider.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.UserAuthFromDBZEvent;
import vn.shippo.rider.event.evententity.UserAuth;
import vn.shippo.rider.service.RiderService;
import vn.velacorp.eventdispatcher.IEventHandler;

public class UpdatedRiderFromDBZHandler implements IEventHandler<UserAuthFromDBZEvent> {
    private static final Logger logger = LoggerFactory.getLogger(UpdatedRiderFromDBZHandler.class.getSimpleName());
    private static final String RIDER = "RIDER";

    @Override
    public void on(UserAuthFromDBZEvent event) {
        try {
            UserAuth after = event.mappingEvent(UserAuth.class, event.getAfter());
            logger.info("handling event UpdatedRiderFromDBZHandler data: {}", after);
            if (after.getSection().equals(RIDER)) {
                if (event.isCreate()) {
                    RiderService.UpdatedRiderOnDBZ(after);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
