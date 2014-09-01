package mockmagican.api;

/**
 * @author armin
 */
public interface ForetellingDSLCalledHowOften {


  /**
   * this tells that a call is expected to happen
   * @return the part of the dsl where the wizard tells what will happen after the call.
   */
  ForetellingDSLReturnValue willBeCalled();

}
