package vn.shippo.rider.service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.entity.TransportationTaskComment;
import vn.shippo.rider.i18n.Internationalization;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class TransportationTaskCommentService {
    private static Internationalization internationalization = Internationalization.getInstance();
    private static String messageForTransCancelledComment = internationalization.getBundle().getString("cancelledTransComment");
    private static Logger logger = LoggerFactory.getLogger(TransportationTaskCommentService.class.getSimpleName());

    //region set value cho transportationtask comment
    private static TransportationTaskComment _setValueForNewTransportationComment(TransportationTask task) {
        TransportationTaskComment comment = new TransportationTaskComment();
        comment.setContextType("ACTIVITY");
        comment.setScope("INTERNAL");
        comment.setTaskId(task.getId());
        comment.setCreatedBy(task.getCreatedBy());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setVersion(task.getVersion() % 2 == 0 ? task.getVersion() + 2 : task.getVersion() + 1);
        comment.setContext(_buildJsonContext());
        return comment;
    }
    //endregion

    //region tạo context cho transportationtaskcomment
    private static Object _buildJsonContext() {
        Map<String, Object> content = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("message", messageForTransCancelledComment);
        content.put("_type", "ACTIVITY");
        content.put("_attributes", attributes);
        return content;
    }
    //endregion

    //region insert transportationtaskComment
    public static void insertTransportationTaskComment(TransportationTask transportationTask, EbeanServer server,
                                                       Transaction transaction) {
        TransportationTaskComment transportationTaskComment = _setValueForNewTransportationComment(transportationTask);
        server.insert(transportationTaskComment, transaction);
        logger.info("Insert transportation task comment: {} successfully", transportationTaskComment);
    }
    //endregion
}
