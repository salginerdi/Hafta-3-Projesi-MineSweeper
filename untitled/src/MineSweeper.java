import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // Değerlendirme Formu 5 : Proje "MineSweeper" sınıfı içinde tasarlanmıştır.
    int rowNumber; // Evaluation Form 5 : The project is designed within the "MineSweeper" class.
    int colNumber;
    int[][] map;
    int[][] board;
    int size;
    boolean gameStatus = true;

    MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new int[rowNumber][colNumber];
        this.board = new int[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public void startGame() { // Değerlendirme Formu 6 : İlgili metotlar (oyun başlatma, oyun kaybetme vs.) isimlendirmesi yapılmıştır.
        int row, col, success = 0; // Evaluation Form 6 : Related methods (starting a game, losing a game, etc.) were named.
        createGame();
        print(this.map);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================ GAME ON ! ================\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (gameStatus) {
            print(this.board); // Oyunu alanını konsolda gösteriyoruz.
            // We show the game field on the console.
            System.out.print("\nEnter row : ");
            row = scanner.nextInt();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Enter column : "); // Değerlendirme Formu 9 : Kullanıcıdan girmek istediği satır ve sütun bilgisi alınıyor.
            col = scanner.nextInt(); // Evaluation Form 9 : The row and column information that the user wants to enter is received.

            if ((row < 0 || row >= this.rowNumber) && (col < 0 || col >= this.colNumber)) {
                /* Değerlendirme Formu 10 : Kullanıcının seçtiği nokta dizinin sınırları içerisinde olup olmadığı kontrol ediliyor,
                değilse uyarı mesajı veriliyor ve tekrar giriş isteniyor.

                Evaluation Form 10 : Checking whether the point selected by the user is within the index limits; if not,
                a warning message is given and re-entry is requested. */
                System.out.println("\nInvalid Cell!\n");
                continue;
            }
            if (row < 0 || row >= this.rowNumber) {
                System.out.println("\nInvalid Cell!\n");
                continue;
            }
            if (col < 0 || col >= this.colNumber) {
                System.out.println("\nInvalid Cell!\n");
                continue;
            }
            if (this.map[row][col] != -1) {
                checkMine(row, col); // Değerlendirme Formu 14 : Tüm noktalar mayınsız bir şekilde seçilirse oyunu kazanmanın kontrolü yapılıyor.
                success++; // Evaluation Form 14 : Checking to win the game if all points are chosen without mines.
                if (success == (this.size - (this.size / 4))) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // Değerlendirme Formu 15 : Kullanıcının oyunu kaybetme ya da kazanma durumunda uygun mesajlar kullanıcıya gösteriliyor.
                    // Evaluation Form 15 : Appropriate messages are shown to the user in case of losing or winning the game.
                    System.out.println("\n================ CONGRATULATIONS, YOU WON THE GAME ! ================\n");
                    break;
                }
            } else { // Değerlendirme Formu 13 : Kullanıcının mayına bastığı durum kontrol ediliyor.
                try { // Evaluation Form 13 : The situation where the user stepped on a mine is checked.
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n================ GAME OVER ! ================\n");
                break;
            }
        }
    }

    public void createGame() { // Değerlendirme Formu 8 : Diziye boyutunun 4 te 1 i kadar mayın aşağıdaki metod ile yerleştiriliyor.
        int randomRow, randomCol; // Evaluation Form 8 : The mines are placed in the array at 1/4 of its size with the following method.
        int count = 0;
        while (count != this.size / 4) {
            randomRow = random.nextInt(this.rowNumber);
            randomCol = random.nextInt(this.colNumber);
            if (this.map[randomRow][randomCol] != -1) { // Mayınları -1 ile ifade ediyoruz.
                this.map[randomRow][randomCol] = -1; // We denote mines by -1.
                count++;
            }
        }
    }

    public boolean isValidCell(int row, int col) { // Dizinin dışını kontrol etmemesi için kurduğumuz metod.
        return row >= 0 && row < this.rowNumber && col >= 0 && col < this.colNumber; // The method we set to not check outside the array.
    }

    public void checkMine(int row, int col) {
        /*
        Değerlendirme Formu 12 : Kullanıcının girdiği alanda mayın olup olmadığını kontrol ediyor ve
        çevredeki mayın sayısı hakkında bilgi veriyoruz.

        Evaluation Form 12 : We check whether there are mines in the area the user enters and provide information
        about the number of mines in the surrounding area.
        */

        if ((isValidCell(row, col + 1) && this.map[row][col + 1] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row + 1, col) && this.map[row + 1][col] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row, col - 1) && this.map[row][col - 1] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row - 1, col) && this.map[row - 1][col] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row - 1, col - 1) && this.map[row - 1][col - 1] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row + 1, col + 1) && this.map[row + 1][col + 1] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row - 1, col + 1) && this.map[row - 1][col + 1] == -1)) {
            this.board[row][col]++;
        }
        if ((isValidCell(row + 1, col - 1) && this.map[row + 1][col - 1] == -1)) {
            this.board[row][col]++;
        }
        if ((this.board[row][col] == 0)) {
            board[row][col] = -2;
        }
    }

    // Değerlendirme Formu 11 : Kullanıcı her hamle yaptığında oyun alanının güncellendiği array metodu.
    // Evaluation Form 11 : Array method where the playing field is updated every time the user makes a move.
    public void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] >= 0) {
                    System.out.print(" ");
                }
                System.out.print(array[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}



