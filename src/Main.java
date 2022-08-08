import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] products = {"Хлеб", "Молоко", "Мясо", "Картофель", "Конфета"};
        int[] prices = {56, 79, 550, 65, 200};
        String[] specPriceSale = {products[0], products[2], products[4]};
        for (int i = 0; i < products.length; i++) {
            System.out.print((i + 1) + ". " + products[i] + " - " + prices[i] + " руб/шт.");
            if (products[i].equals(specPriceSale[0])
                    || products[i].equals(specPriceSale[1])
                    || products[i].equals(specPriceSale[2])) {
                System.out.print("  Это товар по акции “ 3 по цене 2х ”.");
            }
            System.out.println();
        }

        int[] countOfProducts = new int[products.length];
        boolean[] fillOrNot = new boolean[products.length];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите номер товара и количество или для выхода наберите \"end\" .");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            if (input.isEmpty()) {
                System.out.println("Надо что-нибудь ввести =)");
            }
            if (input.length() < 3) {
                System.out.println("Надо вводить правильно(номер продукта пробел количество!)");
                continue;
            }
            int nomOfProd;
            int countOfProd;
            if (input.contains(" ")) {
                if (input.startsWith(" ") || input.endsWith(" ")) {
                    System.out.println("Надо вводить правильно(номер продукта пробел количество!)");
                } else {
                    int countOfRegex = 0;
                    for (int i = 1; i < input.length() - 1; i++) {
                        if (input.charAt(i) == ' ') {
                            countOfRegex++;
                        }
                    }
                    if (countOfRegex >= 2) {
                        System.out.println("Ввели лишнее.Надо вводить правильно(номер продукта пробел количество!)");
                        continue;
                    }
                    String[] parts = input.split(" ");
                    try {
                        nomOfProd = Integer.parseInt(parts[0]);
                        countOfProd = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Надо вводить цифры...");
                        continue;
                    }
                    if (nomOfProd > products.length || nomOfProd <= 0) {
                        System.out.println("Не верный номер продукта!");
                        continue;
                    }
                    if (countOfProd < 0 || countOfProd > 100) {
                        System.out.println(" Введено не приемлемое количество продуктов!");
                        continue;
                    }

                    countOfProducts[nomOfProd - 1] += countOfProd;
                    fillOrNot[nomOfProd - 1] = true;
                }

            } else {
                System.out.println("Необходимо номер продукта и количество разделить пробелом!");
            }
        }
        System.out.println("Ваша корзина :");
        int count = 1;
        int sum = 0;
        for (int i = 0; i < products.length; i++) {
            if (fillOrNot[i]) {
                if (countOfProducts[i] % 3 == 0) {
                    System.out.println(count + ". " + products[i] + "-"
                            + countOfProducts[i] + "шт. "
                            + prices[i] + " руб/шт., "
                            + ((countOfProducts[i] * prices[i]) - ((countOfProducts[i] / 3) * prices[i]) + " руб. в сумме; АКЦИЯ =)"));
                    count++;
                    sum += ((countOfProducts[i] * prices[i]) - ((countOfProducts[i] / 3) * prices[i]));
                } else {
                    System.out.println(count + ". " + products[i] + "-"
                            + countOfProducts[i] + "шт. "
                            + prices[i] + " руб/шт., "
                            + (countOfProducts[i] * prices[i]) + " руб. в сумме;");
                    count++;
                    sum += countOfProducts[i] * prices[i];
                }
            }
        }
        System.out.println("  Итого: " + sum + " руб.");
    }
}
