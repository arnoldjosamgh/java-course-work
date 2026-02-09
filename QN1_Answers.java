import java.util.Scanner;

public class QN1_Answers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int grade1 = 0;
        int grade2 = 0;
        int grade3 = 0;
        int grade4 = 0;
        int grade5 = 0;
        int grade6 = 0;
        int grade7 = 0;
        int grade8 = 0;
        int grade9 = 0;
        int count = 0;

        while (count < 5) {
            System.out.print("Enter student score out of 100: ");
            int score = scanner.nextInt();
            
            int grade = 0;
            String remark = "";

            if (score >= 80 && score <= 100) {
                grade = 1;
                remark = "D1";
                grade1++;
            } else if (score >= 75 && score <= 79) {
                grade = 2;
                remark = "D2";
                grade2++;
            } else if (score >= 66 && score <= 74) {
                grade = 3;
                remark = "C3";
                grade3++;
            } else if (score >= 60 && score <= 65) {
                grade = 4;
                remark = "C4";
                grade4++;
            } else if (score >= 50 && score <= 59) {
                grade = 5;
                remark = "C5";
                grade5++;
            } else if (score >= 45 && score <= 49) {
                grade = 6;
                remark = "C6";
                grade6++;
            } else if (score >= 35 && score <= 44) {
                grade = 7;
                remark = "P7";
                grade7++;
            } else if (score >= 30 && score <= 34) {
                grade = 8;
                remark = "P8";
                grade8++;
            } else if (score >= 0 && score <= 29) {
                grade = 9;
                remark = "F";
                grade9++;
            } else {
                System.out.println("Invalid score. Please enter a score between 0 and 100.");
                continue;
            }

            System.out.println("Score: " + score);
            System.out.println("Grade: " + grade);
            System.out.println("Remark: " + remark);
            System.out.println("---------------------------");
            count++;
        }

        System.out.println("\nGrade Summary:");
        System.out.println("Grade 1: " + grade1);
        System.out.println("Grade 2: " + grade2);
        System.out.println("Grade 3: " + grade3);
        System.out.println("Grade 4: " + grade4);
        System.out.println("Grade 5: " + grade5);
        System.out.println("Grade 6: " + grade6);
        System.out.println("Grade 7: " + grade7);
        System.out.println("Grade 8: " + grade8);
        System.out.println("Grade 9: " + grade9);

        scanner.close();
    }
}
