package nl.hva.Decoder;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        validateArguments(args);
        BigInteger n = new BigInteger(args[0]);
        BigInteger e = new BigInteger(args[1]);
        String c = args[2];
        // Create a new decoder, give the inputs as a parameter and decode the encrypted message
        Decoder decoder = new Decoder(n, e, c);
        decoder.decode();
    }

    private static void validateArguments(String[] args) {
        if (args.length != 3) {
            // Validate amount of arguments
            System.out.println("The application needs values of n, e and c put in as arguments");
            System.exit(1);
        }
    }



}
