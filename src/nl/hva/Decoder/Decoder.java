package nl.hva.Decoder;

import nl.hva.RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private BigInteger valueP;
    private BigInteger valueQ;
    private String decodedMessage;
    private long elapsedTime;
    private BigInteger valueR;
    private BigInteger valueD;
    private List<Integer> toBeDecodedValues;
    private BigInteger valueN;
    private BigInteger valueE;
    private RSA rsa = new RSA();

    public Decoder(BigInteger valueN, BigInteger valueE, String toBeDecodedValues) {
        this.toBeDecodedValues = convertToIntegerList(toBeDecodedValues);
        this.valueN = valueN;
        this.valueE = valueE;
    }

    public void decode() {
        long startTime = System.currentTimeMillis();
        calculatePandQ();
        calculateR();
        calculateD();
        buildDecodedMessage();
        long stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        printTheValues();

    }

    private void printTheValues() {
        // Print out the values
        System.out.print("" +
                "\nn is: " + valueN +
                "\ne is: " + valueE +
                "\np is: " + valueP +
                "\nq is: " + valueQ +
                "\nd is: " + valueD +
                "\nMessage after decoding is: " + decodedMessage +
                "\nAmount of time busy decoding was: " + elapsedTime);
    }

    private void buildDecodedMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer value : toBeDecodedValues) {
            BigInteger c = BigInteger.valueOf(value);
            int d = valueD.intValue();
            int asciiCode = c.pow(d).mod(valueN).intValue();
            String ch = convertASCIItoString(asciiCode);
            stringBuilder.append(ch);
        }
        decodedMessage = stringBuilder.toString();
    }

    private String convertASCIItoString(int ascii) {
        return Character.toString((char) ascii);
    }

    private void calculateD() {
        valueD = valueE.modInverse(valueR);
    }


    private void calculateR() {
        valueR = (valueP.subtract(BigInteger.ONE)).multiply(valueQ.subtract(BigInteger.ONE));
    }

    private void calculatePandQ() {
        List<BigInteger> pqList = rsa.calculatePandQ(valueN);
        valueP = pqList.get(0);
        valueQ = pqList.get(1);
    }

    private static List<Integer> convertToIntegerList(String toBeDecoded) {
        List<Integer> values = new ArrayList<>();
        for (String value : toBeDecoded.split(","))
            values.add(Integer.valueOf(value));
        return values;
    }


}
