import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n================ Welcome to Minesweeper ! ================\n");
        try {                         // 1 saniye (1000 milisaniye) bekliyor.
            Thread.sleep(1000); // 1 second (1000 milliseconds) standby
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("To start the game you must enter the number of rows and columns!\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException wait) {
            wait.printStackTrace();
        }
        System.out.print("Please enter the number of rows : "); // Değerlendirme Formu 7 : Matris boyutu kullanıcı tarafından belirlenmektedir.
        int row = scanner.nextInt(); // Evaluation Form 7 : Matrix size is determined by the user.

        if (row <= 0) {
            System.out.println("Please enter a positive number greater than 0!");
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException wait) {
                wait.printStackTrace();
            }
            System.out.print("Please enter the number of cols : ");
            int col = scanner.nextInt();

            if (col <= 0) {
                System.out.println("Please enter a positive number greater than 0!");
            } else {
                MineSweeper mine = new MineSweeper(row, col);
                mine.startGame();
            }
        }
    }
}
