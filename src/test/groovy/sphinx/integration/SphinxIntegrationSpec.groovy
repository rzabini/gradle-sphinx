package sphinx.integration

import gradle.GradleIntegrationSpec
import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult

class SphinxIntegrationSpec extends GradleIntegrationSpec{



    def "can generate sphinx site"(){
        when:
        directory('src/main/rst')
        copyResources('rst', 'src/main/rst')
        buildFile << buildscript()
        buildFile << """
apply plugin:'com.github.rzabini.gradle-sphinx'


""".stripIndent()

        ExecutionResult result = runTasksSuccessfully('sphinx')

        then:
        notThrown(Exception)
    }


}
