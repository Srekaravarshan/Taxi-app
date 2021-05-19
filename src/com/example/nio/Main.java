package com.example.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();  // getByName()
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
//            String echoString;

//            do {
//                System.out.println("Enter string to be echoed: " );
//                echoString = scanner.nextLine();

//                byte[] buffer = echoString.getBytes();
//
//                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
//                datagramSocket.send(packet);
//
//                byte[] buffer2 = new byte[50];
//                packet = new DatagramPacket(buffer2, buffer2.length);
//                datagramSocket.receive(packet);
//                System.out.println("Text received is: " + new String(buffer2, 0, packet.getLength()));

//            } while(!echoString.equals("exit"));


            for (int i=0; i< 10; i++) {
                System.out.print("Customer Id: ");
                int customerId = scanner.nextInt();
                System.out.print("Pickup Point: ");
                String pickupPoint = scanner.next();
                System.out.print("Drop Point: ");
                String dropPoint = scanner.next();
                System.out.print("Pickup Time: ");
                double pickupTime = scanner.nextDouble();

                byte[] buffer = (customerId + " " + pickupPoint + " " + dropPoint + " " + pickupTime).getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                byte[] buffer2 = new byte[50];
                do {
                    packet = new DatagramPacket(buffer2, buffer2.length);
                    datagramSocket.receive(packet);
                    System.out.println("Text received is: " + new String(buffer2, 0, packet.getLength()));
                } while (new String(buffer2, 0, packet.getLength()).equals("exit"));


//                if (TaxiHandler.getInstance().bookTaxi(customerId, pickupPoint, dropPoint, pickupTime) != null) {
//                    System.out.println("Taxi was booked!");
//                    System.out.println();
//                } else {
//                    System.out.println("Taxi cannot be allocated!");
//                }
            }
        } catch(SocketTimeoutException e) {
            System.out.println("The socket timed out");
        } catch(IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }







    }
}
