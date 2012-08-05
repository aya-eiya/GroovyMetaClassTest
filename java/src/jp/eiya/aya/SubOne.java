package jp.eiya.aya;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class SubOne extends SuperOne {
  @Override
  public <T> Future<T> mySubmit(Callable clbl) throws Exception {
    clbl.call();
    return null;
  }
}

