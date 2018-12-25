package com.github.dstaflund.nts.search.name.validator;

import com.github.dstaflund.nts.search.name.NameSearchParams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameSnippetParentDefinedValidator implements ConstraintValidator<NameSnippetParentDefined, NameSearchParams> {

   public void initialize(NameSnippetParentDefined constraint) {
   }

   public boolean isValid(NameSearchParams params, ConstraintValidatorContext ctx) {
      return params.getName() != null
          || params.getSnippet() != null
          || params.getParent() != null;
   }
}
