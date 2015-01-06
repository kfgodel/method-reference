package ar.com.kfgodel.methodreference.api;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 *  This type represents the method referencer that is bound to a given type, so it can get method references from that type
 * Created by kfgodel on 06/01/15.
 */
public interface MethodReferencer<T> {

    /**
     * Gets a method reference from the type bounded to this instance
     * @param methodCall The call to a single method that identifies which method is asked
     * @return
     */
    Method get(Consumer<T> methodCall);

    /**
     * Gets a proxy recorder that will hold a reference to each method called
     * @return The proxy that serves to catch the method calls
     */
    T getRecorder();

    /**
     * The last recorded method or an exception if none is present
     * @return The called method
     */
    Method getLastRecordedMethod();
}
