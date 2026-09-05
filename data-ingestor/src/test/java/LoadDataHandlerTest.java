import com.diet.app.components.KafkaPublisher;
import com.diet.app.dto.LoadFoodDataSchema;
import com.diet.app.dto.Nutrition;
import com.diet.app.service.LoadNutritionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoadDataHandlerTest {

    @Mock
    KafkaPublisher kafkaPublisher;

    @Test
    void processSingleElementArray(){
        LoadFoodDataSchema loadFoodDataSchema = prepareBaseLoadFoodDataSchema();
        loadFoodDataSchema.setPayload("""
                [{
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
                ]
                """);

        ArgumentCaptor<Nutrition> argumentCaptor = ArgumentCaptor.forClass(Nutrition.class);
        LoadNutritionHandler loadNutritionHandler = new LoadNutritionHandler(kafkaPublisher);
        loadNutritionHandler.process(loadFoodDataSchema);
        verify(kafkaPublisher).publish(argumentCaptor.capture());
        List<Nutrition> nutritions = argumentCaptor.getAllValues();
        Assertions.assertEquals(1, nutritions.size());
        Assertions.assertEquals(0.03, nutritions.get(0).getMicroInfo().getManganese());
        Assertions.assertEquals("Chicken", nutritions.get(0).getName());
    }

    @Test
    void processTwoElementArray(){
        LoadFoodDataSchema loadFoodDataSchema = prepareBaseLoadFoodDataSchema();
        loadFoodDataSchema.setPayload("""
                [{
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
                    },
                    {
                      "name": "Fish",
                      "kcal": 130,
                      "protein": 15,
                      "producer": "ABC",
                      "carbohydrates": 30,
                      "fat": 20
                    }
                ]
                """);

        ArgumentCaptor<Nutrition> argumentCaptor = ArgumentCaptor.forClass(Nutrition.class);
        LoadNutritionHandler loadNutritionHandler = new LoadNutritionHandler(kafkaPublisher);
        loadNutritionHandler.process(loadFoodDataSchema);
        verify(kafkaPublisher, times(2)).publish(argumentCaptor.capture());
        List<Nutrition> nutritions = argumentCaptor.getAllValues();
        Assertions.assertEquals(2, nutritions.size());
        Assertions.assertEquals(0.03, nutritions.get(0).getMicroInfo().getManganese());
        Assertions.assertEquals("Chicken", nutritions.get(0).getName());
        Assertions.assertEquals("Fish", nutritions.get(1).getName());

    }

    private LoadFoodDataSchema prepareBaseLoadFoodDataSchema(){
        LoadFoodDataSchema loadFoodDataSchema = new LoadFoodDataSchema();
        loadFoodDataSchema.setSchemaVersion(1);
        loadFoodDataSchema.setDataType(MediaType.APPLICATION_JSON);
        loadFoodDataSchema.setSource("test");
        loadFoodDataSchema.setSize(1);

        return loadFoodDataSchema;
    }
}
