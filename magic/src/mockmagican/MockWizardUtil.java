package mockmagican;

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

  }

  /**
   * returns the last call recieved on a mock interface.
   * @return the last recorded call on a mock interface, null if none happened
   */
  public static GlassBall lastRecordedCall() {
    return null;
  }

  /**
   * leaves the record mode.
   */
  public static void leaveRecordMode() {

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
}
