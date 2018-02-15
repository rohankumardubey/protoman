package com.spotify.protoman.validation.rules;

import com.spotify.protoman.descriptor.EnumValueDescriptor;
import com.spotify.protoman.validation.ValidationRule;
import com.spotify.protoman.validation.ViolationType;
import java.util.Objects;

public class EnumNameChangeRule implements ValidationRule {

  private EnumNameChangeRule() {
  }

  public static EnumNameChangeRule create() {
    return new EnumNameChangeRule();
  }

  @Override
  public void enumValueChanged(final Context ctx,
                               final EnumValueDescriptor current,
                               final EnumValueDescriptor candidate) {
    if (!Objects.equals(current.name(), candidate.name())) {
      ctx.report(
          ViolationType.GENERATED_SOURCE_CODE_INCOMPATIBILITY_VIOLATION,
          "enum value name changed"
      );
    }

    // TODO(staffan): Detect removing UNKNOWN from name for enum value 0 (as above)
  }
}