package com.github.dstaflund.nts.search.area.validator;

import com.github.dstaflund.nts.search.area.AreaSearchParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NorthGreaterThanSouthValidator implements ConstraintValidator<NorthGreaterThanSouth, AreaSearchParams> {
    private static final Logger sLogger = LogManager.getLogger(NorthGreaterThanSouthValidator.class);

    @Override
    public void initialize(NorthGreaterThanSouth constraint) {
    }

    @Override
    public boolean isValid(AreaSearchParams params, ConstraintValidatorContext ctx) {
        sLogger.debug("Validating " + params);
        if (params.getNorth() == null || params.getSouth() == null) return true;
        return params.getNorth() > params.getSouth();
    }
}
