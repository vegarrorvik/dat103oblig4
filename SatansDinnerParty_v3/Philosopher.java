package SatansDinnerParty_v3;

/**
 * Created by André, Vegar, Jostein, Simon
 */
public class Philosopher extends Thread implements Runnable{
    int index;
    int numberInDiningRoom = 0;

    MySemaphore[] chopsticks = Main.chopsticks;

    public Philosopher(int number){
        this.index = number;
    }

    /**
     * Makes the philosopher eat. It will pick up two chopsticks, eat, wait a bit,
     * put down the chopsticks, and then wait a little more.
     */
    public void eat(){
        chopsticks[index].w8();
        chopsticks[(index +1)%5].w8();
        System.out.println("Philosopher " + (index + 1) + " is eating. ");
        SleepingTimers.sleep();
        chopsticks[index].signal();
        chopsticks[(index +1)%5].signal();
        SleepingTimers.sleep(1);
        System.out.println("Philosopher " + (index + 1) + " stopped eating");
    }

    /**
     * Makes the philosopher think.
     */
    public void think(){
        System.out.println("Philosopher " + (index + 1) + " is thinking");
        SleepingTimers.sleep();
    }

    /**
     * Makes the philosopher enter the dining room, and asserts that
     * there are no more than 5 people in the room
     */
    public void enterDiningRoom(){
        numberInDiningRoom++;
        System.out.println("Filosof " + (index +1) + " går inn i spisesalen.");
        assert numberInDiningRoom < 5;
    }

    /**
     * Makes the philosopher exit the dining room, and asserts that
     * there are no fewer than 0(1) people in the room.
     */
    public void exitDiningRoom(){
        numberInDiningRoom--;
        System.out.println("Filosof " + (index +1) + " går ut av spisesalen.");
        assert numberInDiningRoom >=0;
    }

    @Override
    public void run() {
        while (1<2) {
            enterDiningRoom();
            eat();
            exitDiningRoom();
            think();
        }
    }
}
