package com.javarush.services.decrypt;

import com.javarush.io.FileManager;
import com.javarush.model.Alphabet;
import com.javarush.services.TextChipherService;

import java.io.IOException;

public class BruteForce {
    private final TextChipherService textChipherService;
    private final FileManager fileManager;

    private static final char[] FREQUENT_LETTERS = {' ', 'о', 'е', 'а', 'и', 'н', 'т', 'с', 'р', 'в'};
    private static final String[] COMMON_WORDS = {" и ", " в ", " не ", " на ", " что ", " с ", " по ", " к "};

    public BruteForce(TextChipherService textChipherService, FileManager fileManager) {
        this.textChipherService = textChipherService;
        this.fileManager=fileManager;
    }

    public int decryptByBruteForce(String encryptedFile, String outputFile) throws IOException {
        String encryptedText=fileManager.readFile(encryptedFile);

        int primaryKey=0;
        int primaryScore=-1;

        for (int key = 1; key< Alphabet.LENGTH; key++){
            String decryptedText=textChipherService.decryptText(encryptedText, key);
            int score =calculateScore(decryptedText);

            if(score>primaryScore){
                primaryScore=score;
                primaryKey=key;
            }
        }

        String bestText=textChipherService.decryptText(encryptedText, primaryKey);
        fileManager.writeFile(bestText, outputFile);

        return primaryKey;
    }

    private int calculateScore(String text){
        int score=0;
        String lowerText=text.toLowerCase();

        long spaces=lowerText.chars().filter(c -> c == ' ').count();
        score+=spaces*3;

        for (char letter : FREQUENT_LETTERS){
            long count=lowerText.chars().filter(c -> c == letter).count();
            score+=count;
        }

        for (String word : COMMON_WORDS){
            if(lowerText.contains(word)){
                score+=word.length()*10;
            }
        }

        return score;
    }
}
