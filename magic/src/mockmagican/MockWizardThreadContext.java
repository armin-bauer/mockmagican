package mockmagican;

import mockmagican.util.ValueHolder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author armin
 */
public class MockWizardThreadContext {

  private List<MockObject> mocks = new ArrayList<>();
  private List<GlassBall> expectedCallsToMocks = new ArrayList<>();
  private ValueHolder<GlassBall> lastRecordedCall = new ValueHolder<>();
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
  public void recordMockCall(final GlassBall glassBall) {
    lastRecordedCall.set(glassBall);
  }

  /**
   * @return the last recorded call. Throws an Exception when no call is recorded. unsets the last recorded call after getting
   */
  public GlassBall getLastRecordedCall() {
    return lastRecordedCall.getAndReset();
  }

  public void setRecordModeEnabled(final boolean recordModeEnabled) {
    this.recordModeEnabled = recordModeEnabled;
  }

  public boolean isRecordModeEnabled() {
    return recordModeEnabled;
  }


  /**
   * tries to find a call that has been recorded previously.
   * @param proxy the mock object that received the call
   * @param method the called method
   * @param args the arguments that have been called
   * @return null if no glassball for that call has been found, the glassball otherwise
   */
  public GlassBall getMatchingForetoldCall(final Object proxy, final Method method, final Object[] args) {
    for (final GlassBall current : expectedCallsToMocks) {
      if (current.getMockObject() == proxy && current.getCalledMethod().equals(method) && arraysEqual(args, current.getParameters())) {
        return current;
      }
    }

    return null;
  }

  private boolean arraysEqual(final Object[] args, final Object[] parameters) {
    if (args.length != parameters.length) {
      return false;
    }

    for (int i = 0; i < parameters.length; i++) {
      if (args[i] != parameters[i] && (args[i] == null || !args[i].equals(parameters[i]))) {
        return false;
      }
    }

    return true;
  }

  public void addExpectedCall(final GlassBall call) {
    this.expectedCallsToMocks.add(call);
  }

  public boolean hasUnfulfilledForetellings() {
    for (final GlassBall g : expectedCallsToMocks) {
      if (!g.isDone()) {
        return true;
      }
    }

    return false;
  }

  public String getUnfulfilledForetellingsString() {
    final StringBuilder sb = new StringBuilder();

    for (final GlassBall g : expectedCallsToMocks) {
      if (!g.isDone()) {
        sb.append("\nMissing call to ").append(g.getCalledMethod().getName()).append(" with parameters [");
        boolean first = true;
        for (final Object o : g.getParameters()) {

          // comma separated list.
          if (!first) {
            sb.append(", ");
          }
          first = false;

          if (o instanceof String) {
            sb.append("\"").append(o).append("\"");
          } else {
            sb.append(String.valueOf(o));
          }
        }
        sb.append("]");
      }
    }

    return sb.toString();
  }
}
