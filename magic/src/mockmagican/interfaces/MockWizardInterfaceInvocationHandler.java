package mockmagican.interfaces;

import mockmagican.MockWizardThreadContext;

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

    return null;
  }


}
