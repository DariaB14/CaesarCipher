package com.javarush.model;

import java.util.LinkedHashMap;

public class Alphabet {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static final int LENGTH = ALPHABET.length;

    public static final LinkedHashMap<Character, Integer> ALPHABET_MAP = createMap();

    private static LinkedHashMap<Character, Integer> createMap() {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < LENGTH; i++) {
            map.put(ALPHABET[i], i);
        }
        return map;
    }
}

