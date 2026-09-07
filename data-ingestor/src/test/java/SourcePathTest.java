import com.diet.app.mapping.resolver.SourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SourcePathTest {
    @Test
    void fieldNameNoMapping(){
        SourcePath sourcePath = new SourcePath();
        String field = "field";
        Assertions.assertFalse(sourcePath.hasMapping(field));
        Assertions.assertNull(sourcePath.getInputPath(field));
    }

    @Test
    void fieldNameMappingFlat(){
        SourcePath sourcePath = new SourcePath(
                Map.entry("file", "file_name"),
                Map.entry("something", "something2")
        );
        Assertions.assertTrue(sourcePath.hasMapping("file"));
        Assertions.assertEquals("file_name", sourcePath.getInputPath("file")[0]);
        Assertions.assertEquals(1, sourcePath.getInputPath("file").length);

        Assertions.assertEquals("something2", sourcePath.getInputPath("something")[0]);
        Assertions.assertTrue(sourcePath.hasMapping("something"));
        Assertions.assertEquals(1, sourcePath.getInputPath("something").length);

    }

    @Test
    void fieldNameMappingTwoSegments(){
        SourcePath sourcePath = new SourcePath(
                Map.entry("file", "before.file_name"),
                Map.entry("something", "before2.something2")
        );

        Assertions.assertEquals("before", sourcePath.getInputPath("file")[0]);
        Assertions.assertEquals("file_name", sourcePath.getInputPath("file")[1]);
        Assertions.assertTrue(sourcePath.hasMapping("file"));
        Assertions.assertEquals(2, sourcePath.getInputPath("file").length);

        Assertions.assertEquals("before2", sourcePath.getInputPath("something")[0]);
        Assertions.assertEquals("something2", sourcePath.getInputPath("something")[1]);
        Assertions.assertEquals(2, sourcePath.getInputPath("something").length);
        Assertions.assertTrue(sourcePath.hasMapping("something"));

    }

    @Test
    void fieldNameMappingFlatResetIndex(){
        SourcePath sourcePath = new SourcePath(
                Map.entry("file", "file_name"),
                Map.entry("something", "something2")
        );

        Assertions.assertTrue(sourcePath.hasMapping("file"));
        Assertions.assertTrue(sourcePath.hasMapping("something"));
        Assertions.assertFalse(sourcePath.hasMapping("something else"));


        Assertions.assertEquals(1, sourcePath.getInputPath("file").length);
        Assertions.assertEquals("file_name", sourcePath.getInputPath("file")[0]);
        Assertions.assertEquals(1, sourcePath.getInputPath("something").length);
        Assertions.assertEquals("something2", sourcePath.getInputPath("something")[0]);
    }
}
