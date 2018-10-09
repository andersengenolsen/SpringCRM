package springcrm.converter;

import org.springframework.stereotype.Component;

/**
 * Interface for converting an object.
 *
 * @param <F> Convert from / to
 * @param <T> Convert from / to
 */
@Component
public interface Converter<F, T> {
    /**
     * @param f Convert from f
     * @return Object converted from F to T
     */
    T convert(F f);

}
