package sphinx

import jython.JythonPlugin
import jython.JythonTask
import org.gradle.api.Project
import org.gradle.api.Plugin

public class SphinxPlugin implements Plugin<Project> {

	def quickstart= {project -> """
import sys
from sphinx.quickstart import generate
d = {}
d['path'] = '$project.sphinx.sourceDir'.replace('\\r','\\\\r')
d['project']= '$project.name'
d['author']='$project.sphinx.author'
d['version']='$project.version'
d['release']='$project.version'
d['master']='index'
d['sep']=False
d['dot']='_'
d['suffix']='.rst'
d['epub']=False
d['makefile']=False
d['batchfile']=False
generate(d)
"""
}

	void apply(Project project){
		project.apply plugin:'com.github.rzabini.gradle-jython'
		
		
		project.dependencies{
			pythonpath project.files(this.class.getProtectionDomain().getCodeSource().location.path)
		}
		
		SphinxExtension sphinxExt=new SphinxExtension(
			sourceDir:project.file('src/main/rst'),
			outputDir:project.file("$project.buildDir/doc"),
			release :project.version

		)
		
		project.extensions.add("sphinx", sphinxExt)

		project.task('sphinx', type:sphinx.SphinxTask)
		
		project.task('sphinx-quickstart', type:jython.JythonTask) {
			script(quickstart(project))
		
		
			ant.unzip(
				src: this.class.getProtectionDomain().getCodeSource().location.path,
				dest:"${project.sphinx.sourceDir}/_templates")
				{
					patternset {
						include(name: "sphinx/themes/default/**/*")
						include(name: "sphinx/themes/basic/**/*")
					}
					cutdirsmapper(dirs:2)
				}
			
		}
		
		
	}

}