package mockmagican;

import java.lang.reflect.Method;

/**
 * A glassball is a call to
 *
 * @author armin
 */
public class GlassBall {
  private final MockObject mockObject;
  private final Method calledMethod;
  private final Object[] callParams;
  private int numExpectedCalls = 0;
  private int numActualCalls = 0;
  private Object returnValue;

  public GlassBall(final MockObject proxy, final Method method, final Object... args) {
    this.mockObject = proxy;
    this.calledMethod = method;
    this.callParams = args;
  }

  public Object getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(final Object result) {
    this.returnValue = result;
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

  public void expectCall() {
    this.numExpectedCalls++;
  }

  public int getNumActualCalls() {
    return numActualCalls;
  }

  public void increaseNumActualCalls() {
    numActualCalls++;
  }

  public boolean isDone() {
    return numExpectedCalls == numActualCalls;
  }
}
