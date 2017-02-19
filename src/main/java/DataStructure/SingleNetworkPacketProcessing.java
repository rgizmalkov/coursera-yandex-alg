package DataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by romanizmalkov on 13.02.17.
 */
public class SingleNetworkPacketProcessing {
    public static void main(String[] args) {
        SingleNetworkPacketProcessing npp = new SingleNetworkPacketProcessing();
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
                    buffer.tick(output, time);
                    time++;
                }
            }
            if(packet.processTime == 0 && buffer.isEmpty())
                output.set(packet.index, time);
            else
                connect(packet);
//            if(packet.processTime > 0) connect(packet);
//            else if(!buffer.isNonAvailable())output.set(packet.index, time);
        }

        while (!buffer.isEmpty()){
            buffer.tick(output, time);
            time++;
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
        if(isNonAvailable())
            return false;
        else {
            buffer.add(p);
            return true;
        }
    }

    void tick(List output, int time){
        int availablePower = 1;
            while (availablePower == 1) {
                if(!buffer.isEmpty()) {
                    Packet packet = buffer.get(0);
                    packet.start(time);
                    if (!(packet.processTime == 0)) availablePower--;
                    if (packet.done()) {
                        output.set(packet.index, packet.started);
                        buffer.remove(packet);
                    }
                }else {
                    break;
                }
            }
    }

    boolean isEmpty(){
        return buffer.isEmpty();
    }

    boolean isNonAvailable(){
        return buffer.size() == size;
    }
}

class Packet{
    Integer index;
    int deliveryTime;
    int processTime;
    int timeOfDone;
    int started = -2;

    public Packet(Integer incomingNumber, int deliveryTime, int processTime) {
        this.index = incomingNumber;
        this.deliveryTime = deliveryTime;
        this.processTime = processTime;
        this.timeOfDone = deliveryTime;
    }

    public void start(int time){
        if(started != -2) return;
        this.started = time;
    }


    boolean done(){
        return --processTime <= 0;
    }
}

