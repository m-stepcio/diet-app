package com.diet.app.service;

import com.diet.app.dto.MacroInfo;
import com.diet.app.dto.NutritionDto;
import com.diet.app.entity.MacroInfoEmbeddable;
import com.diet.app.entity.Nutrition;
import com.diet.app.enums.QueryOperator;
import com.diet.app.enums.Unit;
import com.diet.app.exceptions.BadUnitException;
import com.diet.app.exceptions.NotFoundException;
import com.diet.app.exceptions.MissingRequiredFieldException;
import com.diet.app.models.NutritionBasicInfo;
import com.diet.app.repository.NutritionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<NutritionBasicInfo> getProducts(String name, QueryOperator kcalOp, Double kcal,
                                                QueryOperator proteinOp, Double protein,
                                                QueryOperator fatOp, Double fat,
                                                QueryOperator carbohydratesOp, Double carbohydrates,
                                                int page, int size){
        Specification<Nutrition> specification =
                NutritionSpecifications.matches(
                        name, kcal, protein, fat, carbohydrates
                );
        nutritionRepository.findAll()
    }

    public NutritionBasicInfo getProductById(int id, double size, String unit){
        Nutrition nutrition = nutritionRepository
                .findById(id).orElseThrow(
                        ()-> new NotFoundException(id)
        );
        Unit requestedUnit = Unit.fromValue(unit);
        if(!requestedUnit.getBaseUnit().equals(nutrition.getUnit())){
            throw new BadUnitException(nutrition.getUnit().getSymbol(), requestedUnit.getSymbol());
        }
        double outputUnitMultiplayer = 1 / requestedUnit.getToBaseMultiplayer();
        double multiplicator = calculateMultiplayer(nutrition.getSize(), size, outputUnitMultiplayer);
        NutritionBasicInfo.NutritionBasicInfoBuilder nutritionBasicInfoBuilder = NutritionBasicInfo.builder();
        nutritionBasicInfoBuilder = mapProductInfo(nutritionBasicInfoBuilder, nutrition);
        nutritionBasicInfoBuilder = prepareMacroInfo(nutritionBasicInfoBuilder, nutrition, multiplicator);
        return nutritionBasicInfoBuilder.build();
    }

    public Nutrition parseToNutrition(NutritionDto nutritionDto){
        Nutrition nutrition = new Nutrition();
        nutrition.setName(nutritionDto.getName());
        nutrition.setProducer(nutrition.getProducer());
        if(isNull(nutritionDto.getSize())){
            throw new MissingRequiredFieldException("Size cannot be empty");
        }
        if(isNull(nutritionDto.getUnit())){
            throw new MissingRequiredFieldException("Unit cannot be null");
        }
        nutrition.setUnit(nutritionDto.getUnit().getBaseUnit());

        nutrition.setSize(nutrition.getUnit().getBaseRepresentationAmount());
        double multiplicator = calculateMultiplayer(nutrition.getSize(),
                nutrition.getUnit().getBaseRepresentationAmount(),
                nutritionDto.getUnit().getToBaseMultiplayer());

        nutrition.setMacroInfo(mapMacro(nutritionDto.getMacroInfo(), multiplicator));

        return nutrition;
    }

    private double calculateMultiplayer(double inputSize, double outputSize, double unitMultiplayer){
        return inputSize*unitMultiplayer/outputSize;
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

    private NutritionBasicInfo.NutritionBasicInfoBuilder mapProductInfo(
            NutritionBasicInfo.NutritionBasicInfoBuilder builder,
            Nutrition nutrition
    ){
        return builder
                .id(nutrition.getId())
                .name(nutrition.getName())
                .producent(nutrition.getProducer());
    }

    private NutritionBasicInfo.NutritionBasicInfoBuilder prepareMacroInfo(
                NutritionBasicInfo.NutritionBasicInfoBuilder builder,
                Nutrition nutrition, double multiplicator
    ){
        return builder
                .protein(nutrition.getMacroInfo().getProtein() * multiplicator)
                .carbs(nutrition.getMacroInfo().getCarbohydrates() * multiplicator)
                .fat(nutrition.getMacroInfo().getFat() * multiplicator)
                .kcal(nutrition.getMacroInfo().getKcal() * multiplicator);
    }
}
