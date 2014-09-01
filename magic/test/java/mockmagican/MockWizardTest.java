package mockmagican;

import mockmagican.api.MagicDisruptsTheSpaceTimeContinuumException;
import mockmagican.api.MockWizard;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MockWizardTest {

  @Test
  public void conjuresUpA_returnsInterfaceMock() throws Exception {
    // fixture: initialise a mock context
    MockWizardUtil.createNewContext();

    // execution: call conjuresUpA...
    final TestInterface result = MockWizard.conjuresUpA(TestInterface.class);

    // assertion: should have returned an instance.
    assertThat(result).describedAs("resulting object recieved by the mock wizard").isInstanceOf(TestInterface.class).isInstanceOf(MockObject.class);
    assertThat(MockWizardUtil.current().getMocks()).describedAs("mocks created for this object").hasSize(1).containsOnly(result);
  }

  @Test
  public void entersTheStage_createsNewContext() throws Exception {
    try {
      // fixture: not needed

      // execution: call entersTheStage
      MockWizard.entersTheStage();

      // assertion: should have initialised a context.
      assertThat(MockWizardUtil.current()).isNotNull();
    } finally {
      MockWizardUtil.releaseContext();
    }
  }

  @Test(expectedExceptions = MagicDisruptsTheSpaceTimeContinuumException.class, expectedExceptionsMessageRegExp = "Oops... The magican tried to change the fate of an object that was not one of his creations and thus the spacetime continuum broke.")
  public void foretells_throwsExceptionWhenParameterObjectIsNotAMockObject() throws Exception {
    // fixture: not needed

    // execution: call foretells with a non mock object
    MockWizard.foretells("test");

    // assertion: by expected exception
  }

  @Test(expectedExceptions = MagicDisruptsTheSpaceTimeContinuumException.class, expectedExceptionsMessageRegExp = "Oh no! The spacetime continuum was disrupted because the foretellings did not come true.\nMissing call to test with parameters \\[\"123\"\\]")
  public void vanished_throwsException_whenThereAreUnfulfilledGlassballs() throws Exception {
    // fixture: prepare a context with unfulfilled glassballs
    MockWizard.entersTheStage();

    final GlassBall glassBall = new GlassBall(new MyMockObject(), MyMockObject.class.getMethod("test", String.class), "123");
    glassBall.expectCall();
    MockWizardUtil.current().addExpectedCall(glassBall);

    // execution: call vanishes.
    MockWizard.vanishes();

    // assertion: by expected Exception
  }



  private static interface TestInterface {

  }

  public static class MyMockObject implements MockObject {
    public String test(String s) { return s; }
  }

}