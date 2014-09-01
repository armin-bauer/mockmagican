package mockmagican.interfaces;

import mockmagican.MockWizardThreadContext;
import mockmagican.MockWizardUtil;

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
    }

    // no further operations implemented.
    return null;
  }


}
