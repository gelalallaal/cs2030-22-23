import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class Fraction extends AbstractNum<Frac> {

    private Fraction(int n, int d) {
        super(Optional.of(Frac.of(Num.of(n), Num.of(d))));
    }

    private Fraction(Optional<Frac> opt) {
        super(opt);
    }

    private Fraction(AbstractNum<Frac> n) {
        super(n.opt);
    }

    private static Fraction of(Optional<Num> n, Optional<Num> d) {
        Optional<Function<Num, Frac>> curryFunc = n.map(x -> y -> Frac.of(x, y));
        Optional<Frac> fraction = d.flatMap(x -> curryFunc.map(y -> y.apply(x)));
        return new Fraction(fraction);
    }

    public static Fraction of(int n, int d) {
        if (AbstractNum.valid.test(n) && AbstractNum.valid.test(d)) {
            if (!Num.zero().equals(Num.of(d))) {
                return new Fraction(n, d);
            }
        }
        return new Fraction(Optional.empty());
    }

    private Optional<Frac> opt() {
        return super.opt;
    }

    private Optional<Num> numerator() {
        return this.opt().map(x -> x.first());
    }

    private Optional<Num> denominator() {
        return this.opt().map(x -> x.second());
    }

    public Fraction add(Fraction other) {
        // ( a * d + b * c ) / b * d
        Optional<Num> newN1 = this.numerator()
                    .flatMap(x -> other.denominator().map(y -> x.mul(y)));
        Optional<Num> newN2 = this.denominator()
                    .flatMap(x -> other.numerator().map(y -> x.mul(y)));
        Optional<Num> newN = newN1.flatMap(x -> newN2.map(y -> x.add(y)));
        Optional<Num> newD = this.denominator()
                    .flatMap(x -> other.denominator().map(y -> x.mul(y)));
        return Fraction.of(newN, newD);
    }

    public Fraction sub(Fraction other) {
        // ( a * d - b * c ) / b * d
        Optional<Num> newN1 = this.numerator()
                    .flatMap(x -> other.denominator().map(y -> x.mul(y)));
        Optional<Num> newN2 = this.denominator()
                    .flatMap(x -> other.numerator().map(y -> x.mul(y)));
        Optional<Num> newN = newN1.flatMap(x -> newN2.map(y -> x.sub(y)));
        Optional<Num> newD = this.denominator()
                    .flatMap(x -> other.denominator().map(y -> x.mul(y)));
        return Fraction.of(newN, newD);
    }

    public Fraction mul(Fraction other) {
        Optional<Num> newN = this.numerator()
                    .flatMap(x -> other.numerator().map(y -> x.mul(y)));
        Optional<Num> newD = this.denominator()
                    .flatMap(x -> other.denominator().map(y -> x.mul(y)));

        return Fraction.of(newN, newD);
    }
}
