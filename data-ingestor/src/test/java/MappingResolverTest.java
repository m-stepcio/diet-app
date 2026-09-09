import com.diet.app.dto.NutritionDto;
import com.diet.app.exceptions.MissingRequiredFieldException;
import com.diet.app.mapping.resolver.SourcePath;
import com.diet.app.mapping.resolver.MappingResolver;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MappingResolverTest {

    @Test
    void mappingFlatJsonToNutritionObjectTest() {
        JsonObject jsonObject = validNutritionJson();

        MappingResolver mappingResolver = new MappingResolver();
        NutritionDto nutritionDto = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutritionDto.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutritionDto.getName());
        Assertions.assertEquals(15, nutritionDto.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutritionDto.getMacroInfo().getFat());
    }

    @Test
    void mappingFlatJsonToNutritionObjectWithMissingRequiredFieldThrowExceptionTest() {
        JsonObject jsonObject = validNutritionJson();
        jsonObject.remove("fat");
        MappingResolver mappingResolver = new MappingResolver();
        MissingRequiredFieldException exception = Assertions
                .assertThrows(MissingRequiredFieldException.class, () -> mappingResolver.mapTo(jsonObject));
        Assertions.assertTrue(exception.getMessage().contains("fat"));
    }

    @Test
    void mappingFlatFileOptionalFieldMissingTest() {
        JsonObject jsonObject = validNutritionJson();
        jsonObject.remove("producer");
        MappingResolver mappingResolver = new MappingResolver();
        NutritionDto nutritionDto = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutritionDto.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutritionDto.getName());
        Assertions.assertEquals(15, nutritionDto.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutritionDto.getMacroInfo().getFat());
        Assertions.assertNull(nutritionDto.getProducer());
    }


    @Test
    void mappingFlatFileOptionalFieldJsonNullTest() {
        JsonObject jsonObject = validNutritionJson();
        jsonObject.add("producer", null);

        MappingResolver mappingResolver = new MappingResolver();
        NutritionDto nutritionDto = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutritionDto.getMacroInfo().getKcal());
        Assertions.assertEquals("Chicken", nutritionDto.getName());
        Assertions.assertEquals(15, nutritionDto.getMacroInfo().getProtein());
        Assertions.assertEquals(20, nutritionDto.getMacroInfo().getFat());
        Assertions.assertNull(nutritionDto.getProducer());
    }

    @Test
    void mappingValidWithMicroFields() {
        JsonObject jsonObject = validNutritionWithMicro();
        jsonObject.remove("iron");

        MappingResolver mappingResolver = new MappingResolver();
        NutritionDto nutritionDto = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(190.0, nutritionDto.getMicroInfo().getPhosphorus());
        Assertions.assertEquals(1.2, nutritionDto.getMicroInfo().getZinc());
        Assertions.assertNull(nutritionDto.getMicroInfo().getIron());

    }


    @Test
    void mappingFlatJsonWithDifferentFlatFieldNames() {
        JsonObject jsonObject = JsonParser.parseString("""
                    {
                        "food_name": "Chicken",
                        "energy": 130,
                        "proteins": 15,
                        "lipids": 20,
                        "carbs": 30
                }""").getAsJsonObject();
        SourcePath fieldNames = new SourcePath(
                Map.entry("name", "food_name"),
                Map.entry("kcal", "energy"),
                Map.entry("protein", "proteins"),
                Map.entry("fat", "lipids"),
                Map.entry("carbohydrates", "carbs")
        );

        MappingResolver mappingResolver = new MappingResolver(fieldNames);
        NutritionDto nutritionDto = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutritionDto.getMacroInfo().getKcal());
        Assertions.assertEquals(30, nutritionDto.getMacroInfo().getCarbohydrates());
        Assertions.assertEquals("Chicken", nutritionDto.getName());
    }

    @Test
    void mappingFlatJsonWithDifferentFieldNames() {
        JsonObject jsonObject = JsonParser.parseString("""
                    {
                        "details": {
                            "food_name": "Chicken"
                        },
                        "food_info": {
                            "energy": 130,
                            "proteins": 15,
                            "lipids": 20,
                            "carbs": 30
                        }
                }""").getAsJsonObject();
        SourcePath fieldNames = new SourcePath(
                Map.entry("name", "details.food_name"),
                Map.entry("kcal", "food_info.energy"),
                Map.entry("protein", "food_info.proteins"),
                Map.entry("fat", "food_info.lipids"),
                Map.entry("carbohydrates", "food_info.carbs")
        );

        MappingResolver mappingResolver = new MappingResolver(fieldNames);
        NutritionDto nutritionDto = mappingResolver.mapTo(jsonObject);

        Assertions.assertEquals(130, nutritionDto.getMacroInfo().getKcal());
        Assertions.assertEquals(30, nutritionDto.getMacroInfo().getCarbohydrates());
        Assertions.assertEquals("Chicken", nutritionDto.getName());
    }

    @Test
    void reusesTheSameMappingForMultipleRecords() {
        SourcePath fieldNames = nestedFieldNames();
        MappingResolver resolver =
                new MappingResolver(fieldNames);

        NutritionDto chicken = resolver.mapTo(chickenJson());
        NutritionDto fish = resolver.mapTo(fishJson());

        Assertions.assertEquals("Chicken", chicken.getName());
        Assertions.assertEquals("Fish", fish.getName());
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

    private JsonObject validNutritionWithMicro() {
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

    private SourcePath nestedFieldNames() {
        return new SourcePath(
                Map.entry("name", "details.food_name"),
                Map.entry("kcal", "food_info.energy"),
                Map.entry("protein", "food_info.proteins"),
                Map.entry("fat", "food_info.lipids"),
                Map.entry("carbohydrates", "food_info.carbs")
        );
    }

    private JsonObject chickenJson() {
        return JsonParser.parseString("""
                {
                  "details": {
                    "food_name": "Chicken"
                  },
                  "food_info": {
                    "energy": 130,
                    "proteins": 15,
                    "lipids": 20,
                    "carbs": 30
                  }
                }
                """).getAsJsonObject();
    }

    private JsonObject fishJson() {
        return JsonParser.parseString("""
                {
                  "details": {
                    "food_name": "Fish"
                  },
                  "food_info": {
                    "energy": 90,
                    "proteins": 22,
                    "lipids": 4,
                    "carbs": 2
                  }
                }
                """).getAsJsonObject();
    }
}
