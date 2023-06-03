public class Main {
    public static void main(String[] args) {
        final char[] input = {8, 9};
        var t = new BrainLuck(",>,<[>[->+>+<<]>>[-<<+>>]<<<-]>>.").process(String.valueOf(input[0] + input[1]));
        System.out.println("8 * 9 = 72 which is symbol " + (char) (input[0] * input[1]) + " in ASCII");
    }
}