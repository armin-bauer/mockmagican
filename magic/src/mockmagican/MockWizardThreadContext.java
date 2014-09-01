package mockmagican;

import mockmagican.util.ValueHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author armin
 */
public class MockWizardThreadContext {

  private List<MockObject> mocks = new ArrayList<>();
  private ValueHolder<GlassBall> callsToMocks = new ValueHolder<>();
  private boolean recordModeEnabled;

  /**
   * @return an unmodifiable list with all the current mocks.
   */
  public List<MockObject> getMocks() {
    return Collections.unmodifiableList(mocks);
  }

  public void addMock(final MockObject mock) {
    this.mocks.add(mock);
  }

  /**
   * @return true if the record mode was enabled.
   */
  public boolean isRecordMode() {
    return recordModeEnabled;
  }

  /**
   * notes the last recorded call
   * @param glassBall initial call information
   */
  public void addRecordedCall(final GlassBall glassBall) {
    callsToMocks.set(glassBall);
  }

  /**
   * @return the last recorded call. Throws an Exception when no call is recorded. unsets the last recorded call after getting
   */
  public GlassBall getLastRecordedCall() {
    return callsToMocks.getAndReset();
  }

  public void setRecordModeEnabled(final boolean recordModeEnabled) {
    this.recordModeEnabled = recordModeEnabled;
  }

  public boolean isRecordModeEnabled() {
    return recordModeEnabled;
  }
}
