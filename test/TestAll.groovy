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
      try{
        [sp1.mySubmit2 ({"${caseName}-Super1_2"} ${argType})
        ,sb1.mySubmit2 ({"${caseName}-Sub1_2"} ${argType})
        ,sb2.mySubmit2 ({"${caseName}-Sub2_2"} ${argType})]
      }catch(Exception e){
        [sp1.mySubmit ({"${caseName}-Super1"} ${argType})
        ,sb1.mySubmit ({"${caseName}-Sub1"} ${argType})
        ,sb2.mySubmit ({"${caseName}-Sub2"} ${argType})]
        [sp1.mySubmitEx ({"${caseName}-Super1_Ex"} ${argType})
        ,sb1.mySubmitEx ({"${caseName}-Sub1_Ex"} ${argType})
        ,sb2.mySubmitEx ({"${caseName}-Sub2_Ex"} ${argType})]
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
