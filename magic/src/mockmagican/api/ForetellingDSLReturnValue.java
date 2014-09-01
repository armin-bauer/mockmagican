package mockmagican.api;

/**
 * @author armin
 */
public interface ForetellingDSLReturnValue {

  /**
   * sets the return value for a call.
   * @param result the result to be used as a return value
   * @return another instance of the same object.
   */
  ForetellingDSLReturnValue andThenReturns(Object result);

}
