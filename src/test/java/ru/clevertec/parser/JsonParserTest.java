package ru.clevertec.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.util.TestDataBuilder;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JsonParserTest {
    @InjectMocks
    private JsonParser parser;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        objectMapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    @DisplayName("toJson: int[]")
    void testToJsonWithPrimitiveArray() throws JsonProcessingException {
        var argument = TestDataBuilder.aData().getOneDimensionArray();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertThat(actual)
                .isNotEmpty()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("toJson: String[][]")
    void testToJsonWithTwoDimensionArray() throws JsonProcessingException {
        var argument = TestDataBuilder.aData().getTwoDimensionArray();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toJson: String")
    void testToJsonWithString() throws JsonProcessingException {
        var argument = TestDataBuilder.aData().getAString();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toJson: with Object")
    void testToJsonWithObject() throws JsonProcessingException {
        var argument = TestDataBuilder.aData()
                .withInnerObject(null)
                .build();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertNotNull(actual);
        assertThatJson(actual)
                .when(Option.IGNORING_ARRAY_ORDER)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("toJson: null")
    void testToJsonWithNull(Object argument) throws JsonProcessingException {
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toJson: List<String>")
    void testToJsonWithCollection() throws JsonProcessingException {
        var argument = TestDataBuilder.aData()
                .getAList();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toJson: Map<String, Integer>")
    void testToJsonWithMap() throws JsonProcessingException {
        var argument = TestDataBuilder.aData()
                .getAMap();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toJson: with nested object")
    void testToJsonWithNestedObject() throws JsonProcessingException {
        var argument = TestDataBuilder.aData()
                .withInnerObject(TestDataBuilder.aData()
                        .build())
                .build();
        var expected = objectMapper.writeValueAsString(argument);

        var actual = parser.fromObject(argument);

        assertThatJson(actual).isEqualTo(expected);
    }
}