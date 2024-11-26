import java.util.Scanner;

class DemoApplication {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scan = new Scanner(System.in);
        String input = new String();
        System.out.println("My calculator\ninput your exercise when you want to finish type exit\n");
        input = scan.next();
        while (!input.equals("exit")) {
            try {
                System.out.println(calculator.calculate(input));
            } catch (Exception e) {
                System.out.println("You have problem!!!\n" + e.getMessage());
            }
            System.out.println("Enter another exercise or press Exit");
            input = scan.next();
        }
    }
}
