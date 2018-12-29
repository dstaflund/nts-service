package com.github.dstaflund.nts.search.area.validator;

import com.github.dstaflund.nts.search.area.AreaSearchParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EastGreaterThanWestValidator implements ConstraintValidator<EastGreaterThanWest, AreaSearchParams> {
    private static final Logger sLogger = LogManager.getLogger(EastGreaterThanWestValidator.class);

    @Override
    public void initialize(EastGreaterThanWest constraint) {
    }

    @Override
    public boolean isValid(AreaSearchParams params, ConstraintValidatorContext ctx) {
        sLogger.debug("Validating " + params);
        if (params.getEast() == null || params.getWest() == null) return true;
        return params.getEast() > params.getWest();
    }
}
