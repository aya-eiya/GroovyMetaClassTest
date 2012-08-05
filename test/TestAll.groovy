def createTestShell = {
  def loader = new GroovyClassLoader()
  loader.addClasspath('../lib/metaTestJava.jar')
  new GroovyShell(loader)
}


def testTheCase = { caseName,expectation ->
  def sh=createTestShell()
  sh.evaluate("../groovy/${caseName}.groovy" as File)
  assert(expectation==
    sh.evaluate(
      """
        import jp.eiya.aya.*
        def printlnAndReturn={println it;it}
        def sp1Res = new SuperOne().mySubmit {printlnAndReturn "${caseName}-Super1"; }
        def sb1Res = new SubOne().mySubmit {printlnAndReturn "${caseName}-Sub1";   }
        def sb2Res = new SubTwo().mySubmit {printlnAndReturn "${caseName}-Sub2";   }
        [sp1Res,sb1Res,sb2Res]
      """
    )
  )
}

def testCases =
  [ 'changeMySubmit': [null,null,null],
    'changeSuperOne': [null,null,null],
    'changeSubOne'  : [null,null,null],
    'changeSubTwo'  : [null,null,null]]

testCases.each {
  testTheCase(it.key,it.value)
}
