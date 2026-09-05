import com.diet.app.dto.Nutrition;
import com.diet.app.exceptions.MissingRequiredFieldException;
import com.diet.app.mapping.resolver.MappingResolver;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MappingResolverTest {

    @Test
    void mappingFlatJsonToNutritionObjectTest(){
        JsonObject jsonObject = JsonParser.parseString("""
                {
                "name": "Chicken",
                "kcal":130,
                "protein": 15,
                "carbohydrates": 30,
                "fat":20
                }
                """).getAsJsonObject();
        MappingResolver mappingResolver = new MappingResolver();
        Nutrition nutrition = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutrition.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutrition.getName());
        Assertions.assertEquals(15, nutrition.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutrition.getMacroInfo().getFat());
    }

    @Test
    void mappingFlatJsonToNutritionObjectWithMissingRequiredFieldThrowExceptionTest(){
        JsonObject jsonObject = JsonParser.parseString("""
                {
                "name": "Chicken",
                "kcal":130,
                "protein": 15,
                "carbohydrates": 30
                }
                """).getAsJsonObject();
        MappingResolver mappingResolver = new MappingResolver();
        MissingRequiredFieldException exception = Assertions
                .assertThrows(MissingRequiredFieldException.class, () -> mappingResolver.mapTo(jsonObject));
        Assertions.assertTrue(exception.getMessage().contains("fat"));
    }
}
