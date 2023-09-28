import java.lang.IllegalArgumentException;
import java.util.function.Function;
import java.util.Optional;


class Log<T> {
    private final T value;
    private final String string;

    private Log(T t, String s) {
        this.value = t;
        this.string = s;
    }

    public static <T> Log<T> of(T t) {
        return of(t, "");
    }

    public static <T> Log<T> of(T t, String s) {
        return Optional.ofNullable(t)
            .filter(v -> !(v instanceof Log))
            .flatMap(v -> Optional.ofNullable(s).map(x -> new Log<>(v, x)))
            .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    public <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return new Log<>(mapper.apply(this.value), string);
    }

    // public <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
    //     return Optional.ofNullable(mapper.apply(this.value))
    //                .flatMap(log -> Optional.of(new Log<>(log.value(), string + log.string())))
    //                .orElseThrow(IllegalArgumentException::new);
    // }

    // public <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
    //     return mapper.apply(value()).map(r -> new Log<>(r, string + "\n" + r.string()));
    // }

    public <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
        Log<? extends R> result = mapper.apply(value());
        String newString = this.string().equals("") ?
                            result.string() : this.string() + "\n" + result.string();
        return new Log<>(result.value(), newString);
    }

    public T value() {
        return this.value;
    }

    public String string() {
        return this.string;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Log)) {
            return false;
        }
        Log<?> other = (Log<?>) obj;
        return this.value.equals(other.value()) && this.string.equals(other.string());
    }

    @Override
    public String toString() {
        return "Log[" + value + "]\n" + string;
    }
}
