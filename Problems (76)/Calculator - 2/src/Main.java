/* Please, do not rename it */
class Problem {

    public static void main(String args[]) {
        String operator = args[0];

        int[] arr = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            arr[i-1] = Integer.parseInt(args[i]);
        }

        int result;

        switch (operator) {
            case ("MAX"):
                result = arr[0];
                for (Integer el : arr) {
                    if (el > result) result = el;
                }
                System.out.println(result);
                break;
            case ("MIN"):
                result = arr[0];
                for (Integer el : arr) {
                    if (el < result) result = el;
                }
                System.out.println(result);
                break;
            case ("SUM"):
                result = 0;
                for (Integer el : arr) {
                    result += el;
                }
                System.out.println(result);
                break;
        }

    }
}