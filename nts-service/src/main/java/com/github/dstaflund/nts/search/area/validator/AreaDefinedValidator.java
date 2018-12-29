package com.github.dstaflund.nts.search.area.validator;

import com.github.dstaflund.nts.search.area.AreaSearchParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AreaDefinedValidator implements ConstraintValidator<AreaDefined, AreaSearchParams> {
    private static final Logger sLogger = LogManager.getLogger(AreaDefinedValidator.class);

    @Override
    public void initialize(AreaDefined constraint) {
    }

    @Override
    public boolean isValid(AreaSearchParams params, ConstraintValidatorContext ctx) {
        sLogger.debug("Validating " + params);
        return params.getNorth() != null
            && params.getSouth() != null
            && params.getEast() != null
            && params.getWest() != null;
    }
}
