package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.Rider;
import vn.shippo.rider.event.exception.RiderNotFoundException;
import vn.shippo.rider.event.evententity.UserAuth;
import vn.velacorp.Registry;


public class RiderService {
    private static Logger _logger = LoggerFactory.getLogger(RiderService.class.getSimpleName());
    private static EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

    //region Update userId on rider from field id in usersAuth DBZ
    public static void UpdatedRiderOnDBZ(UserAuth userAuth) {
        Rider rider = _getRiderByUsernameAndEmail(userAuth.getUsername(), userAuth.getEmail());
        if (rider == null) {
            throw new RiderNotFoundException("Rider not found !");
        }
        Transaction transaction = server.beginTransaction();

        rider.setUserId(userAuth.getId());

        try {

            server.update(rider, transaction);
            transaction.commit();
            _logger.info("Updated Rider {} successfully !", rider.toString());
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
    //endregion

    //region Get rider by username and email
    private static Rider _getRiderByUsernameAndEmail(String username, String email) {

        Rider rider = server.find(Rider.class)
                .where()
                .and()
                .eq("username", username)
                .eq("email", email)
                .findUnique();
        return rider;
    }
    //endregion

    //region Updated Rider on event
    public static void updatedRiderOnEvent(UserAuth userAuth) {

        Rider rider = _getRiderById(userAuth.getId());
        if (rider == null) {
            throw new RiderNotFoundException("Rider not found !");
        }
        Transaction transaction = server.beginTransaction();

        rider.setCreatedAt(userAuth.getCreatedAt());
        rider.setUpdatedAt(userAuth.getUpdatedAt());
        rider.setEmail(userAuth.getEmail());
        rider.setUsername(userAuth.getUsername());
        rider.setState(userAuth.getState());
        rider.setVersion(userAuth.getVersion() % 2 == 0 ? userAuth.getVersion() + 2 : userAuth.getVersion() + 1);
        try {
            server.update(rider, transaction);
            transaction.commit();
            _logger.info("Updated rider {} successfully !", rider.toString());
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
    //endregion

    //region Delete Rider from event
    public static void deletedRiderOnEvent(UserAuth userAuth) {

        Rider rider = _getRiderByUsernameAndEmail(userAuth.getUsername(), userAuth.getEmail());
        Transaction transaction = server.beginTransaction();

        if (rider == null) {
            throw new RiderNotFoundException("Rider not found !");
        }
        try {
            server.delete(rider, transaction);
            transaction.commit();
            _logger.info("Delete rider {} done !", rider);

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }
    //endregion

    //region Get rider by id from userAuth
    private static Rider _getRiderById(Integer id) {
        return server.find(Rider.class)
                .where()
                .and()
                .eq("userId", id)
                .findUnique();
    }
    //endregion

    static Rider getRiderById(Integer id) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(Rider.class)
                .where()
                .and()
                .eq("id", id)
                .endAnd()
                .findUnique();
    }
}
