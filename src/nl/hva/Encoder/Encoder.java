package nl.hva.Encoder;


import nl.hva.RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Encoder {
    private BigInteger valueN;
    private BigInteger valueE;
    private List<Integer> message;
    private BigInteger valueP;
    private BigInteger valueQ;
    private BigInteger valueR;
    private BigInteger valueD;
    private String encodedMessage;
    private long elapsedTime;
    private RSA rsa = new RSA();


    public Encoder(BigInteger valueN, BigInteger valueE, String message) {
        this.valueN = valueN;
        this.valueE = valueE;
        this.message = convertToASCII(message);


    }

    private List<Integer> convertToASCII(String message) {
        List<Integer> asciiMessage = new ArrayList<>();
        for (char ch : message.toCharArray()) {
            asciiMessage.add((int) ch);
        }
        return asciiMessage;
    }

    public void encode() {
        long startTime = System.currentTimeMillis();
        calculatePandQ();
        calculateR();
        calculateD();
        buildEncodedMessage();
        long stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        printTheValues();

    }

    private void buildEncodedMessage() {
        StringJoiner joiner = new StringJoiner(",");
        for (int ch : message) {
            BigInteger chh = BigInteger.valueOf(ch);
            String cypher = chh.pow(valueE.intValue()).mod(valueN).toString();
            joiner.add(cypher);
        }
        encodedMessage = joiner.toString();
    }

    private void calculateR() {
        valueR = (valueP.subtract(BigInteger.ONE)).multiply(valueQ.subtract(BigInteger.ONE));
    }

    private void printTheValues() {
        System.out.print("" +
                "\nn is: " + valueN +
                "\ne is: " + valueE +
                "\np is: " + valueP +
                "\nq is: " + valueQ +
                "\nd is: " + valueD +
                "\nMessage after encryption is: " + encodedMessage +
                "\nAmount of time busy encoding was: " + elapsedTime + " ms");

    }

    private void calculateD() {
        valueD = valueE.modInverse(valueR);
    }

    private void calculatePandQ() {
        List<BigInteger> pqList = rsa.calculatePandQ(valueN);
        valueP = pqList.get(0);
        valueQ = pqList.get(1);
    }
}


