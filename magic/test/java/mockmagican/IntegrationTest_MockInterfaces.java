package mockmagican;

import mockmagican.api.MagicDisruptsTheSpaceTimeContinuumException;
import mockmagican.api.MockWizard;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class IntegrationTest_MockInterfaces {

  @Test
  public void interface_isMockedAndCallsAreExpected() throws Exception {
    try {
      MockWizard.entersTheStage();

      // fixture: prepare mock for the interface and expect a call to that interface
      // TODO when i get a java8, i'll make this into a closure
      final MyInterface implementation = MockWizard.conjuresUpA(MyInterface.class);
      MockWizard.foretells(implementation).that(new Runnable() { public @Override void run() { implementation.someFunction("hello", "world", 1.0f); }}).willBeCalled().andThenReturns(RESULT_STRING);

      // execution: call as foretold
      final Object result = implementation.someFunction("hello", "world", 1.0f);

      // assertion: should have returned "hello world 1.0"
      assertThat(result).describedAs("the result of the call to our mock object").isEqualTo(RESULT_STRING);
    } finally {
      MockWizard.vanishes();
    }
  }

  @Test(expectedExceptions = MagicDisruptsTheSpaceTimeContinuumException.class, expectedExceptionsMessageRegExp = "Oh no! The spacetime continuum was disrupted because the foretellings did not come true.\nMissing call to someFunction with parameters \\[\"hello\", \"world\", 1.0\\]")
  public void interface_throwsExceptionWhenVanihsing_whenForetoldMethodInvocationsArentExecuted() throws Exception {
    try {
      MockWizard.entersTheStage();

      // fixture: prepare mock for the interface and expect a call to that interface
      // TODO when i get a java8, i'll make this into a closure
      final MyInterface implementation = MockWizard.conjuresUpA(MyInterface.class);
      MockWizard.foretells(implementation).that(new Runnable() { public @Override void run() { implementation.someFunction("hello", "world", 1.0f); }}).willBeCalled().andThenReturns(RESULT_STRING);

      // execution: forget to execute the foretold method.
    } finally {
      MockWizard.vanishes();
    }
  }







  public static final String RESULT_STRING = "hello world 1.0";

  static interface MyInterface {

    Object someFunction (String p1, String p2, float p3);

    Integer someFunction(Integer p1, Integer p2, Integer p3);

    String someFuntion(float p1, float p2, String p3, String p4);
  }
}