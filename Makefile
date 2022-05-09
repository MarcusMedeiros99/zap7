all:
	@javac -sourcepath src -d bin src/App.java

run:
	@java -cp bin App

clean:
	@rm -r bin
