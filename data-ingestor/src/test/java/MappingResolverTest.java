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
        JsonObject jsonObject = validNutritionJson();

        MappingResolver mappingResolver = new MappingResolver();
        Nutrition nutrition = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutrition.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutrition.getName());
        Assertions.assertEquals(15, nutrition.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutrition.getMacroInfo().getFat());
    }

    @Test
    void mappingFlatJsonToNutritionObjectWithMissingRequiredFieldThrowExceptionTest(){
        JsonObject jsonObject = validNutritionJson();
        jsonObject.remove("fat");
        MappingResolver mappingResolver = new MappingResolver();
        MissingRequiredFieldException exception = Assertions
                .assertThrows(MissingRequiredFieldException.class, () -> mappingResolver.mapTo(jsonObject));
        Assertions.assertTrue(exception.getMessage().contains("fat"));
    }

    @Test
    void mappingFlatFileOptionalFieldMissingTest(){
        JsonObject jsonObject = validNutritionJson();
        jsonObject.remove("producer");
        MappingResolver mappingResolver = new MappingResolver();
        Nutrition nutrition = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutrition.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutrition.getName());
        Assertions.assertEquals(15, nutrition.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutrition.getMacroInfo().getFat());
        Assertions.assertNull(nutrition.getProducer());
    }


    @Test
    void mappingFlatFileOptionalFieldJsonNullTest(){
        JsonObject jsonObject = validNutritionJson();
        jsonObject.add("producer", null);

        MappingResolver mappingResolver = new MappingResolver();
        Nutrition nutrition = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutrition.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutrition.getName());
        Assertions.assertEquals(15, nutrition.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutrition.getMacroInfo().getFat());
        Assertions.assertNull(nutrition.getProducer());
    }

    @Test
    void mappingValidWithMicroFields(){
        JsonObject jsonObject = validNutritionWithMicro();
        jsonObject.remove("iron");

        MappingResolver mappingResolver = new MappingResolver();
        Nutrition nutrition =  mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(190.0, nutrition.getMicroInfo().getPhosphorus());
        Assertions.assertEquals(1.2, nutrition.getMicroInfo().getZinc());
        Assertions.assertNull(nutrition.getMicroInfo().getIron());

    }

    private JsonObject validNutritionJson() {
        return JsonParser.parseString("""
            {
              "name": "Chicken",
              "kcal": 130,
              "protein": 15,
              "producer": "ABC",
              "carbohydrates": 30,
              "fat": 20
            }
            """).getAsJsonObject();
    }

    private JsonObject validNutritionWithMicro(){
        return JsonParser.parseString("""
                  {
                      "name": "Chicken",
                      "kcal": 130,
                      "protein": 15,
                      "producer": "ABC",
                      "carbohydrates": 30,
                      "fat": 20,
                      "folat": 12.5,
                      "calcium": 18.0,
                      "iron": 1.4,
                      "magnesium": 24.0,
                      "potassium": 256.0,
                      "sodium": 74.0,
                      "zinc": 1.2,
                      "selenium": 22.0,
                      "iodine": 7.0,
                      "phosphorus": 190.0,
                      "copper": 0.08,
                      "manganese": 0.03
                    }
            """).getAsJsonObject();
    }
}
