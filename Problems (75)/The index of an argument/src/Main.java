class Problem {

    public static void main(String[] args) {

        boolean testIs = false;
        for (int i = 0; i < args.length; i++) {
            if ("test".equals(args[i])) {
                System.out.println(i);
                testIs = true;
            }
        }
        if (!testIs) System.out.println(-1);
    }
}