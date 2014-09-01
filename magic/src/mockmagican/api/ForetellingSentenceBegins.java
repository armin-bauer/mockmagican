package mockmagican.api;

/**
 * @author armin
 */
public interface ForetellingSentenceBegins {

  /**
   * a foretelling always starts with an information on what it contains
   * @param r a closure that will execute the command the wizard expects to appear (including parameters). This is for recording.
   * @return the rest of the foretelling dsl
   */
  ForetellingDSLCalledHowOften that(Runnable r);

}
