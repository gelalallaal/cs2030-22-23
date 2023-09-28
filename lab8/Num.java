import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class Num extends AbstractNum<Integer> {

    private Num(int i) {
        super(Optional.of(i));
    }

    private Num(Optional<Integer> opt) {
        super(opt);
    }

    private Num(AbstractNum<Integer> n) {
        super(n.opt);
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    public static Num of(int i) {
        return AbstractNum.valid.test(i)? new Num(i) :
            new Num(Optional.empty());
    }

    private Optional<Integer> i() {
        return super.opt;
    }

    public Num succ() {
        return new Num(this.i().map(AbstractNum.s));
    }

    static Num one() {
        return Num.zero().succ();
    }

    public Num add(Num other) {
        Num sum = this;
        if (this.isValid() && other.isValid()) {
            Num count = Num.zero();
            while (!count.equals(other)) {
                sum = sum.succ();
                count = count.succ();
            }
        } else {
            sum = new Num(Optional.empty());
        }
        return sum;
    }

    public Num mul(Num other) {
        Num result = Num.zero();
        if (this.isValid() && other.isValid()) {
            if (other.equals(result)) {
                return result;
            } else {
                Num count = Num.zero();
                while (!count.equals(other)) {
                    result = result.add(this);
                    count = count.succ();
                }
            }
            return result;
        } else {
            return new Num(Optional.empty());
        }
    }

    public Num sub(Num other) {
        Num result = Num.zero();
        if (this.isValid() && other.isValid()) {
            if (other.equals(result)) {
                return this;
            } else {
                Optional<Integer> optStart = other.i().map(n);
                Optional<Integer> optThis = this.i();
                Optional<Integer> optZero = Num.zero().i();
                while (! optThis.equals(optZero)) {
                    optStart = optStart.map(AbstractNum.s);
                    optZero = optZero.map(AbstractNum.s);
                }
                result = new Num(optStart.filter(AbstractNum.valid));
                return result;
            }
        }
        return new Num(Optional.empty());
    }

    // public Num sub(Num n) {
    //     return n.i().map(AbstractNum.n).map(x -> x.add(this));
    // }
}

