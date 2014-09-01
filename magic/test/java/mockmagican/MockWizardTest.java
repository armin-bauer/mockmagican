package mockmagican;

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




  private static interface TestInterface {

  }

}