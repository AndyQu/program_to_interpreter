def cli = new CliBuilder(usage:"add_line_by_line.groovy -h | add_line_by_line.groovy file_path")
cli.with{
	h longOpt:"--help", "show usage information"
}

def options = cli.parse(args)
if(options==null || options.h ){
	cli.usage()
	return
}

def extraArguments = options.arguments()
if(!extraArguments || extraArguments.size()<1){
	cli.usage()
	return
}

def filePath=extraArguments[0]
def inputFile = new File(filePath)
if(inputFile.exists()==false){
	println "Error: file[${filePath}] does not exist"
	return
}

int lineNum=0
inputFile.eachLine {
	line->
		lineNum++
		println "line ${lineNum}"
		print "\t"
		//必须使用trim()，将开头的空白符去掉。否则，如果存在空白符，则split返回的数组第一个元素将是“”
		//refer：http://docs.oracle.com/javase/8/docs/api/index.html
		def params=line.trim().split("\\s+")
		if(params.length<2){
			println "Error: params count < 2, '${params}'"
			return
		}
		def operand1=0
		try{
			operand1=Double.parseDouble(params[0])
		}catch(NumberFormatException e){
			println "Error: 1st parameter  not a double, [${params[0]}]"
				return
		}
		def operand2=0
		try{
			operand2=Double.parseDouble(params[1])
		}catch(NumberFormatException e){
			println "Error: 2nd parameter  not a double, [${params[1]}]"
				return
		}
		def result=operand1+operand2
		println "${operand1}+${operand2}=${result}"
}