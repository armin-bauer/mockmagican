package mockmagican.interfaces;

import mockmagican.GlassBall;
import mockmagican.MockObject;
import mockmagican.MockWizardUtil;
import mockmagican.TestInterface;
import mockmagican.api.MockWizard;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.fest.assertions.Assertions.assertThat;

public class MockWizardInterfaceInvocationHandlerTest {

  public static final String RETURN_VALUE = "return value";

  @Test
  public void invoke_returnsOwnHashcodeForHashcodeCall() throws Exception {
    // fixture: make up a proxy with that invocation handler
    final InvocationHandler handler = new MockWizardInterfaceInvocationHandler(MockObject.class, null);
    final MockObject proxy = (MockObject) Proxy.newProxyInstance(MockObject.class.getClassLoader(), new Class[] { MockObject.class }, handler);

    // execution: call hashCode on the proxy.
    final int code = proxy.hashCode();

    // assertion: should have returned the same hashcode as the invocation handler.
    assertThat(code).describedAs("hash code for the invocation handler").isEqualTo(handler.hashCode());
  }

  @Test
  public void invoke_recordsCall_whenContextIsInRecordMode() throws Exception {
    try {
      // fixture: set current context to recod mode.
      MockWizardUtil.createNewContext();

      // execution: make a call to a mock and leave current mode
      MockWizardUtil.goToRecordMode();
      final TestInterface handler = (TestInterface) Proxy.newProxyInstance(TestInterface.class.getClassLoader(), new Class[] { TestInterface.class, MockObject.class },  new MockWizardInterfaceInvocationHandler(TestInterface.class, MockWizardUtil.current()));
      handler.test("hello world", 123, 456.0f);
      MockWizardUtil.leaveRecordMode();

      // assertion: should have the last call stored.
      final GlassBall call = MockWizardUtil.lastRecordedCall();

      assertThat(call.getMockObject()).describedAs("mock object").isSameAs(handler);
      assertThat(call.getParameters()).describedAs("parameters of call").hasSize(3).containsOnly("hello world", 123, 456.0f);
      assertThat(call.getCalledMethod()).describedAs("method called on the mock object").isEqualTo(TestInterface.class.getMethod("test", String.class, Integer.class, float.class));
    } finally {
      MockWizardUtil.releaseContext();
    }
  }

  @Test
  public void invoke_matchesRecordedCalls_whenContextIsNotInRecordingMode() throws Exception {
    try {
      // fixture: record a call to a mock
      MockWizardUtil.createNewContext();
      final TestInterface myMock = MockWizard.conjuresUpA(TestInterface.class);
      final GlassBall glassBall = new GlassBall((MockObject) myMock, TestInterface.class.getMethods()[0], "hello world", 123, 456.0f);
      glassBall.setReturnValue(RETURN_VALUE);
      glassBall.expectCall();
      MockWizardUtil.current().addExpectedCall(glassBall);

      // execution: make a call to a mock
      final Object result = myMock.test("hello world", 123, 456.0f);

      // assertion: should have returned the string "return value" and crossed out the mock call
      assertThat(result).describedAs("result of call to mocked test method").isEqualTo(RETURN_VALUE);
      assertThat(glassBall.getNumActualCalls()).describedAs("number of times the method was invoked with the given set of parameters").isEqualTo(1);
    } finally {
      MockWizardUtil.releaseContext();
    }
  }


}