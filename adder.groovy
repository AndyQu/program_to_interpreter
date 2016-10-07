def cli = new CliBuilder(usage:"adder.groovy -h | adder.groovy double_value double_value")
cli.with { h longOpt:"--help", "show usage information" }

def options = cli.parse(args)
if(!options || options.h){
	cli.usage()
	return
}

def extraArguments = options.arguments()
if(!extraArguments || extraArguments.size()<2){
	cli.usage()
	return
}
def operand1=0
try{
	operand1=Double.parseDouble(extraArguments[0])
}catch(NumberFormatException e){
	println "first argument ${extraArguments[0]} : not a numeric value."
	return
}
def operand2
try{
	operand2=Double.parseDouble(extraArguments[1])
}catch(NumberFormatException e){
	println "first argument ${extraArguments[1]} : not a numeric value."
	return
}
def result=operand1+operand2
println "add ${operand1} ${operand2} result=${result}"

