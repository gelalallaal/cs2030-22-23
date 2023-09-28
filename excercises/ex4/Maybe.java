import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Predicate;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    <R> Maybe<? extends R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.value));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            if (!(obj instanceof Maybe<?>)) {
                return false;
            } else {
                Maybe<?> other = (Maybe<?>) obj;
                if (this.isEmpty()) {
                    return other.isEmpty();
                } else {
                    return this.value.equals(other.value);
                }
            }
        }
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        if (this.isEmpty()) {
            return Maybe.empty();
        } else {
            if (predicate.test(this.value)) {
                return this;
            } else {
                return Maybe.empty();
            }
        }
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (!this.isEmpty()) {
            consumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable action) {
        if (this.isEmpty()) {
            action.run();
        } else {
            consumer.accept(this.value);
        }
    }

    public T orElse(T other) {
        if (this.isEmpty()) {
            return other;
        } else {
            return this.value;
        }
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        if (this.isEmpty()) {
            return supplier.get();
        } else {
            return this.value;
        }
    }

    public Maybe<? extends T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (this.isEmpty()) {
            Maybe<? extends T> temp = supplier.get();
            return Maybe.<T>of(temp.get());
        } else {
            return this;
        }
    }

    public <R> Maybe<? extends R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            Maybe<? extends R> temp = mapper.apply(this.value);
            return Maybe.<R>of(temp.get());
        }
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}
