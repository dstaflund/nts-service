package com.github.dstaflund.nts.search.coordinate.validator;

import com.github.dstaflund.nts.search.coordinate.CoordinateSearchParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateDefinedValidator implements ConstraintValidator<CoordinateDefined, CoordinateSearchParams> {
    private static final Logger sLogger = LogManager.getLogger(CoordinateDefinedValidator.class);

    @Override
    public void initialize(CoordinateDefined constraint) {

    }

    @Override
    public boolean isValid(CoordinateSearchParams params, ConstraintValidatorContext ctx) {
        sLogger.debug("Validating " + params);
        return params.getLatitude() != null
            && params.getLongitude() != null;
    }
}
