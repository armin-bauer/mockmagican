package mockmagican;

/**
 * This is the internal management for the MockWizard. It's the API for the DSL that is used to program the wizard.
 *
 * @author armin
 */
public class MockWizardUtil {

  private ThreadLocal<MockWizardThreadContext> context = new ThreadLocal<>();

  public static void goToRecordMode() {

  }

  public static GlassBall lastRecordedCall() {
    return null;
  }

  public static void leaveRecordMode() {

  }
}
