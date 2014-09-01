package mockmagican.util;

/**
 * A value holder that can threadsafe hold any value.
 *
 * @author armin
 */
public class ValueHolder<T> {

  private T value;

  public ValueHolder() {
    this(null);
  }

  public ValueHolder(T value) {
    set(value);
  }

  public synchronized void set(final T value) {
    this.value = value;
  }

  public synchronized T get() {
    return this.value;
  }

  public synchronized boolean isFilled() {
    return this.value != null;
  }

  public synchronized boolean isEmpty() {
    return !isFilled();
  }

  public T getAndReset() {
    try {
      return value;
    } finally {
      reset();
    }
  }

  private void reset() {
    set(null);
  }
}
