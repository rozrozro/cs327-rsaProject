import java.math.BigInteger;


public class RozRogelioProj2 {
// c = m^e mod N (public key)
    public static void main(String[] args) {
        //how many bits does N have
        BigInteger N = new BigInteger ("1010517893091632587989664773819175360110584414091408545389643", 10); //10 means base 10
        //^^ new object N that is a BigInteger
        BigInteger e = new BigInteger("65537", 10);
        //^new object e that is a BigInteger
        System.out.println("Bit length of N =" + N.bitLength());

        //got p & q from sagecell 
        BigInteger p = new BigInteger("863490933159798076360907020927", 10);
        System.out.println("p (hex) = " + p.toString(16));
        BigInteger q = new BigInteger("1170270415456262384144425721909", 10);
        System.out.println("q (hex) = " + q.toString(16));
        System.out.println("Bit length of p =" + p.bitLength());
        System.out.println("Bit length of q = " + q.bitLength());
    
    //calculate phi N = (p-1)(q-1)
    BigInteger phi = p.subtract(BigInteger.ONE) //p.subtract(1) error, 1 is an int
        .multiply(q.subtract(BigInteger.ONE)); //basically the p-1)(q-1) formula lol
    System.out.println("phi (hex) = " + phi.toString(16));

    //calculate4 d ------ modular inverse of e mod phi
    BigInteger d = e.modInverse(phi); //d big int = inverse phi, find # that multiplies with e to give remainder of 1 mod phi
    System.out.println("d (hex) = " + d.toString(16));

    //store alice's message 3 as bigint
    BigInteger m = BigInteger.valueOf(3);
    //c - m^e mod n encryption
    BigInteger c = m.modPow(e, N);

    // Print encrypted message.
    System.out.println("Ciphertext (hex) = " + c.toString(16));

    // Decrypt, then print the decrypted message.
    BigInteger decrypted = c.modPow(d, N);
    System.out.println("Decrypted message (hex) = " + decrypted.toString(16));

    realWorldExample();

}   //end main

    public static void realWorldExample() {
        System.out.println("\nReal world example");

        BigInteger p = new BigInteger(
            "c86422b12e3a0c79cbe9f10c47a436fc54a71309214a3455054fca5912e442c32247ffdc782e574c96d7d43e60e383d7f0f091f08909f2629c5ad1bd5ce2427333dedb281abb1bffcbfb2814b402dc29bf5406d6e749ba57e1e6843f0111875a34439aeb2aa6c554d71b8cbaefbe0f6a4c7a0145d75afda323c7520a29d4d3d799a676977a0d3bd0f3411023c3ab106ee5fb0d2fbaa64e85fb71b6b76ebebbbfcd1ffd1de93302e90a3fefe0ffcb95a3da7ce6e2dcdd973873b96dd939763025", 16);
        BigInteger q = new BigInteger(
            "d062fa3d79191304a4a64d39dc3b5ca757b9b740304825b13d05d17ffcf3b1e033dbffbe31eeea0b908f8ed36ebc2d1b1db3a094e2ddd4976549e225981ac6f9586a31f79b90cc41ed4c069d1306482345c8cc61fc4567bd2043484f8ac2fca32d7690c0881889b44a365795b0af07046a6bc468b10570033e2b6ad8d7702266c6d6ca5eec75224039998f719f74ad8fdebe9c4347a71e6cd96e03796b570f459c23c6ca250761e741f957d4b9f24df783e6c2ebe91e64863b710989da81a261", 16);
        BigInteger e = new BigInteger("65537", 10);
        BigInteger m = new BigInteger("3", 10);

        BigInteger N = p.multiply(q);
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger qMinusOne = q.subtract(BigInteger.ONE);
        BigInteger phi = pMinusOne.multiply(qMinusOne);

        //find d 
        BigInteger d = e.modInverse(phi);

        //encrypt 3 using poublic key
        BigInteger c = m.modPow(e, N);
        //decryptr with private key
        BigInteger decrypted = c.modPow(d, N);

        System.out.println("p (hex) = " + p.toString(16));
        System.out.println("q (hex) = " + q.toString(16));
        System.out.println("N bits = " + N.bitLength());
        System.out.println("p bits = " + p.bitLength());
        System.out.println("q bits = " + q.bitLength());
        System.out.println("Public key (N, e), both hex:");
        System.out.println("N = " + N.toString(16));
        System.out.println("e = " + e.toString(16));
        System.out.println("p - 1 (hex) = " + pMinusOne.toString(16));
        System.out.println("q - 1 (hex) = " + qMinusOne.toString(16));
        System.out.println("phi = (p - 1)(q - 1) (hex) = " + phi.toString(16));
        System.out.println("Private key is (N, d), using N above.");
        System.out.println("d = e inverse mod phi (hex) = " + d.toString(16));
        System.out.println("e * d mod phi (hex) = " + e.multiply(d).mod(phi).toString(16));
        System.out.println("Original message (hex) = " + m.toString(16));
        System.out.println("Ciphertext = m^e mod N (hex) = " + c.toString(16));
        System.out.println("Decrypted = c^d mod N (hex) = " + decrypted.toString(16));
//crt
        BigInteger dp = d.mod(pMinusOne);
        BigInteger dq = d.mod(qMinusOne);
        BigInteger qInverse = q.modInverse(p);
//find m's reaminder modulo each prime
        BigInteger m1 = c.modPow(dp, p);
        BigInteger m2 = c.modPow(dq, q);
//combine two remainders for whole m
        BigInteger h = qInverse.multiply(m1.subtract(m2)).mod(p);
        BigInteger crtDecrypted = m2.add(h.multiply(q));

        System.out.println("\nCRT calculations (all hex):");
        System.out.println("dp = d mod (p - 1) = " + dp.toString(16));
        System.out.println("dq = d mod (q - 1) = " + dq.toString(16));
        System.out.println("q inverse mod p = " + qInverse.toString(16));
        System.out.println("m1 = c^dp mod p = " + m1.toString(16));
        System.out.println("m2 = c^dq mod q = " + m2.toString(16));
        System.out.println("h = qInverse * (m1 - m2) mod p = " + h.toString(16));
        System.out.println("CRT decrypted = m2 + h * q = " + crtDecrypted.toString(16));

        for (int i = 0; i< 100; i++) {
            decrypted = c.modPow(d, N);
            m1 = c.modPow(dp, p);
            m2 = c.modPow(dq, q);
            h = qInverse.multiply(m1.subtract(m2)).mod(p);
            crtDecrypted = m2.add(h.multiply(q));
        }

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            decrypted = c.modPow(d, N);
        }
        long end = System.nanoTime();

//turn nanoseconds to seconds then calculate throughput
        double regularSeconds = (end - start) / 1_000_000_000.0;
        double regularSecondsPerDecryption = regularSeconds / 1000.0;
        double regularKbps = N.bitLength() / regularSecondsPerDecryption / 1000.0;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            m1 = c.modPow(dp, p);
            m2 = c.modPow(dq, q);
            h = qInverse.multiply(m1.subtract(m2)).mod(p);
            crtDecrypted = m2.add(h.multiply(q));
        }
        end = System.nanoTime();

        double crtSeconds = (end - start) / 1_000_000_000.0;
        double crtSecondsPerDecryption = crtSeconds / 1000.0;
        double crtKbps = N.bitLength() / crtSecondsPerDecryption / 1000.0;

        //speed and time are double values so decimal output is allowed
        System.out.println("\nTiming results for 1000 decryptions:");
        System.out.println("Regular RSA total seconds = " + regularSeconds);
        System.out.println("Regular RSA seconds per decryption = " + regularSecondsPerDecryption);
        System.out.println("Regular RSA speed in kbps = " + regularKbps);
        System.out.println("CRT total seconds = " + crtSeconds);
        System.out.println("CRT seconds per decryption = " + crtSecondsPerDecryption);
        System.out.println("CRT speed in kbps = " + crtKbps);
        System.out.println("CRT speedup = " + (crtKbps / regularKbps));

        //print final results from loops to confirm they still recover 3
        System.out.println("Regular final message (hex) = " + decrypted.toString(16));
        System.out.println("CRT final message (hex) = " + crtDecrypted.toString(16));

        //gbps connection requires 1000000 kbps
        System.out.println("Regular RSA fast enough for 1 Gbps: " + (regularKbps >= 1_000_000.0));
        System.out.println("CRT fast enough for 1 Gbps: " + (crtKbps >= 1_000_000.0));
        System.out.println("A lower speed would bottleneck the connection in the assignment.");
        System.out.println("CRT is expected to be about four times faster with balanced primes,");
        System.out.println("but the exact ratio depends on big-number algorithms, Java's runtime,");
        System.out.println("CPU load, and the extra work of combining the CRT results.");
    }

} //end class