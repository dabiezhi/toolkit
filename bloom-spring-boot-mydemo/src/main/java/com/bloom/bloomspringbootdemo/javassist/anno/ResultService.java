package
        com.bloom.bloomspringbootdemo.javassist.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author taosy
 * Created by on 2022-01-13 下午5:32
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface ResultService {

    Class<?> serviceClass();

    Class<?> facadeClass();
}
