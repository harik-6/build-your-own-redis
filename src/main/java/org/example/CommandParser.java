package org.example;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    public List<String> parseInput(Socket client) {
        int value = -1;
        StringBuilder command;
        List<String> commandList = new ArrayList<>();
        int wordsToRead = 0;
        int wordsRead = 0;
        do {
            wordsRead += 1;
            command = new StringBuilder();
            do {
                try {
                    value = client.getInputStream().read();
                    command.append((char) value);
                }catch (IOException e){
                    // if socket reset error meaning we have sent output which is not suitable for redis protocol
                    System.out.println("Error reading from client "+e.getMessage());
                    System.exit(1);
                }
            } while (value != -1 && value != '\n');
            if(wordsRead==1) {
                wordsToRead = 1 + ((command.charAt(1) - '0') * 2);
            }
            if(wordsRead%2!=0){
                commandList.add(command.toString().strip().toUpperCase());
            }
        }while (wordsRead < wordsToRead);
        return commandList;
    }

    public byte[] parseOutput(String output)  {
        // start of file, end of file
        String sof = "+";
        String eof = "\r\n";
        return (sof+output+eof).getBytes();
    }
}
