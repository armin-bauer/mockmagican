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

  public void expectCall() {
    this.numExpectedCalls++;
  }

  public void setReturnValue(final Object result) {
    this.returns = result;
  }
}
