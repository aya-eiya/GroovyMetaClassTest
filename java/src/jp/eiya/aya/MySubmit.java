package jp.eiya.aya;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

interface MySubmit {
  public <T> Future<T> mySubmit(Callable clbl) throws Exception;
}
