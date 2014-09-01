package mockmagican.util;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ValueHolderTest {

  public static final String STRING = "hello world";

  @Test
  public void get_returnsStoredObject() throws Exception {
    // assertion: get should return the stored object
    assertThat(new ValueHolder<>(STRING).get()).isEqualTo(STRING);
  }

  @Test(expectedExceptions = ValueHolderException.class)
  public void get_throwsException_whenHolderIsempty() throws Exception {
    // assertion: get should throw an exception if nothing is stored in the object
    new ValueHolder<>().get();
  }

  @Test
  public void getAndReset_getsTheContentsOfTheValueHolderAndThenClearsTheHolder() throws Exception {
    // fixture: prepare a valueholder
    final ValueHolder<String> holder = new ValueHolder<>();
    holder.set(STRING);

    // execution: call getAndReset
    final String result = holder.getAndReset();

    // assertion: should have returned hello world and the holder is empty.
    assertThat(result).describedAs("what we got from the value holder").isEqualTo(STRING);
    assertThat(holder.isEmpty()).describedAs("the holder is empty after calling getAndReset()").isTrue();
  }

  @Test
  public void isEmpty_returnsTrue_whenAValueIsStored() throws Exception {
    // assertion: should return true since there's a value in the value holder.
    assertThat(new ValueHolder<>().isEmpty()).isTrue();
  }

  @Test
  public void isEmpty_returnsFalse_whenAValueIsStored() throws Exception {
    // assertion: should return true since there's a value in the value holder.
    assertThat(new ValueHolder<>(STRING).isEmpty()).isFalse();
  }

  @Test
  public void isFilled_returnsFalse_whenAValueIsStored() throws Exception {
    // assertion: should return true since there's a value in the value holder.
    assertThat(new ValueHolder<>().isFilled()).isFalse();
  }

  @Test
  public void isFilled_returnsTrue_whenAValueIsStored() throws Exception {
    // assertion: should return true since there's a value in the value holder.
    assertThat(new ValueHolder<>(STRING).isFilled()).isTrue();
  }


}