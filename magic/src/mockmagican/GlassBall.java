package mockmagican;

import java.lang.reflect.Method;

/**
 * A glassball is a call to
 *
 * @author armin
 */
public class GlassBall {
  private MockObject mockObject;
  private Method calledMethod;
  private Object[] callParams;
  private int numExpectedCalls = 0;
  private Object returns;

  public GlassBall(final MockObject proxy, final Method method, final Object[] args) {
    this.mockObject = proxy;
    this.calledMethod = method;
    this.callParams = args;
  }

  public void expectCall() {
    this.numExpectedCalls++;
  }

  public void setReturnValue(final Object result) {
    this.returns = result;
  }

  public void setMockObject(final MockObject mockObject) {
    this.mockObject = mockObject;
  }

  public MockObject getMockObject() {
    return mockObject;
  }

  public Object[] getParameters() {
    return callParams;
  }

  public Method getCalledMethod() {
    return calledMethod;
  }
}
