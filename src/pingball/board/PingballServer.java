package pingball.board;

/*
 * Server class

 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Scanner;

/**
 * Thread Safety Argument: All instance variables are final, which ensures
 * concrete references. serverSocket is never passed to any threads. board is
 * thread-safe because of safety implementations in the PSClientHandler and
 * Board classes. players is thread-safe because it is synchronized during all
 * accesses and modifications. It is passed to the PSClientHandler class, where
 * additional safety implementations preserve thread safety.
 * 
 * ALWAYS SYNCHRONIZE TOGETHER
 */
public class PingballServer {

    private final ServerSocket serverSocket;
    private final BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

    private Map<String, BlockingQueue<String>> queueMap = new HashMap<String, BlockingQueue<String>>();
    private Map<String, Map<String, String>> boardMap = new HashMap<String, Map<String, String>>();
    private BlockingQueue<String> queueForServer = new LinkedBlockingQueue<String>();

    /**
     * Make a Pingball server that listens for connections on port.
     * 
     * @param port
     *            port number, requires 0 <= port <= 65535
     */
    public PingballServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    /**
     * Run the server, listening for client connections and handling them. Never
     * returns unless an exception is thrown.
     * 
     * @throws IOException
     *             if the main server socket is broken (IOExceptions from
     *             individual clients do *not* terminate serve())
     */
    public void serve() throws IOException {
        while (true) {
            // block until a client connects
            System.out.println("waiting...");
            final Socket socket = serverSocket.accept();
            System.out.println("accepted socket");

            // start a new thread to handle the clients
            Thread handler = new Thread(new Runnable() {
                private Socket s = socket;
                public void run() {
                    try {
                        handle(s);
                    } catch (SocketException e) {
                        System.out.println("connection closed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            
            // thread to handle queue... and... input from user?
            // oh bother
            Thread queue = new Thread(new Runnable() {
                public void run() {
                    try {
                        String input = queueForServer.take();
                        System.out.println(input);
                        String[] tokens = input.split(" ");
                        if (tokens[0].equals("--sendBall")) {
                            BlockingQueue<String> clientQ;
                            synchronized(queueMap){
                                clientQ = queueMap.get(tokens[2]);
                            }
                            clientQ.add(input);
                            System.out.print(clientQ);
                            System.out.println("I'm the queue, and I'm done.");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //handles input  
                }
            });
            Thread user = new Thread(new Runnable() {
                public void run() {
                    try {
                        readFromUser();
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                }               
            });
            System.out.println("Starting user/queue/handler.");
            user.start();
            queue.start();
            handler.start();  
        }
    }
    
    private void readFromUser() throws Exception{
        for (String input = userInput.readLine(); input != null; input = userInput.readLine()) {;
            System.out.println("I see...");
            String[] tokens = input.split(" "); 
            if (!(tokens.length == 3)){
                continue;
            }
            System.out.println("I have 3 tokens");
            String client1 = tokens[1];
            String client2 = tokens[2];
            String oldClient1, oldClient2;
            BlockingQueue<String> queue1, queue2, oldQueue1, oldQueue2;
    
            String dir1;
            String dir2;
            String orientation;
    
            if (tokens[0].equals("v")) {
                //so client1 bottom = client2 and client2 top = client1
                //send --joinBoard client1 client2 v
                dir1 = "bottom";
                dir2 = "top";
                orientation = "v";
            } else if (tokens[0].equals("h")){
                //so client1 right = client2 and client2 left = client1
                //send --joinBoard client1 client2 h
                dir1 = "right";
                dir2 = "left";
                orientation = "h";
            } else {
                continue;
            }
            synchronized(queueMap){
                synchronized(boardMap){
                    oldClient1 = boardMap.get(client1).get(dir1);
                    boardMap.get(client1).put(dir1, client2);
                    oldClient2 = boardMap.get(client2).get(dir1);
                    boardMap.get(client2).put(dir2, client1);
                    queue1 = queueMap.get(client1);
                    queue2 = queueMap.get(client2);
                    oldQueue1 = queueMap.get(client1);
                    oldQueue2 = queueMap.get(client2);
                }
            }
            if (client1 == null || client2 == null){
                continue;
            }
            if (!(oldClient1 == null) && (!oldClient1.equals(""))) {
                String msg = "--unjoinBoard " + client1 + " " + oldClient1 + " " + orientation;
                queue1.add(msg);
                oldQueue1.add(msg);
            }
            if (!(oldClient2 == null) && (!oldClient2.equals(""))) {
                String msg = "--unjoinBoard " + oldClient2 + " " + client2 + " " + orientation;
                queue2.add(msg);
                oldQueue2.add(msg);
            }
            String joinMsg = "--joinBoard " + client1 + " " + client2 + " " + orientation;
            queue1.add(joinMsg);
            queue2.add(joinMsg);
            System.out.println("joining " + client1 + " and " + client2);
        }
    }

    /**
     * Handle multiple client connections. Returns when client disconnects.
     * 
     * @param socket
     *            socket where the client is connected
     * @throws IOException
     *             if connection has an error or terminates unexpectedly
     */
    private void handle(Socket s) throws IOException {
         final BlockingQueue<String> handlerQueue = new LinkedBlockingQueue<String>();
         final Socket sock = s;
        // handle the client
        try {
            Thread hqueue = new Thread(new Runnable() {
                public void run() {
                    try {
                        while(true){
                            System.out.println("I'm waiting to get something...");
                            String input = handlerQueue.take();
                            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                            System.out.println("sending out");
                            System.out.println("input: " + input);
                            out.println(input);     
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //handles input  
                }
            });
            hqueue.start();
            handleConnection(s, handlerQueue);
        } finally {
            s.close();
        }
    }

    /**
     * Handle a single client connection. Returns when client disconnects.
     * 
     * @param socket
     *            socket where the client is connected
     * @throws IOException
     *             if connection has an error or terminates unexpectedly
     */
    private void handleConnection(Socket s, BlockingQueue<String> q) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                handleRequest(line, s, q);
            }
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Handler for client input, performing requested operations and returning
     * an output message.
     * 
     * @param input
     *            message from client
     * @return message to client
     */
    private void handleRequest(String input, Socket s, BlockingQueue<String> q) throws IOException {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("--sendBall")) {
            queueForServer.add(input);
        } else if (tokens[0].equals("--addBoard")) {
            System.out.println("adding " + tokens[1]);
            synchronized(queueMap){    
                synchronized(boardMap){
                    queueMap.put(tokens[1], q);
                    Map<String, String> defaultMap = new HashMap<String, String>();
                    defaultMap.put("top", "");
                    defaultMap.put("bottom", "");
                    defaultMap.put("left", "");
                    defaultMap.put("right", "");
                    boardMap.put(tokens[1], defaultMap);     
                }
            }
            System.out.println(tokens[1] + ": " + q.toString());
        } else if (tokens[0].equals("--delBoard")) {
            synchronized(queueMap){
                synchronized(boardMap){
                    queueMap.remove(tokens[1]);
                    boardMap.remove(tokens[1]);  
                }
            }
        }
    }

    /**
     * Start a PingballServer using the given arguments.
     * 
     * Usage: 1. PingballClient [--port PORT] 2. PingballClient [--host HOST]
     * [--port PORT] FILE
     * 
     * HOST is an optional hostname or IP address of the server to connect to.
     * If no HOST is provided, then the client starts in single-machine play
     * mode, as described above.
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying
     * the port the server should be listening on for incoming connections.
     * Default port is 10987.
     * 
     * FILE is a required argument (if using the second command-line structure)
     * specifying a file pathname where a board has been stored. If this
     * argument is given, the stored Pingball board should be loaded as the
     * starting board.
     * 
     * The board file format is given in the project specs: will decode and
     * write over here.
     */
    public static void main(String[] args) {
        // Command-line argument parsing is provided. Do not change this method.
        int port = 10987; // default port
        File file = null;

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            return;
        }

        try {
            runPingballServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start a PingballServer running on the specified port, with a board loaded
     * from a file.
     * 
     * @param file
     *            start with a board loaded from the specified file, according
     *            to the input file format defined in the JavaDoc for main().
     * @param port
     *            The network port on which the server should listen.
     */
    public static void runPingballServer(int port) throws IOException {
        // start the server
        PingballServer server = new PingballServer(port);
        System.out.println("server made!");
        server.serve();
    }
}
