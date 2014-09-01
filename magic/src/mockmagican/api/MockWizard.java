package mockmagican.api;

/**
 * The control class for everything in the mocking framework.
 *
 * @author armin
 */
public class MockWizard {

  /**
   * this method initializes the mocking framework. It can be called before a test is executed.
   * If you write an integration adapter for a test framework, you should call it before executing the
   * test method.
   */
  public static void entersTheStage() {


  }

  /**
   * this method cleans up the mocking framework. It also will throw an Exception if an expected call
   * to a mock object was not made.
   */
  public static void vanishes() throws MagicDisruptsTheSpaceTimeContinuumException {

  }

  /**
   * this method will create a mock object
   * @param interfaceClass the interface or abstract class or concrete class to create a mock from
   * @return a mock implementation / proxy of the given interface or class.
   */
  public static <T> T conjuresUpA(final Class<T> interfaceClass) {
    return null;
  }

  /**
   * begin a foretelling by the mock wizard
   * @return a foretelling dsl.
   */
  public static ForetellingSentenceBegins foretells(final Object mockObject) {
    return null;
  }
}
