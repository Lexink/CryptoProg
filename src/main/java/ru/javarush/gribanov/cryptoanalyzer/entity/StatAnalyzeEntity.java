package ru.javarush.gribanov.cryptoanalyzer.entity;

public class StatAnalyzeEntity {
    private final Character charOfText;
    private final Double frequencyOfChar;

    public StatAnalyzeEntity(Character charOfText, Double frequencyOfChar) {
        this.charOfText = charOfText;
        this.frequencyOfChar = frequencyOfChar;
    }

    public Character getCharOfText() {
        return charOfText;
    }

    public Double getFrequencyOfChar() {
        return frequencyOfChar;
    }

    @Override
    public boolean equals(Object obj) {
        StatAnalyzeEntity entity = (StatAnalyzeEntity) obj;
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return Math.abs(this.frequencyOfChar - entity.frequencyOfChar) <= 0.5;
    }

    @Override
    public String toString() {
        return "Символ: " + charOfText + " Частота: " + frequencyOfChar;
    }
}
