package ru.clevertec.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(staticName = "aData")
@AllArgsConstructor
@With
@Getter
public class TestDataBuilder {
    private String aString = "String value";

    private int[] oneDimensionArray = new int[]{1, 2, 3};

    private String[][] twoDimensionArray = new String[][]{{"a", "b", "c"}, {"d", "e", "f"}};

    private List<String> aList = List.of("hello", "world", "!");

    private boolean aBoolean = true;

    private double aDouble = 111.1;

    private char aChar = 'c';

    private Date date = new Date();

    private LocalDateTime localDateTime = LocalDateTime.now();

    private DataClass.Enum anEnum = DataClass.Enum.FIRST;


    private DataClass innerObject = DataClass.builder()
            .aString("Inner")
            .aDouble(9.99).build();

    private Map<String, Integer> aMap = Map.of("abc", 4, "defj", 5, "higk", 7);


    public DataClass build() {
        return DataClass.builder()
                .aString(aString)
                .oneDimensionArray(oneDimensionArray)
                .twoDimensionArray(twoDimensionArray)
                .aList(aList)
                .aBoolean(aBoolean)
                .aChar(aChar)
                .anEnum(anEnum)
                .aDouble(aDouble)
                .innerObject(innerObject)
                .aMap(aMap)
                .build();
    }
}
