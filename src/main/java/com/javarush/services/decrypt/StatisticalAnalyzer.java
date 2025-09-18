package com.javarush.services.decrypt;

import com.javarush.io.FileManager;
import com.javarush.model.Alphabet;
import com.javarush.services.TextChipherService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalyzer {
    private final TextChipherService textChipherService;
    private final FileManager fileManager;

    public StatisticalAnalyzer(TextChipherService textChipherService, FileManager fileManager) {
        this.textChipherService = textChipherService;
        this.fileManager = fileManager;
    }

    public int decrypt(String encryptedFile, String sampleFile, String outputFile) throws IOException {
        String encryptedText = fileManager.readFile(encryptedFile);
        String sampleText = fileManager.readFile(sampleFile);

        int primaryKey = findKey(encryptedText, sampleText);

        String decryptedText = textChipherService.decryptText(encryptedText, primaryKey);
        fileManager.writeFile(decryptedText, outputFile);

        return primaryKey;
    }

    private int findKey(String encryptedText, String sampleText) {
        char frequentEncrypted = findFrequentChar(encryptedText);
        char frequentSample = findFrequentChar(sampleText);

        Integer encryptedIndex = Alphabet.ALPHABET_MAP.get(frequentEncrypted);
        Integer sampleIndex = Alphabet.ALPHABET_MAP.get(frequentSample);

        if (encryptedIndex != null && sampleIndex != null) {
            int key = (encryptedIndex - sampleIndex + Alphabet.LENGTH) % Alphabet.LENGTH;
            return key;
        }
        return 0;
    }

    private char findFrequentChar(String text) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (Alphabet.ALPHABET_MAP.containsKey(c)) {
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);

            }
        }

        return frequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(' ');
    }
}

