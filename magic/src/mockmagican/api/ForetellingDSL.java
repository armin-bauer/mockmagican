package mockmagican.api;

import mockmagican.GlassBall;
import mockmagican.MockObject;
import mockmagican.MockWizardUtil;

/**
* @author armin
*/
class ForetellingDSL implements ForetellingSentenceBegins, ForetellingDSLCalledHowOften, ForetellingDSLReturnValue {
  private GlassBall call;
  private final MockObject mockObject;

  ForetellingDSL(final MockObject mockObject) {
    this.mockObject = mockObject;
  }

  @Override
  public ForetellingDSLCalledHowOften that(Runnable r) {
    try {
      MockWizardUtil.goToRecordMode();

      r.run();
      this.call = MockWizardUtil.lastRecordedCall();
      MockWizardUtil.current().addExpectedCall(call);

      assert call.getMockObject() == mockObject;

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
