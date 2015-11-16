package SatansDinnerParty;

public class DiningRoom {
    private int availableSpaces;
    private int numberOfPhilosophers;

    public DiningRoom(int philosophers){
        this.numberOfPhilosophers = philosophers;
        availableSpaces = numberOfPhilosophers -1;

    }

    /**
     * Metode som får filosofene til å gå inn i spisesalen
     * slik at de kan spise.
     */
    public synchronized void enterDiningRoom(){
        while(availableSpaces == 0){
            try{
                wait();
            }catch(InterruptedException e){
            }
            availableSpaces--;
        }
    }

    /**
     * Metode som får filosofene til å gå ut av spisesalen
     * slik at andre filosofer kan gå inn og spise
     */
    public synchronized void exitDiningRoom(){
        availableSpaces++;
        notifyAll();
    }
}
