package com.github.dstaflund.nts.search.area.validator;

import com.github.dstaflund.nts.search.area.AreaSearchParams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EastGreaterThanWestValidator implements ConstraintValidator<EastGreaterThanWest, AreaSearchParams> {

    @Override
    public void initialize(EastGreaterThanWest constraint) {

    }

    @Override
    public boolean isValid(AreaSearchParams params, ConstraintValidatorContext ctx) {
        return params.getEast() > params.getWest();
    }
}
