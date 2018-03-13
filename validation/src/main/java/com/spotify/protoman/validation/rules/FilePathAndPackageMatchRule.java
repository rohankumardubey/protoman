package com.spotify.protoman.validation.rules;

import com.google.common.base.Joiner;
import com.spotify.protoman.descriptor.FileDescriptor;
import com.spotify.protoman.validation.ValidationContext;
import com.spotify.protoman.validation.ValidationRule;
import com.spotify.protoman.validation.ViolationType;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FilePathAndPackageMatchRule implements ValidationRule {

  private FilePathAndPackageMatchRule() {
  }

  public static FilePathAndPackageMatchRule create() {
    return new FilePathAndPackageMatchRule();
  }

  @Override
  public void validateFile(final ValidationContext ctx, final FileDescriptor candidate) {
    // Package name should match file path
    if (!filePathMatchesPackage(candidate)) {
      ctx.report(
          ViolationType.BEST_PRACTICE_VIOLATION,
          "proto file path must match package name"
      );
    }
  }

  private boolean filePathMatchesPackage(final FileDescriptor descriptor) {
    final Path parent = Paths.get(descriptor.file().name()).getParent();
    return parent != null && Objects.equals(Joiner.on(".").join(parent), descriptor.protoPackage());
  }
}