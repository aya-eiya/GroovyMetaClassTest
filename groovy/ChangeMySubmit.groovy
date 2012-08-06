import jp.eiya.aya.*
import java.util.concurrent.Callable

MySubmit.metaClass.define {
  mySubmit = {Closure closure ->
    println "${delegate} Interface method changed."
    return delegate.mySubmit (new Callable(){
      @Override
      Object call() {
        return closure()
      }
    })
  }
}

