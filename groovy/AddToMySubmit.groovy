import jp.eiya.aya.*
import java.util.concurrent.Callable

MySubmit.metaClass.define {
  mySubmit2 = {Closure closure ->
    println "${delegate} Interface method added."
    return delegate.mySubmit (new Callable(){
      @Override
      Object call() {
        return closure()
      }
    })
  }
}
