package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.MerchantChangeInfoEvent;
import vn.shippo.rider.event.evententity.Merchant;
import vn.shippo.rider.service.TransportationTaskService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

public class TaskUpdateFromMerchantHandler implements IEventHandler<MerchantChangeInfoEvent> {
    private static Logger logger = LoggerFactory.getLogger(TaskUpdateFromMerchantHandler.class.getSimpleName());

    @Override
    public void on(MerchantChangeInfoEvent event) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        Transaction transaction = server.beginTransaction();
        try {
            if (event.isUpdate()) {
                Merchant before = event.mappingEvent(Merchant.class, event.getBefore());
                Merchant after = event.mappingEvent(Merchant.class, event.getAfter());
                logger.info("Handling event TaskUpdateFromMerchantHandler before:{}, after: {}", before, after);
                if (before != null && after != null && _isMerchantChangeInfo(before, after)) {
                    TransportationTaskService.updateTaskFromMerchantHandler(after, transaction);
                }
            }
            transaction.commit();
            logger.info("Handler TaskUpdateFromMerchantHandler successfully!");
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        }
    }

    private boolean _isMerchantChangeInfo(Merchant before, Merchant after) {
        return _isMerchantHasChangeFullName(before, after) || _isMerchantHasChangeMobile(before, after);
    }

    private boolean _isMerchantHasChangeFullName(Merchant before, Merchant after) {
        return (before.getFullName() == null && after.getFullName() != null)
                || (before.getFullName() != null && !before.getFullName().equals(after.getFullName()));
    }

    private boolean _isMerchantHasChangeMobile(Merchant before, Merchant after) {
        return (before.getMobile() == null && after.getMobile() != null)
                || (before.getMobile() != null && !before.getMobile().equals(after.getMobile()));
    }
}
