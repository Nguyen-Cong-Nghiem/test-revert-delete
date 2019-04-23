package vn.shippo.rider.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.velacorp.Registry;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Firebase {

    private static String DATABASE_URL = "";
    private FirebaseDatabase firebaseDatabase;
    Logger logger = LoggerFactory.getLogger(Firebase.class.getSimpleName());

    static {
        Properties properties = Registry.getProperties();
        DATABASE_URL = properties.getProperty("firebase.url");
    }

    public Firebase() throws Exception {
        FileInputStream serviceAccount = null;

        try {
            serviceAccount = new FileInputStream("conf/key.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();
            FirebaseApp.initializeApp(options);
            FirebaseApp app = FirebaseApp.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance(app);

        } catch (IOException ioe) {
            logger.error("Error when init firebase connect, error{}", ioe.getMessage());
        } finally {
            if (serviceAccount != null) {
                serviceAccount.close();
            }
        }
    }


    public void update(Object value, String path) {
        DatabaseReference ref = firebaseDatabase.getReference(path);
        ref.updateChildren((Map<String, Object>) value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    logger.error("Data could not be updated " + databaseError.getMessage());
                } else {
                    logger.info("Data updated successfully.");
                }
            }
        });
    }

    public void save(Object object, String path) {
        DatabaseReference ref = firebaseDatabase.getReference(path);
        ref.setValue(object, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    logger.error("Data could not be saved " + databaseError.getMessage());
                } else {
                    logger.info("Data saved successfully.");
                }
            }
        });
    }

    public void remove(String path) {
        DatabaseReference ref = firebaseDatabase.getReference(path);
        ref.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    logger.error("Data could not be remove " + databaseError.getMessage());
                } else {
                    logger.info("Data removed successfully.");
                }
            }
        });
    }

    public void close() {
        firebaseDatabase.getApp().delete();
    }
}