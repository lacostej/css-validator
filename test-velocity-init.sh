# mvn install
jar=`ls -1 target/*.jar`
dir=.
deps_classpath=$dir/lib/jigsaw.jar:$dir/lib/velocity-1.6.4.jar:$dir/lib/commons-collections-3.2.1.jar:$dir/lib/commons-lang-2.5.jar
java -classpath target/test-classes/:target/classes/:$deps_classpath  org.w3c.css.css.CssValidator file:$dir/autotest/results/valid.css 

java -classpath $jar:$deps_classpath  org.w3c.css.css.CssValidator file:$dir/autotest/results/valid.css 
java -classpath $jar:target/classes/:$deps_classpath  org.w3c.css.css.CssValidator file:$dir/autotest/results/valid.css 
java -classpath target/classes/:$jar:$deps_classpath:  org.w3c.css.css.CssValidator file:$dir/autotest/results/valid.css 

