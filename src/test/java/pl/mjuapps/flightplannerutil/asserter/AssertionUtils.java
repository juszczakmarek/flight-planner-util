package pl.mjuapps.flightplannerutil.asserter;

import lombok.SneakyThrows;
import org.assertj.core.api.ThrowableAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class AssertionUtils {

    public static final String NPE_MESSAGE = "%s is marked non-null but is null";

    public static void assertUnsupportedOperationException(ThrowableAssert.ThrowingCallable callable) {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(callable);
    }

    public static void assertNullPointerException(ThrowableAssert.ThrowingCallable callable,
                                                  String nullableParameterName) {
        assertThatNullPointerException()
                .isThrownBy(callable)
                .withMessage(String.format(NPE_MESSAGE, nullableParameterName));
    }

    @SneakyThrows
    public static <T> void assertListIsSemiImmutable(List<T> list, Class<T> clazz, boolean assertReplacement) {
        assertUnsupportedOperationException(() -> list.add(clazz.getDeclaredConstructor().newInstance()));
        assertUnsupportedOperationException(() -> list.remove(0));
        if (assertReplacement) {
            assertUnsupportedOperationException(() -> list.set(0, clazz.getDeclaredConstructor().newInstance()));
        }
    }

}
