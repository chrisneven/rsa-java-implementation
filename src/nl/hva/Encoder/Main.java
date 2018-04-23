package nl.hva.Encoder;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        validateArguments(args);
        BigInteger n = new BigInteger(args[0]);
        BigInteger e = new BigInteger(args[1]);
        String m = args[2];
        // Create a new encoder, give the inputs as a parameter and encode the message
        Encoder encoder = new Encoder(n, e, m);
        encoder.encode();
    }

    private static void validateArguments(String[] args) {
        if (args.length != 3) {
            // Validate amount of arguments
            System.out.println("The application needs values of n, e and m put in as arguments");
            System.exit(1);
        }
    }



}
