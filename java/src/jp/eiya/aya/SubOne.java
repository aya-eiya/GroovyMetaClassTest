package jp.eiya.aya;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class SubOne extends SuperOne {
  @Override
  public <T> Future<T> mySubmit(Callable clbl) throws Exception {
    final Object obj = clbl.call();
    return new MyFuture(){
       public Object get(){
         return obj;
       }
    };
  }
  @Override
  public <T> Future<T> mySubmitEx(Callable clbl) throws Exception {
    final Object obj = clbl.call();
    return new MyFuture(){
       public Object get(){
         return obj;
       }
    };
  }
}

