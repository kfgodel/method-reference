package ar.com.kfgodel.methodreference.api;

import ar.com.kfgodel.methodreference.impl.ClassReferencer;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * Created by kfgodel on 06/01/15.
 */
public interface MethodReference {

    /**
     * This method allows a compile time access to a method reference by using a call to that method as an indicator.<br>
     *     The class is used as a type token so the methodCall argument is bounded to that type
     * @param aClass The class that indicates the type that contains the method
     * @param methodCall The method call (or Java 8 reference) that calls the indicated method
     * @param <T> The Type that contains the method
     * @return The called method
     */
    public static <T> Method inClass(Class<T> aClass, Consumer<T> methodCall) {
        return inClass(aClass).get(methodCall);
    }

    /**
     * This method creates a class referencer that can be used to obtain a method reference
     * @param aClass The class to which the referencer will be bound as a Type containing the referentiable methods
     * @return The class method referencer
     */
    static <T> MethodReferencer<T> inClass(Class<T> aClass) {
        return ClassReferencer.create(aClass);
    }
}
