package com.diet.app.service;

import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.NutritionDto;
import com.diet.app.entity.MacroInfoEmbeddable;
import com.diet.app.entity.Nutrition;
import com.diet.app.exception.NotFoundException;
import com.diet.app.exceptions.MissingRequiredFieldException;
import com.diet.app.models.NutritionBasicInfo;
import com.diet.app.repository.NutritionRepository;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class NutritionService {
    private final NutritionRepository nutritionRepository;

    public NutritionService(NutritionRepository nutritionRepository) {
        this.nutritionRepository = nutritionRepository;
    }

    public void uploadNewNutrition(NutritionDto nutritionDto){
        nutritionRepository.save(parseToNutrition(nutritionDto));
    }

    public NutritionBasicInfo getProductBeId(int id){
        Nutrition nutrition = nutritionRepository
                .findById(Integer.valueOf(id)).orElseThrow(
                        ()->{throw new NotFoundException(id);}
        );

        return NutritionBasicInfo.fromNutrition(nutrition);
    }

    public Nutrition parseToNutrition(NutritionDto nutritionDto){
        Nutrition nutrition = new Nutrition();
        nutrition.setName(nutritionDto.getName());
        nutrition.setProducer(nutrition.getProducer());
        if(isNull(nutrition.getSize())){
            throw new MissingRequiredFieldException("Size cannot be empty");
        }
        if(isNull(nutrition.getUnit())){
            throw new MissingRequiredFieldException("Unit cannot be null");
        }
        double multiplicator = calculateMultiplayer(nutrition.getSize(), nutrition.getUnit().getTargetSize());

        nutrition.setMacroInfo(mapMacro(nutritionDto.getMacroInfo(), multiplicator));

        return nutrition;
    }

    private double calculateMultiplayer(double inputSize, double targetSize){
        return inputSize/targetSize;
    }

    private double recalculateField(double inputValue, double multiplicator){
        return inputValue * multiplicator;
    }

    MacroInfoEmbeddable mapMacro(MacroInfo macroInfo, double multiplicator){
        MacroInfoEmbeddable macroInfoEmbeddable = new MacroInfoEmbeddable();
        macroInfoEmbeddable.setCarbohydrates(recalculateField(macroInfo.getCarbohydrates(), multiplicator));
        macroInfoEmbeddable.setKcal(recalculateField(macroInfo.getKcal(), multiplicator));
        macroInfoEmbeddable.setProtein(recalculateField(macroInfo.getProtein(), multiplicator));
        macroInfoEmbeddable.setFat(recalculateField(macroInfo.getFat(), multiplicator));
        return macroInfoEmbeddable;
    }


}
