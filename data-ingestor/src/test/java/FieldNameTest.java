import com.diet.app.mapping.resolver.FieldName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FieldNameTest {
    @Test
    void fieldNameNoMapping(){
        FieldName fieldName = new FieldName();
        String field = "field";
        Assertions.assertFalse(fieldName.hasNextPart(field));
        Assertions.assertEquals("field", fieldName.getInputField(field));
    }

    @Test
    void fieldNameMappingFlat(){
        FieldName fieldName = new FieldName(
                Map.entry("file", "file_name"),
                Map.entry("something", "something2")
        );

        Assertions.assertEquals("file_name", fieldName.getInputField("file"));
        Assertions.assertFalse(fieldName.hasNextPart("file"));
        Assertions.assertNull(fieldName.getInputField("file"));

        Assertions.assertEquals("something2", fieldName.getInputField("something"));
        Assertions.assertFalse(fieldName.hasNextPart("something"));
        Assertions.assertNull(fieldName.getInputField("something"));

    }

    @Test
    void fieldNameMappingTwoSegments(){
        FieldName fieldName = new FieldName(
                Map.entry("file", "before.file_name"),
                Map.entry("something", "before2.something2")
        );

        Assertions.assertEquals("before", fieldName.getInputField("file"));
        Assertions.assertTrue(fieldName.hasNextPart("file"));
        Assertions.assertEquals("file_name", fieldName.getInputField("file"));
        Assertions.assertFalse(fieldName.hasNextPart("file"));
        Assertions.assertNull(fieldName.getInputField("file"));

        Assertions.assertEquals("before2", fieldName.getInputField("something"));
        Assertions.assertTrue(fieldName.hasNextPart("something"));
        Assertions.assertEquals("something2", fieldName.getInputField("something"));
        Assertions.assertFalse(fieldName.hasNextPart("something"));
        Assertions.assertNull(fieldName.getInputField("something"));
    }

    @Test
    void fieldNameMappingFlatResetIndex(){
        FieldName fieldName = new FieldName(
                Map.entry("file", "file_name"),
                Map.entry("something", "something2")
        );

        Assertions.assertEquals("file_name", fieldName.getInputField("file"));
        Assertions.assertNull(fieldName.getInputField("file"));
        fieldName.resetIndexes();
        Assertions.assertEquals("file_name", fieldName.getInputField("file"));
    }
}
