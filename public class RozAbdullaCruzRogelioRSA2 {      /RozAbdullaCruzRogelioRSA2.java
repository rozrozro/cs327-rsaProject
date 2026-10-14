import  java.math.BigInteger;

public class RozAbdullaCruzRogelioRSA2 {

    public static void main(String[] args) {

        // Flawed Example N&e
        String feN = "1010517893091632587989664773819175360110584414091408545389643";
        String feE = "65537";

        //TASK 2.2
        // Q1: How many bits does N have?
        BigInteger N = new BigInteger(feN, 10);
        int bits = N.bitLength(); // Returns bit length
        System.out.println("Bit-length of N = " + bits);
        

        // Q2: What are the p and q values that Bob used in the Key generation process?
        // How many bits does p and q have?
        String feP = "863490933159798076360907020927";
        String feQ = "1170270415456262384144425721909";
        BigInteger p = new BigInteger(feP, 10);
        BigInteger q = new BigInteger(feQ, 10);
        int pBits = p.bitLength();
        int qBits = q.bitLength();
        System.out.println("Bit-length of p = " + pBits);
        System.out.println("Bit-length of q = " + qBits);


        // Q3

        // Q4

        // Q5

        // Real World Example p&q
        String rwP = "c86422b12e3a0c79cbe9f10c47a436fc54a71309214a3455054fca5912e442c32247ffdc782e574c96d7d43e60e383d7f0f091f08909f2629c5ad1bd5ce2427333dedb281abb1bffcbfb2814b402dc29bf5406d6e749ba57e1e6843f0111875a34439aeb2aa6c554d71b8cbaefbe0f6a4c7a0145d75afda323c7520a29d4d3d799a676977a0d3bd0f3411023c3ab106ee5fb0d2fbaa64e85fb71b6b76ebebbbfcd1ffd1de93302e90a3fefe0ffcb95a3da7ce6e2dcdd973873b96dd939763025";
        String rwQ = "d062fa3d79191304a4a64d39dc3b5ca757b9b740304825b13d05d17ffcf3b1e033dbffbe31eeea0b908f8ed36ebc2d1b1db3a094e2ddd4976549e225981ac6f9586a31f79b90cc41ed4c069d1306482345c8cc61fc4567bd2043484f8ac2fca32d7690c0881889b44a365795b0af07046a6bc468b10570033e2b6ad8d7702266c6d6ca5eec75224039998f719f74ad8fdebe9c4347a71e6cd96e03796b570f459c23c6ca250761e741f957d4b9f24df783e6c2ebe91e64863b710989da81a261";
        // e = 2^16 + 1
        
        // TASK 2.3

    }
    
}
