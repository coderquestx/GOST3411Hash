public class GOST3411 {

    private int[] h = new int[8];
    private byte[] N = new byte[32];
    private byte[] Sigma = new byte[32];

    public byte[] hash(byte[] input) {
        process(input);
        byte[] digest = new byte[32];
        for (int i = 0; i < 8; i++) {
            intToBytes(h[i], digest, i * 4);
        }
        return digest;
    }

    private void process(byte[] input) {
        // Init h
        for (int i = 0; i < 8; i++) {
            h[i] = 0x01010101;
        }

        int[] m = new int[16];
        int len = input.length;
        int n = len / 32;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                N[j] = input[i * 32 + j];
            }
            for (int j = 0; j < 8; j++) {
                h[j] = h[j] ^ bytesToInt(N, j * 4);
            }
            compress(h, N);
        }
    }

    private void compress(int[] h, byte[] m) {
        for (int j = 0; j < 32; j++) {
            Sigma[j] = m[j];
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                h[j] ^= bytesToInt(Sigma, (i * 8 + j) * 4);
            }
            round(h);
        }
    }

    private void round(int[] h) {
        for (int i = 0; i < 8; i++) {
            h[i] = h[i] ^ h[(i + 2) % 8];
        }
    }

    private int bytesToInt(byte[] b, int offset) {
        return ((b[offset] & 0xFF)) | ((b[offset + 1] & 0xFF) << 8) | ((b[offset + 2] & 0xFF) << 16)
                | ((b[offset + 3] & 0xFF) << 24);
    }

    private void intToBytes(int i, byte[] b, int offset) {
        b[offset] = (byte) (i & 0xFF);
        b[offset + 1] = (byte) ((i >>> 8) & 0xFF);
        b[offset + 2] = (byte) ((i >>> 16) & 0xFF);
        b[offset + 3] = (byte) ((i >>> 24) & 0xFF);
    }
}
