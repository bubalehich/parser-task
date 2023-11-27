package ru.clevertec.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
public class DataClass {
    @JsonInclude
    private LocalDateTime localDateTime;

    @JsonProperty(required = true)
    private Date date;

    @JsonInclude
    private Instant instant;

    @JsonInclude
    private LocalDate localDate;

    @JsonProperty("aString")
    private String aString;

    private int[] oneDimensionArray;

    private String[][] twoDimensionArray;

    @JsonProperty("aList")
    private List<String> aList;

    @JsonProperty("aBoolean")
    private boolean aBoolean;

    @JsonProperty("aDouble")
    private double aDouble;

    @JsonProperty("aChar")
    private char aChar;

    private Enum anEnum;

    @JsonProperty("aNull")
    @JsonInclude
    private Object aNull;

    @JsonInclude
    private DataClass innerObject;

    @JsonProperty(value = "aMap")
    private Map<String, Integer> aMap;

    public DataClass getInnerObject() {
        return innerObject;
    }

    public enum Enum {
        FIRST,
        SECOND,
        THIRD
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public int[] getOneDimensionArray() {
        return oneDimensionArray;
    }

    public void setOneDimensionArray(int[] oneDimensionArray) {
        this.oneDimensionArray = oneDimensionArray;
    }

    public String[][] getTwoDimensionArray() {
        return twoDimensionArray;
    }

    public void setTwoDimensionArray(String[][] twoDimensionArray) {
        this.twoDimensionArray = twoDimensionArray;
    }

    public List<String> getaList() {
        return aList;
    }

    public void setaList(List<String> aList) {
        this.aList = aList;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public Enum getAnEnum() {
        return anEnum;
    }

    public void setAnEnum(Enum anEnum) {
        this.anEnum = anEnum;
    }

    public Object getaNull() {
        return aNull;
    }

    public void setaNull(Object aNull) {
        this.aNull = aNull;
    }

    public Map<String, Integer> getaMap() {
        return aMap;
    }

    public void setaMap(Map<String, Integer> aMap) {
        this.aMap = aMap;
    }

    @Override
    public String toString() {
        return "ExampleClass{" +
                "aString='" + aString + "\n" +
                ", oneDimensionArray=" + Arrays.toString(oneDimensionArray) + "\n" +
                ", twoDimensionArray=" + Arrays.toString(twoDimensionArray) + "\n" +
                ", alist=" + aList + "\n" +
                ", aBoolean=" + aBoolean + "\n" +
                ", aDouble=" + aDouble + "\n" +
                ", aChar=" + aChar + "\n" +
                ", anEnum=" + anEnum + "\n" +
                ", aNull=" + aNull + "\n" +
                ", innerObject=" + innerObject + "\n" +
                ", aMap=" + aMap + "\n" +
                '}';
    }
}
