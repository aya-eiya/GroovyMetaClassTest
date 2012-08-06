def createTestShell = {
  def loader = new GroovyClassLoader()
  loader.addClasspath('../lib/metaTestJava.jar')
  new GroovyShell(loader)
}

def test = { caseName,argType = "" ->
  def sh=createTestShell()
  sh.evaluate("../groovy/${caseName}.groovy" as File)
  sh.evaluate(
    """
      import jp.eiya.aya.*
      def sp1=new SuperOne()
      def sb1=new SubOne()
      def sb2=new SubTwo()
      def f={println it;it}
      try{
        [sp1.mySubmit2 ({f "${caseName}-Super1_2(${argType})"} ${argType})
        ,sb1.mySubmit2 ({f "${caseName}-Sub1_2(${argType})"} ${argType})
        ,sb2.mySubmit2 ({f "${caseName}-Sub2_2(${argType})"} ${argType})]
      }catch(Exception e){
        [sp1.mySubmit ({f "${caseName}-Super1(${argType})"} ${argType})
        ,sb1.mySubmit ({f "${caseName}-Sub1(${argType})"} ${argType})
        ,sb2.mySubmit ({f "${caseName}-Sub2(${argType})"} ${argType})]
        [sp1.mySubmitEx ({f "${caseName}-Super1_Ex(${argType})"} ${argType})
        ,sb1.mySubmitEx ({f "${caseName}-Sub1_Ex(${argType})"} ${argType})
        ,sb2.mySubmitEx ({f "${caseName}-Sub2_Ex(${argType})"} ${argType})]
      }
    """
  )
}

def testCases = [ 'Original'      ,
                  'ChangeMySubmit',
                  'ChangeSuperOne',
                  'ChangeSubOne'  ,
                  'ChangeSubTwo'  ,
                  'AddToMySubmit' ]
println 'Dynamic Typing Case'
testCases.each { test it }
println 'Static Typing Case'
testCases.each { test it,"as Closure"}
