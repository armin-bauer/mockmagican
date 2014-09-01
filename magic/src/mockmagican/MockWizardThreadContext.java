package mockmagican;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author armin
 */
public class MockWizardThreadContext {

  private List<MockObject> mocks = new ArrayList<>();
  private List<GlassBall> callsToMocks = new ArrayList<>();

  /**
   * @return an unmodifiable list with all the current mocks.
   */
  public List<MockObject> getMocks() {
    return Collections.unmodifiableList(mocks);
  }

  public <T> void addMock(final MockObject mock) {
    this.mocks.add(mock);
  }
}
