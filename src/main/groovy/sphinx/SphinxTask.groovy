package sphinx

import jython.JythonTask
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskAction

public class SphinxTask extends JavaExec {

	SphinxTask(){
		main 'org.python.util.jython'
		classpath project.configurations.jython.asPath
		dependsOn 'jythonClasses'
	}

	static final String buildCommand='''
import sys
from sphinx import cmdline
sys.argv.pop(0)
sys.argv.insert(0, 'sphinx-build')
cmdline.main(sys.argv)
'''
	
	@TaskAction
	void exec(){

		args '-c', buildCommand, "-Drelease=$project.sphinx.release", "-Dproject=$project.sphinx.title", project.sphinx.sourceDir.absolutePath, project.sphinx.outputDir.absolutePath
        systemProperties(['python.path': project.configurations.pythonpath.asPath])

        super.exec()
	}
}