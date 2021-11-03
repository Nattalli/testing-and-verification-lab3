package com.company;

import java.io.IOException;
import java.util.*;

public class MaxLength {

    public FileSystemDataService service;

    public MaxLength(){
        this.service = new FileReaderWrap();
    }

    public void setService(FileSystemDataService s){
        this.service = s;
    }

    public HashSet<String> main() throws IOException {

        System.out.println("Enter filepath:");

        Scanner input = new Scanner(System.in);
        String filePath = input.nextLine();

        return FileReading(filePath);
    }


    public HashSet<String> FileReading(String path) throws IOException {

        String text = this.service.getData(path);
        String[] words;
        words = text.split(" |\\r?\\n|\\t");

        for (int i = 0; i < words.length; i++){
            words[i] = words[i].substring(0, Math.min(words[i].length(), 30));
        }

        return wordsChanging(words);
    }

    public HashSet<String> wordsChanging(String[] words){

        int max = 0;

        var newWords = new HashSet<String>();
        for (int i = 0; i < words.length; i++) {

            if (Consonant(words[i]) == max)
            {
                newWords.add(words[i]);
            }

            if (Consonant(words[i]) > max)
            {
                max = Consonant(words[i]);
                newWords = new HashSet<>();
                newWords.add(words[i]);
            }
        }
        return newWords;
    }

    public int Consonant(String word){
        int count = 0;
        int max = 0;

        String consonantLetters = "BCDFGHJKLMNPQRSTVXZWY";

        for (int i = 0; i < word.length(); i++){
            if (consonantLetters.indexOf(Character.toUpperCase(word.charAt(i))) == -1)
            {
                count = 0;
            }
            else
            {
                count++;
                max = Math.max(max, count);
            }
        }

        return max;
    }

    public static String TheLongestWorld(String text) {
        return Arrays.stream(text.split("[^a-zA-Z0-9]")).max(Comparator.comparingInt(String::length)).orElse(null);
    }

    public static String WordSubstring(String text){
        StringBuilder new_text = new StringBuilder();
        for (String word : text.split("[^a-zA-Z0-9]")) {
            word = word.toLowerCase();
            if (word.length() > 30) {
                word = word.substring(0, 30);
            }
            new_text.append(word + " ");
        }
        return new_text.toString();
    }
}
