import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scheduler c = new Scheduler();
        Master p1 = new Master(c);
        Student c1 = new Student(c);
        p1.start();
        c1.start();
        try {
            p1.join();
            c1.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        // clear screen in terminal
        System.out.print("\033[H\033[2J");
        System.out.println("Your Mark: " + c1.mark +"/"+ c1.A.length);
    }
}

class Scheduler {
    private String contents;
    private boolean available = false;

    public synchronized String Answer() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Your Option : ");
        String ans = sc.nextLine();
        available = false;
        notifyAll();
        return ans;

    }

    public synchronized void Question(String value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        contents = value;
        // clear screen in terminal
        System.out.print("\033[H\033[2J");
        System.out.println(contents);
        available = true;
        notifyAll();
    }
}

class Student extends Thread {
    private Scheduler Scheduler;
    String[] A;
    int mark = 0;

    public Student(Scheduler c) {
        Scheduler = c;
        A = new String[] { "a", "a", "b", "c", "a", "a" };
    }

    public void run() {
        for (int i = 0; i < A.length; i++) {
            String ans = Scheduler.Answer();
            if (ans.equals(A[i])) {
                mark++;
            }
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }
}

class Master extends Thread {
    private Scheduler Scheduler;
    private String[] Q;

    public Master(Scheduler c) {
        Scheduler = c;
        Q = new String[] { "How many days are there in a week?\na)7\nb)8\nc)10",
                "Which planet is known as red planet?\na)Mars\nb)Mercury\nc)Earth",
                "Which is the largest ocean in the world?\na)Arctic ocean\nb)Pacific ocean\nc)Atlantic ocean",
                "Which is the largest country in the world?\na)India\nb)Phillipines\nc)Russia",
                "Who designed Java?\na)James Gosling\nb)Dennis Ritchie\nc)Guido Van Rossum",
                "What's the extension for a compiled Java file?\na).class\nb).java\nc).xml\nd).html" };
    }

    public void run() {
        for (int i = 0; i < Q.length; i++) {
            Scheduler.Question(Q[i]);
            // System.out.println((i + 1) + Q[i]);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }
}
