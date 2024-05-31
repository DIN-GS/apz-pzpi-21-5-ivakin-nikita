package org.example;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.file.Paths;

public class ServerListener extends Thread{
    private static final String MULTICAST_GROUP = "230.0.0.0";
    private static final int PORT = 4446;

    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            socket.joinGroup(group);

            System.out.println("Multicast client listening on group " + MULTICAST_GROUP + " port " + PORT);

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                String[] array = received.split(" ");
                IoT.id = Integer.parseInt(array[0].split(":")[1]);
                IoT.login = array[1].split(":")[1];
                IoT.serverUrlSend = array[3];
                IoT.serverUrlAuth = array[5];
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get("src",  "config.txt").toString()))){
                    writer.write(IoT.serverUrlSend +"\n");
                    writer.write(IoT.serverUrlAuth +"\n");
                    writer.write(IoT.id+"\n");
                    writer.write(IoT.login+"\n");
                } catch (IOException e) {
                    System.out.println("File with url config doesn't exist update IoT! Call tech support team.");
                    break;
                }
            }
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("Something went wrong in configuring device, check app for updates and then try again.");;
        } finally {
            if (socket != null) {
                try {
                    socket.leaveGroup(InetAddress.getByName(MULTICAST_GROUP));
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ServerListener().start();
    }
}
