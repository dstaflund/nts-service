package com.github.dstaflund.nts.search.area.validator;

import com.github.dstaflund.nts.search.area.AreaSearchParams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NorthGreaterThanSouthValidator implements ConstraintValidator<NorthGreaterThanSouth, AreaSearchParams> {

    @Override
    public void initialize(NorthGreaterThanSouth constraint) {

    }

    @Override
    public boolean isValid(AreaSearchParams params, ConstraintValidatorContext ctx) {
        if (params.getNorth() == null || params.getSouth() == null) return true;
        return params.getNorth() > params.getSouth();
    }
}
