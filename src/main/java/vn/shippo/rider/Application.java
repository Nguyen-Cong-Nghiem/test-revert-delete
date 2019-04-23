package vn.shippo.rider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.*;
import vn.shippo.rider.handler.*;
import vn.shippo.rider.event.Dispatcher;
import vn.shippo.rider.event.ParcelUpdateEvent;
import vn.shippo.rider.event.PickupRequestUpdateEvent;
import vn.shippo.rider.event.TransportationTaskChangeStateEvent;
import vn.velacorp.Registry;
import vn.velacorp.kafka.SingleConsumer;

import java.io.IOException;
import java.util.Properties;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] args) {

        try {
            _init();

            SingleConsumer kafkaConsumer = Factory.getKafkaConsumer();
            kafkaConsumer.execute();
//            Thread.sleep(1000);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Got Cntrl-C, shutting down by kill switch");
                kafkaConsumer.shutdown();
            }));

        } catch (Exception e) {
            logger.error("Something went wrong {}", e);
        }
    }

    public static void _init() throws IOException {
        //load config first
        Properties properties = Registry.loadConfig();

        //setup Event Dispatcher
        int threadsNo = Integer.parseInt(String.valueOf(properties.getProperty("threads_no", "1")));
        Dispatcher dispatcher = new Dispatcher(threadsNo);
        Registry.registerEventDispatcher(dispatcher);

        //register Events
        dispatcher.register(TransportationTaskChangeStateEvent.class, new UpdatedRiderShiftFromTaskHandler());
        dispatcher.register(ParcelUpdateEvent.class, new TransportTaskUpdateFromParcelHandler());
        dispatcher.register(ParcelUpdateEvent.class, new PickupTaskParcelUpdateFromParcelEventHandler());
        dispatcher.register(UpdateTaskPickUpWhenRequestInStateCancelledEvent.class,new UpdateTaskPickUpWhenRequestCancelledHandler());
        dispatcher.register(PickupRequestUpdateEvent.class,new TransportTaskUpdateFromPickupRequestEventHandler());

//        dispatcher.register(TransportationTaskChangeStateEvent.class, new PickupRequestChangeStateHandler());
        dispatcher.register(UserAuthFromDBZEvent.class, new UpdatedRiderFromDBZHandler());
        dispatcher.register(UserAuthFailEvent.class, new UpdatedAndDeletedRiderFromEventHandler());
        dispatcher.register(CreatePickupTaskParcelFromPickupRequestOrderEvent.class,new CreatePickupTaskParcelHandler());
        dispatcher.register(ParcelUpdateEvent.class, new TransportationTaskSyncLastImportAtFromParcel());

        //Sync Transportation task to firebase
        dispatcher.register(TransportationTaskChangeEvent.class,new SyncTaskToFirebaseHandler());
        dispatcher.register(MerchantChangeInfoEvent.class,new TaskUpdateFromMerchantHandler());

        //shutdown hook for CtrlC or service restarts(i.e. graceful shutdown)
        Runtime.getRuntime().addShutdownHook(new Thread(dispatcher::shutdown));
    }
}
