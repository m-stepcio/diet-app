package com.diet.app.search;

import com.diet.app.entity.Nutrition;
import com.diet.app.enums.QueryOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import org.hibernate.sql.ast.tree.predicate.Predicate;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.nonNull;

public class SpecificationBuilder {
        public static Specification<Nutrition> create(
                String name, QueryOperator kcalOp, Double kcal,
                QueryOperator proteinOp, Double protein,
                QueryOperator fatOp, Double fat,
                QueryOperator carbohydratesOp, Double carbohydrates){
            Specification<Nutrition> spec =
                    ((root, query, cb) -> cb.conjunction());

            if(nonNull(kcal)){
                spec = addPredicate() spec.and((root, query, cb) -> cb)
            }
            return spec;
        }

        private Predicate addPredicate(Root<Nutrition> root, CriteriaBuilder cb,
                                       QueryOperator operator, Double value, String name){
            switch (operator){
                case EQ -> {
                    return cb.equal(root.<Double>get(name), value);
                }
                case GE -> {
                    return cb.greaterThanOrEqualTo(root.<Double>get(name), value);
                }
                case GT -> {
                    return cb.greaterThan(root.<Double>get(name), value);
                }
                case LE -> {
                    return cb.lessThanOrEqualTo(root.<Double>get(name), value);
                }
                case LT -> {
                    return cb.lessThan(root.<Double>get(name), value);
                }
            }
        }

}
