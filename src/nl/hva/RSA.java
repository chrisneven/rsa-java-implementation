package nl.hva;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger r;

    public List<BigInteger> calculatePandQ(BigInteger modulus) {
        int n = modulus.intValue();
        List<BigInteger> factors = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(BigInteger.valueOf(i));
                n /= i;
            }
        }
        return factors;
    }

}
