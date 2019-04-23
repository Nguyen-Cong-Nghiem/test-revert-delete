package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.RiderShift;
import vn.shippo.rider.entity.RiderShiftComment;
import vn.shippo.rider.i18n.Internationalization;
import vn.velacorp.Registry;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class RiderShiftCommentService {
    private static final Logger logger = LoggerFactory.getLogger(RiderShiftComment.class.getSimpleName());

    public static void addNewRiderShiftComment(RiderShift riderShift, Transaction transaction) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

        RiderShiftComment riderShiftComment = new RiderShiftComment();
        riderShiftComment.setContextType("ACTIVITY");
        riderShiftComment.setScope("INTERNAL");
        riderShiftComment.setRiderShiftId(riderShift.getId());
        riderShiftComment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        riderShiftComment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        riderShiftComment.setVersion(0);
        riderShiftComment.setContext(_buildJsonContent());

        server.insert(riderShiftComment, transaction);
        logger.info("Insert RiderShiftComment {} successfully!", riderShiftComment);
    }

    private static Object _buildJsonContent() {
        Map<String, Object> content = new HashMap<String, Object>();
        Map<String, Object> _attributes = new HashMap<String, Object>();

        Internationalization internationalization = Internationalization.getInstance();

        _attributes.put("description", internationalization.getBundle().getString("riderShiftChangeToInProcess"));

        content.put("_type", "ACTIVITY");
        content.put("_attributes", _attributes);
        return content;
    }
}
