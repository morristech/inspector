package io.sweers.inspector.sample;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import com.google.auto.value.AutoValue;
import com.uber.rave.annotation.MustBeFalse;
import com.uber.rave.annotation.MustBeTrue;
import io.sweers.inspector.Inspector;
import io.sweers.inspector.Validator;
import io.sweers.inspector.InspectorIgnored;
import io.sweers.inspector.ValidatedBy;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AutoValue public abstract class Person {

  public abstract String firstName();

  public abstract String lastName();

  @IntRange(from = 0) public abstract int age();

  @Nullable public abstract String occupation();

  @ValidatedBy(DateValidator.class) public abstract Date birthday();

  @InspectorIgnored public abstract String uuid();

  @Size(multiple = 2) public abstract List<String> doublesOfStrings();

  @Size(3) public abstract Map<String, String> threePairs();

  @Size(min = 3) public abstract Set<String> atLeastThreeStrings();

  @Size(max = 3) public abstract Set<String> atMostThreeStrings();

  @MustBeTrue
  public final boolean checkMustBeTrue() {
    return true;
  }

  @MustBeFalse
  public final boolean checkMustBeFalse() {
    return false;
  }

  public static Validator<Person> validator(Inspector inspector) {
    return new AutoValue_Person.InspectorValidator(inspector);
  }
}