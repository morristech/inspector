package io.sweers.inspector.sample;

import io.sweers.inspector.Inspector;
import io.sweers.inspector.Types;
import io.sweers.inspector.ValidationException;
import io.sweers.inspector.Validator;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Validator_Person extends Validator<Person> {
  private final Validator<String> firstNameValidator;

  private final Validator<String> lastNameValidator;

  private final Validator<int[]> favoriteNumbersValidator;

  private final Validator<List<String>> aListValidator;

  private final Validator<Map<String, String>> aMapValidator;

  private final Validator<Set<String>> favoriteFoodsValidator;

  private final Validator<String> stringDefCheckedValidator;

  private final Validator<Integer> intDefCheckedValidator;

  private final Validator<Integer> ageValidator;

  private final Validator<String> occupationValidator;

  private final Validator<Date> birthdayValidator;

  private final Validator<List<String>> doublesOfStringsValidator;

  private final Validator<Map<String, String>> threePairsValidator;

  private final Validator<Set<String>> atLeastThreeStringsValidator;

  private final Validator<Set<String>> atMostThreeStringsValidator;

  private final Validator<Boolean> checkMustBeTrueValidator;

  private final Validator<Boolean> checkMustBeFalseValidator;

  public Validator_Person(Inspector inspector) {
    this.firstNameValidator = inspector.validator(String.class);
    this.lastNameValidator = inspector.validator(String.class);
    this.favoriteNumbersValidator = inspector.validator(int[].class);
    this.aListValidator = inspector.validator(Types.newParameterizedType(List.class, String.class));
    this.aMapValidator = inspector.validator(Types.newParameterizedType(Map.class, String.class, String.class));
    this.favoriteFoodsValidator = inspector.validator(Types.newParameterizedType(Set.class, String.class));
    this.stringDefCheckedValidator = inspector.validator(String.class);
    this.intDefCheckedValidator = inspector.validator(int.class);
    this.ageValidator = inspector.validator(int.class);
    this.occupationValidator = inspector.validator(String.class);
    this.birthdayValidator = new DateValidator();
    this.doublesOfStringsValidator = inspector.validator(Types.newParameterizedType(List.class, String.class));
    this.threePairsValidator = inspector.validator(Types.newParameterizedType(Map.class, String.class, String.class));
    this.atLeastThreeStringsValidator = inspector.validator(Types.newParameterizedType(Set.class, String.class));
    this.atMostThreeStringsValidator = inspector.validator(Types.newParameterizedType(Set.class, String.class));
    this.checkMustBeTrueValidator = inspector.validator(boolean.class);
    this.checkMustBeFalseValidator = inspector.validator(boolean.class);
  }

  @Override
  public void validate(Person value) throws ValidationException {
    String firstName = value.firstName();
    if (firstName == null) {
      throw new ValidationException("firstName() is not null but returns a null");
    }
    firstNameValidator.validate(firstName);
    String lastName = value.lastName();
    if (lastName == null) {
      throw new ValidationException("lastName() is not null but returns a null");
    }
    lastNameValidator.validate(lastName);
    int[] favoriteNumbers = value.favoriteNumbers();
    if (favoriteNumbers == null) {
      throw new ValidationException("favoriteNumbers() is not null but returns a null");
    }
    favoriteNumbersValidator.validate(favoriteNumbers);
    List<String> aList = value.aList();
    if (aList == null) {
      throw new ValidationException("aList() is not null but returns a null");
    }
    aListValidator.validate(aList);
    Map<String, String> aMap = value.aMap();
    if (aMap == null) {
      throw new ValidationException("aMap() is not null but returns a null");
    }
    aMapValidator.validate(aMap);
    Set<String> favoriteFoods = value.favoriteFoods();
    if (favoriteFoods == null) {
      throw new ValidationException("favoriteFoods() is not null but returns a null");
    }
    favoriteFoodsValidator.validate(favoriteFoods);
    String stringDefChecked = value.stringDefChecked();
    if (stringDefChecked == null) {
      throw new ValidationException("stringDefChecked() is not null but returns a null");
    }
    stringDefCheckedValidator.validate(stringDefChecked);
    if (!("foo".equals(stringDefChecked) && "foo2".equals(stringDefChecked))) {
      throw new ValidationException("stringDefChecked's value must be within scope of its StringDef. Is " + stringDefChecked);
    }
    int intDefChecked = value.intDefChecked();
    intDefCheckedValidator.validate(intDefChecked);
    if (!(intDefChecked != 0)) {
      throw new ValidationException("intDefChecked's value must be within scope of its IntDef. Is " + intDefChecked);
    }
    int age = value.age();
    ageValidator.validate(age);
    if (age < 0) {
      throw new ValidationException("age must be greater than 0 but is " + age);
    }
    String occupation = value.occupation();
    occupationValidator.validate(occupation);
    Date birthday = value.birthday();
    if (birthday == null) {
      throw new ValidationException("birthday() is not null but returns a null");
    }
    birthdayValidator.validate(birthday);
    List<String> doublesOfStrings = value.doublesOfStrings();
    if (doublesOfStrings == null) {
      throw new ValidationException("doublesOfStrings() is not null but returns a null");
    }
    doublesOfStringsValidator.validate(doublesOfStrings);
    int doublesOfStringsSize = doublesOfStrings.size();
    if (doublesOfStringsSize % 2 != 0) {
      throw new ValidationException("doublesOfStrings's size must be a multiple of 2 but is " + doublesOfStringsSize);
    }
    Map<String, String> threePairs = value.threePairs();
    if (threePairs == null) {
      throw new ValidationException("threePairs() is not null but returns a null");
    }
    threePairsValidator.validate(threePairs);
    int threePairsSize = threePairs.size();
    if (threePairsSize != 3) {
      throw new ValidationException("threePairs's size must be exactly 3 but is " + threePairsSize);
    }
    Set<String> atLeastThreeStrings = value.atLeastThreeStrings();
    if (atLeastThreeStrings == null) {
      throw new ValidationException("atLeastThreeStrings() is not null but returns a null");
    }
    atLeastThreeStringsValidator.validate(atLeastThreeStrings);
    int atLeastThreeStringsSize = atLeastThreeStrings.size();
    if (atLeastThreeStringsSize < 3) {
      throw new ValidationException("atLeastThreeStrings's size must be greater than 3 but is " + atLeastThreeStringsSize);
    }
    Set<String> atMostThreeStrings = value.atMostThreeStrings();
    if (atMostThreeStrings == null) {
      throw new ValidationException("atMostThreeStrings() is not null but returns a null");
    }
    atMostThreeStringsValidator.validate(atMostThreeStrings);
    int atMostThreeStringsSize = atMostThreeStrings.size();
    if (atMostThreeStringsSize > 3) {
      throw new ValidationException("atMostThreeStrings's size must be less than 3 but is " + atMostThreeStringsSize);
    }
    boolean checkMustBeTrue = value.checkMustBeTrue();
    checkMustBeTrueValidator.validate(checkMustBeTrue);
    if (!value.checkMustBeTrue()) {
      throw new ValidationException("checkMustBeTrue must be true but is false");
    }
    boolean checkMustBeFalse = value.checkMustBeFalse();
    checkMustBeFalseValidator.validate(checkMustBeFalse);
    if (value.checkMustBeFalse()) {
      throw new ValidationException("checkMustBeFalse must be false but is true");
    }
  }
}