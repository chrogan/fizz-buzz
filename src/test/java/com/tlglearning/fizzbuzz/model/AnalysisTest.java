package com.tlglearning.fizzbuzz.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.DirectoryNotEmptyException;
import java.util.EnumSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class AnalysisTest {

private Analysis analysis;

  @BeforeEach
  void setUp() {
    analysis = new Analysis();
  }

  @ParameterizedTest
  @ValueSource(ints = {3,33,999_999_999})
  void analyze_fizz(int value) {
    Set<State> expected = EnumSet.of(State.FIZZ);
    assertEquals(expected, analysis.analyze(value));
  }

  @ParameterizedTest
  @ValueSource(ints = {5,95,10000000})
  void analyze_buzz(int value){
    Set<State> expected = EnumSet.of(State.BUZZ);
    assertEquals(expected, analysis.analyze(value));
  }

  @ParameterizedTest
  @ValueSource (ints = {0, 15, 30000000})
  void analyze_fizzBuzz(int value){
    Set<State> expected = EnumSet.of(State.FIZZ, State.BUZZ);
    assertEquals(expected, analysis.analyze(value));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "neither.csv" , numLinesToSkip = 1)
  void neither_fizz_or_buzz(int value){
    Set<State> expected = EnumSet.noneOf(State.class);
    assertEquals(expected, analysis.analyze(value));
  }

  @ParameterizedTest
  @ValueSource (ints = {-10000000,-60,-5,-3,-1 })
  void analyze_negative(int value){
    try{
      analysis.analyze(value);
      fail();
    }catch(IllegalArgumentException e) {
      //Do nothing; this is the expected behavior.
    }
  }
}