package mockmagican.interfaces;

import mockmagican.GlassBall;
import mockmagican.MockObject;
import mockmagican.MockWizardThreadContext;
import mockmagican.MockWizardUtil;
import mockmagican.api.MagicDisruptsTheSpaceTimeContinuumException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler for interfaces.
 *
 * @author armin
 */
public class MockWizardInterfaceInvocationHandler implements InvocationHandler {

  public <T> MockWizardInterfaceInvocationHandler(final Class<T> interfaceClass, final MockWizardThreadContext context) {

  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
    if ("hashCode".equals(method.getName())) {
      return hashCode();
    }

    // if we're in record mode, record the call to the mock object as a glassball object
    if (MockWizardUtil.current().isRecordMode()) {
      MockWizardUtil.recordMockCall(method, args, proxy);
      return null;
    } else {
      final GlassBall call = MockWizardUtil.current().getMatchingForetoldCall(proxy, method, args);
      if (call != null) {
        call.increaseNumActualCalls();
        return call.getReturnValue();
      } else {
        throw new MagicDisruptsTheSpaceTimeContinuumException(
          "Oh no! The fragile conjuring magic was not prepared for a call to " + new GlassBall((MockObject) proxy, method, args).describeCall() +
          " on a mocked object. It disrupted the spacetime continuum.");
      }
    }
  }


}
