package ar.com.kfgodel.methodreference;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.methodreference.api.MethodReference;
import ar.com.kfgodel.methodreference.api.MethodReferenceException;
import ar.com.kfgodel.methodreference.api.MethodReferencer;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies that the method references are
 * Created by kfgodel on 06/01/15.
 */
@RunWith(JavaSpecRunner.class)
public class MethodReferenceTest extends JavaSpec<TestContext> {

    @Override
    public void define() {

        describe("a method reference", () -> {

            it("is accessible through a class and a method call",()->{
                Method method = MethodReference.inClass(Number.class, Number::intValue);

                assertThat(method.getName()).isEqualTo("intValue");
            });

            it("is accessible from a referencer and a method call",()->{
                MethodReferencer<Number> referencer = MethodReference.inClass(Number.class);

                Method method = referencer.get(Number::intValue);

                assertThat(method.getName()).isEqualTo("intValue");
            });

            it("is accessible from a referencer and a proxy recorder",()->{

                MethodReferencer<Number> referencer = MethodReference.inClass(Number.class);

                Number recorder = referencer.getRecorder();

                recorder.intValue();

                Method method = referencer.getLastRecordedMethod();

                assertThat(method.getName()).isEqualTo("intValue");
            });

            it("throws an error if no method called",()->{
                try{
                    MethodReference.inClass(Number.class, (number)-> {});
                    failBecauseExceptionWasNotThrown(MethodReferenceException.class);
                }catch(MethodReferenceException e){
                    assertThat(e).hasMessage("No method was called to get a reference from");
                }
            });

        });

    }
}