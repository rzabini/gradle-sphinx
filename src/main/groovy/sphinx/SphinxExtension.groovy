package sphinx

class SphinxExtension {

	def sourceDir 
	def outputDir 
	def release
	def title
	def author='nobody'
	
	void setSourceDir(sourceDir){
		this.sourceDir=sourceDir
		//this.sourceDir=project.file(sourceDir).absolutePath
		//assert project.file(sourceDir).exists(), "sourceDir $sourceDir not found"
	}
	
	void setOutputDir(outputDir){
		this.outputDir=outputDir
		//this.outputDir=project.file(outputDir).absolutePath
		//assert project.file(outputDir).exists(), "outputDir $outputDir not found"
	}
	
	void setRelease(release){
		this.release=release
	}
	
	void setTitle(title){
		this.title=title
	}


}