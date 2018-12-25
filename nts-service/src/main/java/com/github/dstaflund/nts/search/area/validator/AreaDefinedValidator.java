package com.github.dstaflund.nts.search.area.validator;

import com.github.dstaflund.nts.search.area.AreaSearchParams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AreaDefinedValidator implements ConstraintValidator<AreaDefined, AreaSearchParams> {

    @Override
    public void initialize(AreaDefined constraint) {

    }

    @Override
    public boolean isValid(AreaSearchParams params, ConstraintValidatorContext ctx) {
        return params.getNorth() != null
            && params.getSouth() != null
            && params.getEast() != null
            && params.getWest() != null;
    }
}
