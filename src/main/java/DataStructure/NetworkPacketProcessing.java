package DataStructure;//package DataStructure.Week_01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by romanizmalkov on 13.02.17.
 */
public class NetworkPacketProcessing {
    public static void main(String[] args) {
        NetworkPacketProcessing npp = new NetworkPacketProcessing();
        npp.init();
    }

    private NetworkBuffer buffer;
    private int time = 0;
    List<Integer> output;

    public void init(){
        Scanner sc = new Scanner(System.in);
        int bufferSize = sc.nextInt();
        this.buffer = new NetworkBuffer(bufferSize);

        int numberOfIncomingPackages = sc.nextInt();
        this.output = new ArrayList<>(numberOfIncomingPackages);

        for (int i = 0; i < numberOfIncomingPackages; i++) {
            output.add(-1);
            Packet packet = readPackage(sc, i);
            if (packet.deliveryTime != time) {
                int diff = packet.deliveryTime - time;
                for (int tick = 0; tick < diff; tick++) {
                    buffer.tick(output);
                }
                time = packet.deliveryTime;
            }
            if(packet.processTime > 0) connect(packet);
            else if(buffer.isEmpty())output.set(packet.index, packet.timeOfDone);
        }

        while (!buffer.isEmpty()){
            buffer.tick(output);
        }

        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
    }

    private void connect(Packet packet) {
        if(!buffer.add(packet))
            output.set(packet.index, -1);
    }

    private Packet readPackage(Scanner sc, Integer incomingNumber) {
        return new Packet(incomingNumber, sc.nextInt(), sc.nextInt());
    }
}

class NetworkBuffer{
    List<Packet> buffer;
    int size;

    NetworkBuffer (int size){
        this.size = size;
        this.buffer = new LinkedList<Packet>();
    }

    boolean add(Packet p){
        if(buffer.size() == size)
            return false;
        else {
            buffer.add(p);
            return true;
        }
    }

    void tick(List output){
        for (Packet packet :
                buffer) {
            if(packet.done()) {
                output.set(packet.index, packet.deliveryTime);
                buffer.remove(packet);
            }
        }
    }

    boolean isEmpty(){
        return buffer.isEmpty();
    }
}

class Packet{
    Integer index;
    int deliveryTime;
    int processTime;
    int timeOfDone;

    public Packet(Integer incomingNumber, int deliveryTime, int processTime) {
        this.index = incomingNumber;
        this.deliveryTime = deliveryTime;
        this.processTime = processTime;
        this.timeOfDone = deliveryTime;
    }


    boolean done(){
        timeOfDone++;
        return --processTime <= 0;
    }
}
