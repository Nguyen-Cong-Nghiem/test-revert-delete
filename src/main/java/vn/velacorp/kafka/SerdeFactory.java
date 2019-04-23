package vn.velacorp.kafka;

import org.apache.kafka.common.serialization.Serde;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SerdeFactory {
    public static <T> Serde<T> factory(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<Serde<T>> constructor = clazz.getDeclaredConstructor();
        return constructor.newInstance();
    }
}
