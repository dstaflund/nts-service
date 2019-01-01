package com.github.dstaflund.nts.search.name.validator;

import com.github.dstaflund.nts.search.name.NameSearchParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameSnippetParentDefinedValidator implements ConstraintValidator<NameSnippetParentDefined, NameSearchParams> {
   private static final Logger sLogger = LogManager.getLogger(NameSnippetParentDefinedValidator.class);

   public void initialize(NameSnippetParentDefined constraint) {
   }

   public boolean isValid(NameSearchParams params, ConstraintValidatorContext ctx) {
      sLogger.debug("Validating " + params);
      return params.getName() != null
          || params.getSnippet() != null;
   }
}
