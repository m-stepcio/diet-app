import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.Nutrition;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MappingResolverTest {

    @Test
    void mappingFlatJsonToNutritionObject(){
        JsonObject jsonObject = JsonParser.parseString("""
                {
                "name": "Chicken",
                "kcal":130,
                "protein": 15,
                "fat":20
                }
                """).getAsJsonObject();
        Nutrition nutrition = new Nutrition();
        nutrition.setName(jsonObject.get("name").getAsString());
        MacroInfo macroInfo = new MacroInfo();
        macroInfo.setFat(jsonObject.get("fat").getAsDouble());
        macroInfo.setKcal(jsonObject.get("kcal").getAsDouble());
        macroInfo.setProtein(jsonObject.get("protein").getAsDouble());

        nutrition.setMacroInfo(macroInfo);
        Assertions.assertEquals(jsonObject.get("kcal").getAsDouble(), nutrition.getMacroInfo().getKcal());
        Assertions.assertEquals(jsonObject.get("name").getAsString(), nutrition.getName());
        Assertions.assertEquals(jsonObject.get("protein").getAsDouble(), nutrition.getMacroInfo().getProtein());
        Assertions.assertEquals(jsonObject.get("fat").getAsDouble(), nutrition.getMacroInfo().getFat());
    }
}
