package mockmagican.interfaces;

import mockmagican.MockObject;
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


}