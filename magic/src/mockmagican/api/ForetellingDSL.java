package mockmagican.api;

import mockmagican.GlassBall;
import mockmagican.MockWizardUtil;

/**
* @author armin
*/
class ForetellingDSL implements ForetellingSentenceBegins, ForetellingDSLCalledHowOften, ForetellingDSLReturnValue {
  private GlassBall call;

  @Override
  public ForetellingDSLCalledHowOften that(Runnable r) {
    try {
      MockWizardUtil.goToRecordMode();

      r.run();
      this.call = MockWizardUtil.lastRecordedCall();

      return this;
    } finally {
      MockWizardUtil.leaveRecordMode();
    }
  }


  @Override
  public ForetellingDSLReturnValue willBeCalled() {
    this.call.expectCall();
    return this;
  }

  public ForetellingDSL andThenReturns(final Object result) {
    this.call.setReturnValue(result);
    return this;
  }
}
