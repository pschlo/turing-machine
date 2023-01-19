import java.util.*;

public class DeltaIn {
    int q;
    char a;

    public DeltaIn(int q, char a) {
        this.q = q;
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeltaIn deltaIn = (DeltaIn) o;
        return q == deltaIn.q && a == deltaIn.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, a);
    }
}
