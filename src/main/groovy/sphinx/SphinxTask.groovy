package sphinx

import jython.JythonTask

import org.gradle.api.tasks.TaskAction

public class SphinxTask extends JythonTask{

	
	
	static final String buildCommand='''
import sys
from sphinx import cmdline
sys.argv.pop(0)
sys.argv.insert(0, 'sphinx-build')
cmdline.main(sys.argv)
'''
	
	@TaskAction
	void exec(){

		args '-c', buildCommand, "-Drelease=$project.sphinx.release", project.sphinx.sourceDir.absolutePath, project.sphinx.outputDir.absolutePath
		super.exec()
	}

	
	
}