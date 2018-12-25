package com.github.dstaflund.nts.search.coordinate.validator;

import com.github.dstaflund.nts.search.coordinate.CoordinateSearchParams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateDefinedValidator implements ConstraintValidator<CoordinateDefined, CoordinateSearchParams> {

    @Override
    public void initialize(CoordinateDefined constraint) {

    }

    @Override
    public boolean isValid(CoordinateSearchParams params, ConstraintValidatorContext ctx) {
        return params.getLatitude() != null
            && params.getLongitude() != null;
    }
}
