package assn02;

import java.util.Scanner;
class Node {
    String _date;

    String _time;

    String _category;

    double _price;

    int _quantity;

    double _rating;

    int _duration;

    Node (String date, String time, String category, double price, int quantity, double rating, int duration) {
        _date = date;
        _time = time;
        _category = category;
        _price = price;
        _quantity = quantity;
        _rating = rating;
        _duration = duration;
    }

}

public class JavaWarmUp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        System.out.print("Number of transactions: ");
        String trans = input.nextLine();
        int int_trans = Integer.parseInt(trans);

        Node data[] = new Node[int_trans];


        double maxPrice = 0;
        double minPrice = 7849073;
        int maxPriceIndex = 0;
        int minPriceIndex = 0;
        double sumDurationBook = 0;
        double sumDurationPhone = 0;
        double sumDurationJewelry = 0;
        double sumRatingBook = 0;
        double sumRatingPhone = 0;
        double sumRatingJewelry = 0;
        double sumPriceBook = 0;
        double sumPricePhone = 0;
        double sumPriceJewelry = 0;
        int sumQuantityBook = 0;
        int sumQuantityPhone = 0;
        int sumQuantityJewelry = 0;
        int transBook = 0;
        int transPhone = 0;
        int transJewelry = 0;

        for (int i = 0; i < int_trans; i++) {
//            System.out.println("Enter data: ");

            String userInput = input.nextLine();
            String[] split_string = userInput.split(" ");
            data[i] = new Node(" ", " ", " ", 1, 1, 1, 1);
            data[i]._date = split_string[0];
            data[i]._time = split_string[1];
            data[i]._category = split_string[2];
            data[i]._price = Double.parseDouble(split_string[3]);
            data[i]._quantity = Integer.parseInt(split_string[4]);
            data[i]._rating = Double.parseDouble(split_string[5]);
            data[i]._duration = Integer.parseInt(split_string[6]);

            if (data[i]._price >= maxPrice) {
                maxPrice = data[i]._price;
                maxPriceIndex = i;
            }
            if (data[i]._price <= minPrice) {
                minPrice = data[i]._price;
                minPriceIndex = i;
            }
            if (data[i]._category.equals("phone")) {
                sumDurationPhone += data[i]._duration;
                sumRatingPhone += data[i]._rating;
                sumPricePhone += data[i]._price * data[i]._quantity;
                sumQuantityPhone += data[i]._quantity;
                transPhone += 1;
            }
            if (data[i]._category.equals("book")) {
                sumDurationBook += data[i]._duration;
                sumRatingBook += data[i]._rating;
                sumPriceBook += data[i]._price * data[i]._quantity;
                sumQuantityBook += data[i]._quantity;
                transBook += 1;
            }
            if (data[i]._category.equals("jewelry")) {
                sumDurationJewelry += data[i]._duration;
                sumRatingJewelry += data[i]._rating;
                sumPriceJewelry += data[i]._price * data[i]._quantity;
                sumQuantityJewelry += data[i]._quantity;
                transJewelry += 1;
            }
        }

        double avgDurationPhone = sumDurationPhone/transPhone;
        double avgRatingPhone = sumRatingPhone/transPhone;
        double avgPricePhone = sumPricePhone/sumQuantityPhone;

        double avgDurationBook = sumDurationBook/transBook;
        double avgRatingBook = sumRatingBook/transBook;
        double avgPriceBook = sumPriceBook/sumQuantityBook;

        double avgDurationJewelry = sumDurationJewelry/transJewelry;
        double avgRatingJewelry = sumRatingJewelry/transJewelry;
        double avgPriceJewelry = sumPriceJewelry/sumQuantityJewelry;


        System.out.println("Highest per unit sale:");
        System.out.println("\tWhen: " + data[maxPriceIndex]._date + " " + data[maxPriceIndex]._time);
        System.out.println("\tCategory: " + data[maxPriceIndex]._category);
        System.out.printf("\tPrice: %.2f\n" , maxPrice);
        System.out.printf("\tRating: %.1f\n" , data[maxPriceIndex]._rating);

        System.out.println("Lowest per unit sale:");
        System.out.println("\tWhen: " + data[minPriceIndex]._date + " " + data[minPriceIndex]._time);
        System.out.println("\tCategory: " + data[minPriceIndex]._category);
        System.out.printf("\tPrice: %.2f\n" , minPrice);
        System.out.printf("\tRating: %.1f\n" , data[minPriceIndex]._rating);

        System.out.println("Averages by book");
        System.out.println("\tQuantity: " + sumQuantityBook);
        System.out.printf("\tPrice: %.2f\n" , avgPriceBook);
        System.out.printf("\tRating: %.2f\n" , avgRatingBook);
        System.out.printf("\tDuration: %.2f\n" , avgDurationBook);

        System.out.println("Averages by jewelry");
        System.out.println("\tQuantity: " + sumQuantityJewelry);
        System.out.printf("\tPrice: %.2f\n" , avgPriceJewelry);
        System.out.printf("\tRating: %.2f\n" , avgRatingJewelry);
        System.out.printf("\tDuration: %.2f\n" , avgDurationJewelry);

        System.out.println("Averages by phone");
        System.out.println("\tQuantity: " + sumQuantityPhone);
        System.out.printf("\tPrice: %.2f\n" , avgPricePhone);
        System.out.printf("\tRating: %.2f\n" , avgRatingPhone);
        System.out.printf("\tDuration: %.2f\n" , avgDurationPhone);
    }
}


