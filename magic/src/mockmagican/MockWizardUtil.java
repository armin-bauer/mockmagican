package mockmagican;

import java.lang.reflect.Method;

/**
 * This is the internal management for the MockWizard. It's the API for the DSL that is used to program the wizard.
 *
 * @author armin
 */
public class MockWizardUtil {

  private static ThreadLocal<MockWizardThreadContext> context = new ThreadLocal<>();

  /**
   * sets the current context into record mode
   */
  public static void goToRecordMode() {
    current().setRecordModeEnabled(true);
  }

  /**
   * returns the last call recieved on a mock interface.
   * @return the last recorded call on a mock interface, null if none happened
   */
  public static GlassBall lastRecordedCall() {
    return current().getLastRecordedCall();
  }

  /**
   * leaves the record mode.
   */
  public static void leaveRecordMode() {
    current().setRecordModeEnabled(false);
  }


  /**
   * get the current context.
   * @return the current context.
   */
  public static MockWizardThreadContext current() {
    final MockWizardThreadContext current = context.get();

    assert current != null;

    return current;
  }

  /**
   * create a new local context.
   */
  public static void createNewContext() {
    context.set(new MockWizardThreadContext());
  }

  public static void releaseContext() {
    assert context.get() != null;

    context.remove();
  }

  /**
   * record a call to a mock
   * @param method called method
   * @param args arguments called for the mock
   * @param proxy the object the call was made on
   */
  public static void recordMockCall(final Method method, final Object[] args, final Object proxy) {
    current().addRecordedCall(new GlassBall((MockObject) proxy, method, args));
  }
}
