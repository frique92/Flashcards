public class AsciiCharSequence implements java.lang.CharSequence {

    private byte[] arrayChar;

    AsciiCharSequence(byte[] array) {
        this.arrayChar = array;
    }

    @Override
    public int length() {
        return arrayChar.length;
    }

    @Override
    public char charAt(int i) {
        return (char) arrayChar[i];
    }

    @Override
    public CharSequence subSequence(int start, int end) {

        int length = end - start;
        byte[] bytes = new byte[length];
        for (int i = 0, j = start; i < length; i++, j++) {
            bytes[i] = arrayChar[j];
        }
        return new AsciiCharSequence(bytes);

    }

    @Override
    public String toString() {
        return new String(arrayChar);
    }
}