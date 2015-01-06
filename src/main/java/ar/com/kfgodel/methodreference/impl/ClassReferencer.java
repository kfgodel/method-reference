package ar.com.kfgodel.methodreference.impl;

import ar.com.kfgodel.methodreference.api.MethodReferenceException;
import ar.com.kfgodel.methodreference.api.MethodReferencer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * This type represents the method referencer that is bound to a given type, so it can get method references from that type
 * Created by kfgodel on 06/01/15.
 */
public class ClassReferencer<T> implements MethodReferencer<T>{

    private Class<T> boundType;
    private T methodRecorder;
    private Method lastRecordedMethod;


    public static <T> ClassReferencer<T> create(Class<T> referencedClass) {
        ClassReferencer<T> referencer = new ClassReferencer<>();
        referencer.boundType = referencedClass;
        referencer.createRecorder();
        return referencer;
    }

    private void createRecorder() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(boundType);
        enhancer.setCallback(getInterceptor());
        this.methodRecorder = (T) enhancer.create();
    }

    @Override
    public Method get(Consumer<T> methodCall) {
        methodCall.accept(this.getRecorder());
        return getLastRecordedMethod();
    }

    @Override
    public T getRecorder() {
        return methodRecorder;
    }

    @Override
    public Method getLastRecordedMethod() {
        if(lastRecordedMethod == null){
            throw new MethodReferenceException("No method was called to get a reference from");
        }
        return lastRecordedMethod;
    }

    public MethodInterceptor getInterceptor() {
        return new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                setLastRecordedMethod(method);
                return null;
            }
        };
    }

    protected void setLastRecordedMethod(Method aMethod){
        this.lastRecordedMethod = aMethod;
    }
}
