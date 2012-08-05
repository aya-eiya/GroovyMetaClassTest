package jp.eiya.aya;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

abstract class MyFuture<Object> implements Future<Object> {
  public Object get(){
    return null;
  }
  public Object get(long timeout,TimeUnit unit){
    return null;
  }
  public boolean cancel(boolean mayInterruptIfRunnnig){
    return false;
  }
  public boolean isCancelled(){
    return false;
  }
  public boolean isDone(){
    return false;
  }
}
