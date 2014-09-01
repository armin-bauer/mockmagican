package mockmagican.interfaces;

import mockmagican.GlassBall;
import mockmagican.MockObject;
import mockmagican.MockWizardUtil;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.fest.assertions.Assertions.assertThat;

public class MockWizardInterfaceInvocationHandlerTest {

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


}